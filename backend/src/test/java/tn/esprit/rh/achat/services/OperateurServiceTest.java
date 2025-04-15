package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceTest {

    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Test
     void testRetrieveAllOperateurs() {
        // Arrange
        Operateur op1 = new Operateur(1L, "Nom1", "Prenom1", "pass1");
        Operateur op2 = new Operateur(2L, "Nom2", "Prenom2", "pass2");
        List<Operateur> expectedOperateurs = Arrays.asList(op1, op2);

        when(operateurRepository.findAll()).thenReturn(expectedOperateurs);

        // Act
        List<Operateur> result = operateurService.retrieveAllOperateurs();

        // Assert
        assertEquals(2, result.size());
        verify(operateurRepository, times(1)).findAll();
    }

    @Test
     void testAddOperateur() {
        // Arrange
        Operateur newOperateur = new Operateur(null, "New", "User", "password");
        Operateur savedOperateur = new Operateur(1L, "New", "User", "password");

        when(operateurRepository.save(any(Operateur.class))).thenReturn(savedOperateur);

        // Act
        Operateur result = operateurService.addOperateur(newOperateur);

        // Assert
        assertNotNull(result.getIdOperateur());
        assertEquals("New", result.getNom());
        verify(operateurRepository, times(1)).save(any(Operateur.class));
    }

    @Test
     void testDeleteOperateur() {
        // Arrange
        Long idToDelete = 1L;
        doNothing().when(operateurRepository).deleteById(idToDelete);

        // Act
        operateurService.deleteOperateur(idToDelete);

        // Assert
        verify(operateurRepository, times(1)).deleteById(idToDelete);
    }

    @Test
     void testUpdateOperateur() {
        // Arrange
        Operateur existingOperateur = new Operateur(1L, "Existing", "User", "pass");
        when(operateurRepository.save(existingOperateur)).thenReturn(existingOperateur);

        // Act
        Operateur result = operateurService.updateOperateur(existingOperateur);

        // Assert
        assertEquals("Existing", result.getNom());
        verify(operateurRepository, times(1)).save(existingOperateur);
    }

    @Test
     void testRetrieveOperateurFound() {
        // Arrange
        Long id = 1L;
        Operateur expectedOperateur = new Operateur(id, "Found", "User", "pass");
        when(operateurRepository.findById(id)).thenReturn(Optional.of(expectedOperateur));

        // Act
        Operateur result = operateurService.retrieveOperateur(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getIdOperateur());
        verify(operateurRepository, times(1)).findById(id);
    }

    @Test
     void testRetrieveOperateurNotFound() {
        // Arrange
        Long id = 99L;
        when(operateurRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Operateur result = operateurService.retrieveOperateur(id);

        // Assert
        assertNull(result);
        verify(operateurRepository, times(1)).findById(id);
    }
}