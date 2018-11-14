package gk.defpub.restservice.controller;

import gk.defpub.restservice.model.Publication;
import gk.defpub.restservice.model.Role;
import gk.defpub.restservice.model.User;
import gk.defpub.restservice.service.PublicationService;
import gk.defpub.restservice.service.UserService;
import gk.defpub.restservice.validation.UserPermissionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

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
@Validated
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
    public Publication getPublication(@PathVariable(value = "id") String id,
                                      Principal principal) {
        validateRequest(principal, id);
        return publicationService.findById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePublication(@PathVariable(value = "id") String id,
                                  Principal principal) {
        validateRequest(principal, id);
        publicationService.delete(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public Publication createPublication(@Valid @RequestBody Publication publication,
                                         Principal principal) {
        publication.setUserId(userService.findOne(principal.getName()).getId());
        return publicationService.save(publication);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Publication updatePublication(@NotBlank @PathVariable(value = "id") String id,
                                         @Valid @RequestBody Publication publication,
                                         Principal principal) {
        validateRequest(principal, id);
        Publication oldPublication = publicationService.findById(id);
        oldPublication.setMessage(publication.getMessage());
        return publicationService.save(oldPublication);
    }

    private void validateRequest(Principal principal, String publicationId) {
        User currentUser = userService.findOne(principal.getName());
        Publication currentPublication = publicationService.findById(publicationId);
        if (currentPublication == null) {
            throw new NoSuchElementException("{\"error\":\"There is no publication with such ID.\"}");
        }
        if (currentUser.getRole() == Role.ADMIN || currentPublication.getUserId().equals(currentUser.getId())) {
            throw new UserPermissionException();
        }
        ;
    }
}
