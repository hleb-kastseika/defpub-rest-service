package gk.defpub.restservice.service.impl;

import gk.defpub.restservice.model.Publication;
import gk.defpub.restservice.repository.PublicationRepository;
import gk.defpub.restservice.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * PublicationServiceImpl class.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Service(value = "publicationService")
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public Publication save(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public List<Publication> findAll() {
        List<Publication> list = new ArrayList<>();
        publicationRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public List<Publication> findAllForUser(String userId) {
        return publicationRepository.findByUserId(userId);
    }

    @Override
    public Publication findById(String id) {
        return publicationRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        publicationRepository.deleteById(id);
    }
}
