package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplMockitoTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock;

    @BeforeEach
    public void setup() {
        stock = new Stock();
        stock.setIdStock(1L);
        stock.setLibelleStock("Stock test");
        stock.setQte(50);
        stock.setQteMin(10);
    }

    @Test
    public void testRetrieveStock() {
        // Configuration du mock
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));

        // Appel de la méthode à tester
        Stock retrievedStock = stockService.retrieveStock(1L);

        // Vérifications
        Assertions.assertNotNull(retrievedStock);
        Assertions.assertEquals(1L, retrievedStock.getIdStock());
        Assertions.assertEquals("Stock test", retrievedStock.getLibelleStock());

        // Vérifier que la méthode du repository a été appelée exactement une fois
        Mockito.verify(stockRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testRetrieveStatusStock() {
        // Création d'une liste de stocks
        List<Stock> stocks = new ArrayList<>();

        // Stock avec quantité supérieure à qteMin
        Stock stock1 = new Stock();
        stock1.setLibelleStock("Stock normal");
        stock1.setQte(20);
        stock1.setQteMin(10);
        stocks.add(stock1);

        // Stock avec quantité inférieure à qteMin
        Stock stock2 = new Stock();
        stock2.setLibelleStock("Stock épuisé");
        stock2.setQte(5);
        stock2.setQteMin(10);
        stocks.add(stock2);

        // Configuration du mock
        Mockito.when(stockRepository.findAll()).thenReturn(stocks);

        // Appel de la méthode à tester
        String status = stockService.retrieveStatusStock();

        // Vérifications
        Assertions.assertNotNull(status);
        Assertions.assertTrue(status.contains("Stock épuisé"));

        // Vérifier que la méthode du repository a été appelée exactement une fois
        Mockito.verify(stockRepository, Mockito.times(1)).findAll();
    }
}