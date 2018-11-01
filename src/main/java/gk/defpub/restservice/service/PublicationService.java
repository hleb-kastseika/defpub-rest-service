package gk.defpub.restservice.service;

import gk.defpub.restservice.model.Publication;

import java.util.List;

/**
 * PublicationService interface.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public interface PublicationService {

    Publication save(Publication publication);
    List<Publication> findAll();
    List<Publication> findAllForUser(String userId);
    Publication findById(String id);
    void delete(String id);
}
