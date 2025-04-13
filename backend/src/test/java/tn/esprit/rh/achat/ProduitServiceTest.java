package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProduitServiceTest {

    @Mock
    ProduitRepository produitRepository;

    @InjectMocks
    ProduitServiceImpl produitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllProduits() {
        Produit p1 = new Produit();
        p1.setIdProduit(1L);
        p1.setCodeProduit("ABC");

        Produit p2 = new Produit();
        p2.setIdProduit(2L);
        p2.setCodeProduit("XYZ");

        when(produitRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Produit> produits = produitService.retrieveAllProduits();
        assertEquals(2, produits.size());
        assertEquals("ABC", produits.get(0).getCodeProduit());
        verify(produitRepository, times(1)).findAll();
    }
}
