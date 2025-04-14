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
        Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Stock retrieved = stockService.retrieveStock(1L);

        Assertions.assertNotNull(retrieved);
        Assertions.assertEquals(1L, retrieved.getIdStock());
        Assertions.assertEquals("Stock test", retrieved.getLibelleStock());

        Mockito.verify(stockRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testRetrieveStatusStock() {
        List<Stock> stocks = new ArrayList<>();

        Stock stock1 = new Stock(); // normal
        stock1.setLibelleStock("Stock normal");
        stock1.setQte(20);
        stock1.setQteMin(10);
        stocks.add(stock1);

        Stock stock2 = new Stock(); // épuisé
        stock2.setLibelleStock("Stock épuisé");
        stock2.setQte(5);
        stock2.setQteMin(10);
        stocks.add(stock2);

        Mockito.when(stockRepository.findAll()).thenReturn(stocks);

        String status = stockService.retrieveStatusStock();

        Assertions.assertNotNull(status);
        Assertions.assertTrue(status.toLowerCase().contains("épuisé"), "Le statut doit contenir 'épuisé'");


        Mockito.verify(stockRepository, Mockito.times(1)).findAll();
    }
}
