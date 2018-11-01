package gk.defpub.restservice.repository;

import gk.defpub.restservice.model.Publication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PublicationRepository interface.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Repository
public interface PublicationRepository extends CrudRepository<Publication, String> {

    List<Publication> findByUserId(String userId);
}
