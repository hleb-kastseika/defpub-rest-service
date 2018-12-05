package gk.defpub.restservice.service.impl;

import gk.defpub.restservice.model.Publication;
import gk.defpub.restservice.repository.PublicationRepository;
import gk.defpub.restservice.service.PublicationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * PublicationServiceImplTest class.
 * <p>
 * Date: Nov 20, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class PublicationServiceImplTest {
    private static final String TEST_PUBLICATION_ID = "testPubId";
    private static final String TEST_USER_ID = "testUserId";
    private static final String TEST_MESSAGE = "test publication";
    private Publication publication;

    @Mock
    private PublicationRepository publicationRepository;

    @InjectMocks
    private PublicationService publicationService = new PublicationServiceImpl();

    @Before
    public void setUp() {
        initMocks(this);
        publication = new Publication();
        publication.setId(TEST_PUBLICATION_ID);
        publication.setMessage(TEST_MESSAGE);
        publication.setUserId(TEST_USER_ID);
    }

    @Test
    public void testSave() {
        when(publicationRepository.save(any(Publication.class))).thenReturn(publication);

        Publication savedPublication = publicationService.save(publication);
        assertThat(savedPublication.getUserId()).isEqualTo(TEST_USER_ID);
        assertThat(savedPublication.getMessage()).isEqualTo(TEST_MESSAGE);
        verify(publicationRepository).save(any(Publication.class));
        verifyNoMoreInteractions(publicationRepository);
    }

    @Test
    public void testFindAll() {
        List<Publication> publications = new ArrayList<>();
        publications.add(publication);
        when(publicationRepository.findAll()).thenReturn(publications);

        List<Publication> allPubs = publicationService.findAll();
        assertThat(allPubs.size()).isEqualTo(1);
        verify(publicationRepository).findAll();
        verifyNoMoreInteractions(publicationRepository);
    }

    @Test
    public void testFindAllForUser() {
        List<Publication> publications = new ArrayList<>();
        publications.add(publication);
        when(publicationRepository.findByUserId(eq(TEST_USER_ID))).thenReturn(publications);

        List<Publication> allUserPubs = publicationService.findAllForUser(TEST_USER_ID);
        assertThat(allUserPubs.size()).isEqualTo(1);
        verify(publicationRepository).findByUserId(eq(TEST_USER_ID));
        verifyNoMoreInteractions(publicationRepository);
    }

    @Test
    public void testFindById() {
        when(publicationRepository.findById(eq(TEST_PUBLICATION_ID))).thenReturn(Optional.of(publication));

        Publication returnedPublication = publicationService.findById(TEST_PUBLICATION_ID);
        assertThat(returnedPublication).isNotNull();
        assertThat(returnedPublication.getId()).isEqualTo(TEST_PUBLICATION_ID);
        verify(publicationRepository).findById(eq(TEST_PUBLICATION_ID));
        verifyNoMoreInteractions(publicationRepository);
    }

    @Test
    public void testDelete() {
        publicationService.delete(TEST_PUBLICATION_ID);
        verify(publicationRepository).deleteById(TEST_PUBLICATION_ID);
        verifyNoMoreInteractions(publicationRepository);
    }
}
