package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    
    @Autowired
    private StockRepository stockRepository;
    
    @Autowired
    private CategorieProduitRepository categorieProduitRepository;

    @Override
    public List<Produit> retrieveAllProduits() {
        List<Produit> produits = (List<Produit>) produitRepository.findAll();
        for (Produit produit : produits) {
            log.info("Produit retrieved: ID = {}, Name = {}", produit.getId_produit(), produit.getLibelle_produit());
        }
        return produits;
    }

    @Transactional
    @Override
    public Produit addProduit(Produit p) {
        try {
            produitRepository.save(p);
            log.info("Produit added successfully: ID = {}, Name = {}", p.getId_produit(), p.getLibelle_produit());
        } catch (Exception e) {
            log.error("Error while adding produit: {}", p.getLibelle_produit(), e);
        }
        return p;
    }

    @Override
    public void deleteProduit(Long produitId) {
        if (produitRepository.existsById(produitId)) {
            produitRepository.deleteById(produitId);
            log.info("Produit with ID: {} deleted successfully", produitId);
        } else {
            log.warn("Produit with ID: {} not found for deletion", produitId);
        }
    }

    @Override
    public Produit updateProduit(Produit p) {
        Produit updatedProduit = produitRepository.save(p);
        log.info("Produit updated: ID = {}, Name = {}", updatedProduit.getId_produit(), updatedProduit.getLibelle_produit());
        return updatedProduit;
    }

    @Override
    public Produit retrieveProduit(Long produitId) {
        Produit produit = produitRepository.findById(produitId).orElse(null);
        if (produit != null) {
            log.info("Produit retrieved: ID = {}, Name = {}", produit.getId_produit(), produit.getLibelle_produit());
        } else {
            log.warn("Produit with ID: {} not found", produitId);
        }
        return produit;
    }

    @Override
    public void assignProduitToStock(Long idProduit, Long idStock) {
        Produit produit = produitRepository.findById(idProduit).orElse(null);
        Stock stock = stockRepository.findById(idStock).orElse(null);

        if (produit == null) {
            log.warn("Produit with ID: {} not found", idProduit);
            return;
        }

        if (stock == null) {
            log.warn("Stock with ID: {} not found", idStock);
            return;
        }

        produit.setStock(stock);
        produitRepository.save(produit);
        log.info("Assigned produit with ID: {} to stock with ID: {}", produit.getId_produit(), stock.getId_stock());
    }
}
