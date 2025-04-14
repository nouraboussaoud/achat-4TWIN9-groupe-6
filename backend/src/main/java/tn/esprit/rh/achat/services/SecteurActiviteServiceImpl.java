package tn.esprit.rh.achat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.List;

@Service
public class SecteurActiviteServiceImpl implements ISecteurActiviteService {

    private static final Logger logger = LoggerFactory.getLogger(SecteurActiviteServiceImpl.class);

    @Autowired
    SecteurActiviteRepository secteurActiviteRepository;

    @Override
    public List<SecteurActivite> retrieveAllSecteurActivite() {
        logger.info("Retrieving all secteurActivites");
        return (List<SecteurActivite>) secteurActiviteRepository.findAll();
    }

    @Override
    public SecteurActivite addSecteurActivite(SecteurActivite sa) {
        logger.info("Adding new secteurActivite: {}", sa);
        secteurActiviteRepository.save(sa);
        return sa;
    }

    @Override
    public void deleteSecteurActivite(Long id) {
        logger.warn("Deleting secteurActivite with id: {}", id);
        secteurActiviteRepository.deleteById(id);
    }

    @Override
    public SecteurActivite updateSecteurActivite(SecteurActivite sa) {
        logger.info("Updating secteurActivite: {}", sa);
        secteurActiviteRepository.save(sa);
        return sa;
    }

    @Override
    public SecteurActivite retrieveSecteurActivite(Long id) {
        logger.info("Retrieving secteurActivite with id: {}", id);
        SecteurActivite secteurActivite = secteurActiviteRepository.findById(id).orElse(null);
        if (secteurActivite == null) {
            logger.warn("SecteurActivite with id {} not found", id);
        }
        return secteurActivite;
    }
}

