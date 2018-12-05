package gk.defpub.restservice.controller;

import gk.defpub.restservice.model.Publication;
import gk.defpub.restservice.model.User;
import gk.defpub.restservice.service.PublicationService;
import gk.defpub.restservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * PublicationControllerTest class.
 * <p>
 * Date: Nov 23, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class PublicationControllerTest {
    private static final String TEST_USERNAME = "testUsername";
    private static final String TEST_USER_ID = "testUserId";
    private static final String TEST_PUBLICATION_ID = "testPubId";
    private static final String UPDATED_MESSAGE = "updated message";
    private Publication publication = new Publication();
    private List<Publication> publications = new ArrayList<>();
    private User user = new User();
    private Principal principal;

    @Mock
    private PublicationService publicationService;
    @Mock
    private UserService userService;
    @InjectMocks
    private PublicationController controller = new PublicationController();

    @Before
    public void setUp() {
        initMocks(this);
        publication.setId(TEST_PUBLICATION_ID);
        publication.setUserId(TEST_USER_ID);
        publications.add(publication);

        user.setId(TEST_USER_ID);
        user.setUsername(TEST_USERNAME);

        principal = new Principal() {
            @Override
            public boolean equals(Object another) {
                return false;
            }

            @Override
            public String toString() {
                return getName();
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String getName() {
                return TEST_USERNAME;
            }
        };
    }

    @Test
    public void testGetAllPublications() {
        when(publicationService.findAll()).thenReturn(publications);

        List<Publication> returnedPublications = controller.getAllPublications();
        assertThat(returnedPublications).isNotEmpty();
        verify(publicationService).findAll();
        verifyNoMoreInteractions(publicationService);
    }

    @Test
    public void testGetAllUserPublications() {
        when(userService.findOne(eq(TEST_USERNAME))).thenReturn(user);
        when(publicationService.findAllForUser(eq(TEST_USER_ID))).thenReturn(publications);

        List<Publication> returnedPublications = controller.getAllUserPublications(principal);
        assertThat(returnedPublications).isNotEmpty();
        verify(userService).findOne(eq(TEST_USERNAME));
        verify(publicationService).findAllForUser(eq(TEST_USER_ID));
        verifyNoMoreInteractions(userService, publicationService);
    }

    @Test
    public void testGetPublication() {
        when(publicationService.findById(TEST_PUBLICATION_ID)).thenReturn(publication);

        Publication returnedPublication = controller.getPublication(TEST_PUBLICATION_ID, principal);
        assertThat(returnedPublication).isNotNull();
        verify(publicationService).findById(eq(TEST_PUBLICATION_ID));
        verifyNoMoreInteractions(publicationService);
    }

    @Test
    public void testDeletePublication() {
        controller.deletePublication(TEST_PUBLICATION_ID, principal);
        verify(publicationService).delete(eq(TEST_PUBLICATION_ID));
        verifyNoMoreInteractions(publicationService);
    }

    @Test
    public void testCreatePublication() {
        when(userService.findOne(eq(TEST_USERNAME))).thenReturn(user);
        when(publicationService.save(eq(publication))).thenReturn(publication);

        Publication savedPublication = controller.createPublication(publication, principal);
        assertThat(savedPublication).isNotNull();
        verify(userService).findOne(eq(TEST_USERNAME));
        verify(publicationService).save(eq(publication));
        verifyNoMoreInteractions(publicationService);
    }

    @Test
    public void testUpdatePublication() {
        when(publicationService.findById(TEST_PUBLICATION_ID)).thenReturn(publication);
        when(publicationService.save(eq(publication))).thenReturn(publication);

        publication.setMessage(UPDATED_MESSAGE);
        Publication savedPublication = controller.updatePublication(TEST_PUBLICATION_ID, principal, publication);
        assertThat(savedPublication).isNotNull();
        assertThat(savedPublication.getMessage()).isEqualTo(UPDATED_MESSAGE);
        verify(publicationService).findById(eq(TEST_PUBLICATION_ID));
        verify(publicationService).save(eq(publication));
        verifyNoMoreInteractions(publicationService);
    }
}
