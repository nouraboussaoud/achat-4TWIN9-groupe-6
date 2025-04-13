package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProduitServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Test: retrieveAllProduits
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

    // ✅ Test: retrieveProduit by ID
    @Test
    public void testRetrieveProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("ABC");

        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));

        Produit result = produitService.retrieveProduit(1L);
        assertEquals("ABC", result.getCodeProduit());
        verify(produitRepository, times(1)).findById(1L);
    }

    // ✅ Test: addProduit
    @Test
    public void testAddProduit() {
        Produit produit = new Produit();
        produit.setCodeProduit("NEW");

        when(produitRepository.save(produit)).thenReturn(produit);

        Produit result = produitService.addProduit(produit);
        assertEquals("NEW", result.getCodeProduit());
        verify(produitRepository, times(1)).save(produit);
    }

    // ✅ Test: deleteProduit
    @Test
public void testDeleteProduit() {
    Long id = 1L;

    when(produitRepository.existsById(id)).thenReturn(true); // 👈 Add this line
    doNothing().when(produitRepository).deleteById(id);

    produitService.deleteProduit(id);

    verify(produitRepository, times(1)).existsById(id); // Optional but good
    verify(produitRepository, times(1)).deleteById(id); // ✅ Now this should pass
}


    // ✅ Test: updateProduit
    @Test
    public void testUpdateProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("UPDATED");

        when(produitRepository.save(produit)).thenReturn(produit);

        Produit updated = produitService.updateProduit(produit);
        assertEquals("UPDATED", updated.getCodeProduit());
        verify(produitRepository, times(1)).save(produit);
    }

    // ✅ Test: assignProduitToStock
    @Test
    public void testAssignProduitToStock() {
        Long produitId = 1L;
        Long stockId = 2L;

        Produit produit = new Produit();
        produit.setIdProduit(produitId);

        Stock stock = new Stock();
        stock.setIdStock(stockId);

        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        produitService.assignProduitToStock(produitId, stockId);

        assertEquals(stock, produit.getStock());
        verify(produitRepository).save(produit);
    }
}
