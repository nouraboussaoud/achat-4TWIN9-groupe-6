package tn.esprit.rh.achat;

import tn.esprit.rh.achat.services.IStockService;
import tn.esprit.rh.achat.entities.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StockServiceImplJUnitTest {

    @Autowired
    private IStockService iStockService;

    @Test
    void testAddStock() {
        Stock stock = new Stock();
        stock.setLibelleStock("Stock test");
        stock.setQte(50);
        stock.setQteMin(10);

        Stock saved = iStockService.addStock(stock);
        assertNotNull(saved, "Le stock sauvegardé ne doit pas être null");
        assertNotNull(saved.getIdStock(), "L'ID du stock doit être généré");
    }

    @Test
    void testRetrieveAllStocks() {
        List<Stock> stocks = iStockService.retrieveAllStocks();
        assertNotNull(stocks, "La liste retournée ne doit pas être null");
        assertTrue(stocks.size() >= 0, "La taille de la liste doit être >= 0");
    }
}
