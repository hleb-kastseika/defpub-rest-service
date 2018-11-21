package gk.defpub.restservice.repository;

import gk.defpub.restservice.model.Publication;
import gk.defpub.restservice.model.Role;
import gk.defpub.restservice.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * PublicationRepositoryTest class.
 * <p>
 * Date: Nov 19, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PublicationRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PublicationRepository publicationRepository;

    @Test
    public void testFindByUserId() {
        User user = new User();
        user.setUsername("testUser");
        user.setRole(Role.USER);
        entityManager.persist(user);
        Publication publication = new Publication();
        publication.setUserId(user.getId());
        publication.setMessage("test message");
        entityManager.persist(publication);
        entityManager.flush();

        List<Publication> publications = publicationRepository.findByUserId(user.getId());
        assertThat(publications.size()).isEqualTo(1);
        assertThat(publications.get(0).getId()).isEqualTo(publication.getId());
    }
}
