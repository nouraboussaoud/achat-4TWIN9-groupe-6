package tn.esprit.rh.achat.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.List;

@Service
public class OperateurServiceImpl implements IOperateurService {
	private static final Logger LOG = LoggerFactory.getLogger(OperateurServiceImpl.class);
	@Autowired
	OperateurRepository operateurRepository;
	@Override
	public List<Operateur> retrieveAllOperateurs() {
		LOG.info("Début de la récupération de tous les opérateurs");
		List<Operateur> operateurs = (List<Operateur>) operateurRepository.findAll();
		LOG.info("{} opérateurs récupérés avec succès", operateurs.size());
		return operateurs;
	}

	@Override
	public Operateur addOperateur(Operateur o) {
		LOG.info("Tentative d'ajout d'un nouvel opérateur : {}", o);
		Operateur savedOperateur = operateurRepository.save(o);
		LOG.info("Opérateur ajouté avec succès - ID : {}", savedOperateur.getIdOperateur());
		return savedOperateur;
	}

	@Override
	public void deleteOperateur(Long id) {
		LOG.warn("Tentative de suppression de l'opérateur ID : {}", id);
		operateurRepository.deleteById(id);
		LOG.info("Opérateur supprimé avec succès - ID : {}", id);
	}

	@Override
	public Operateur updateOperateur(Operateur o) {
		LOG.info("Tentative de mise à jour de l'opérateur ID : {}", o.getIdOperateur());
		Operateur updatedOperateur = operateurRepository.save(o);
		LOG.info("Opérateur mis à jour avec succès - ID : {}", updatedOperateur.getIdOperateur());
		return updatedOperateur;
	}


	@Override
	public Operateur retrieveOperateur(Long id) {
		LOG.debug("Tentative de récupération de l'opérateur ID : {}", id);
		Operateur operateur = operateurRepository.findById(id).orElse(null);
		if(operateur == null) {
			LOG.warn("Aucun opérateur trouvé avec l'ID : {}", id);
		} else {
			LOG.info("Opérateur récupéré avec succès : {}", operateur);
		}
		return operateur;
	}

}
