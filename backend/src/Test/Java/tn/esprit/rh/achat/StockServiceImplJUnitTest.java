package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.services.IStockService;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class StockServiceImplJUnitTest {


    IStockService iStockService;

    @Test
    public void testRetrieveAllStocks() {
        // On récupère tous les stocks
        List<Stock> stocks = iStockService.retrieveAllStocks();
        // On vérifie que la liste n'est pas vide
        Assertions.assertNotNull(stocks);
    }

    @Test
    public void testAddStock() {
        // Création d'un nouveau stock
        Stock s = new Stock();
        s.setLibelleStock("stock test");
        s.setQte(100);
        s.setQteMin(10);

        // Ajout du stock
        Stock savedStock = iStockService.addStock(s);

        // Vérification
        Assertions.assertNotNull(savedStock.getIdStock());
        Assertions.assertEquals("stock test", savedStock.getLibelleStock());
        Assertions.assertEquals(100, savedStock.getQte());
        Assertions.assertEquals(10, savedStock.getQteMin());

        // Nettoyage
        iStockService.deleteStock(savedStock.getIdStock());
    }
}