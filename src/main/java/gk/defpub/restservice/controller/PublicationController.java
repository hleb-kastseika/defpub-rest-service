package gk.defpub.restservice.controller;

import gk.defpub.restservice.model.Publication;
import gk.defpub.restservice.service.PublicationService;
import gk.defpub.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * PublicationController class.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Publication> getAllPublications() {
        return publicationService.findAll();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Publication> getAllUserPublications(Principal principal) {
        return publicationService.findAllForUser(userService.findOne(principal.getName()).getId());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Publication getPublication(@PathVariable(value = "id") String id) {
        return publicationService.findById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePublication(@PathVariable(value = "id") String id) {
        publicationService.delete(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public Publication createPublication(@RequestBody Publication publication, Principal principal) {
        publication.setUserId(userService.findOne(principal.getName()).getId());
        return publicationService.save(publication);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Publication updatePublication(@PathVariable(value = "id") String id,
                                         @RequestBody Publication publication,
                                         Principal principal) {
        Publication oldPublication = publicationService.findById(id);
        if (oldPublication == null) {
            //TODO add error response
        }
        String currentUserId = userService.findOne(principal.getName()).getId();
        if(!currentUserId.equals(oldPublication.getUserId())) {
            //TODO add error response
        }
        oldPublication.setMessage(publication.getMessage());
        return publicationService.save(oldPublication);
    }
}
