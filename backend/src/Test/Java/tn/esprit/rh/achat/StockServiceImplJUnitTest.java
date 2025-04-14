package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockServiceImplJUnitTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Test: retrieveAllStocks
    @Test
    public void testRetrieveAllStocks() {
        Stock s1 = new Stock();
        s1.setIdStock(1L);
        s1.setLibelleStock("Stock 1");

        Stock s2 = new Stock();
        s2.setIdStock(2L);
        s2.setLibelleStock("Stock 2");

        when(stockRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<Stock> stocks = stockService.retrieveAllStocks();
        assertEquals(2, stocks.size());
        assertEquals("Stock 1", stocks.get(0).getLibelleStock());
        verify(stockRepository, times(1)).findAll();
    }

    // ✅ Test: retrieveStock by ID
    @Test
    public void testRetrieveStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setLibelleStock("Test Stock");

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Stock result = stockService.retrieveStock(1L);
        assertEquals("Test Stock", result.getLibelleStock());
        verify(stockRepository, times(1)).findById(1L);
    }

    // ✅ Test: addStock
    @Test
    public void testAddStock() {
        Stock stock = new Stock();
        stock.setLibelleStock("New Stock");

        when(stockRepository.save(stock)).thenReturn(stock);

        Stock result = stockService.addStock(stock);
        assertEquals("New Stock", result.getLibelleStock());
        verify(stockRepository, times(1)).save(stock);
    }

    // ✅ Test: deleteStock
    /*@Test
    public void testDeleteStock() {
        Long id = 1L;

        when(stockRepository.existsById(id)).thenReturn(true);
        doNothing().when(stockRepository).deleteById(id);

        stockService.deleteStock(id);

        verify(stockRepository, times(1)).existsById(id);
        verify(stockRepository, times(1)).deleteById(id);
    }*/

    // ✅ Test: updateStock
    @Test
    public void testUpdateStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setLibelleStock("Updated Stock");

        when(stockRepository.save(stock)).thenReturn(stock);

        Stock updated = stockService.updateStock(stock);
        assertEquals("Updated Stock", updated.getLibelleStock());
        verify(stockRepository, times(1)).save(stock);
    }

    // ✅ Test: retrieveStatusStock
    @Test
    public void testRetrieveStatusStock() {
        Stock s1 = new Stock();
        s1.setLibelleStock("Low Stock");
        s1.setQte(5);
        s1.setQteMin(10);

        when(stockRepository.retrieveStatusStock()).thenReturn(Arrays.asList(s1));

        String status = stockService.retrieveStatusStock();
        assertTrue(status.contains("inférieur à la quantité minimale"));
        verify(stockRepository, times(1)).retrieveStatusStock();
    }
}