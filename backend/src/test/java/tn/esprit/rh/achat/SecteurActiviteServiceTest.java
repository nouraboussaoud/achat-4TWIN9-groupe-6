package tn.esprit.rh.achat;

import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SecteurActiviteServiceTest {

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private SecteurActiviteServiceImpl secteurActiviteService;

    private SecteurActivite secteurActivite;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        secteurActivite = new SecteurActivite(1L, "Code 1", "Secteur 1", null);
    }

    @Test
    void testAddSecteurActivite() {
        when(secteurActiviteRepository.save(any(SecteurActivite.class))).thenReturn(secteurActivite);

        SecteurActivite result = secteurActiviteService.addSecteurActivite(secteurActivite);

        assertNotNull(result);
        assertEquals("Secteur 1", result.getLibelleSecteurActivite());
        verify(secteurActiviteRepository, times(1)).save(secteurActivite);
    }

    @Test
    void testDeleteSecteurActivite() {
        when(secteurActiviteRepository.findById(1L)).thenReturn(java.util.Optional.of(secteurActivite));

        secteurActiviteService.deleteSecteurActivite(1L);

        verify(secteurActiviteRepository, times(1)).deleteById(1L);
    }
}

