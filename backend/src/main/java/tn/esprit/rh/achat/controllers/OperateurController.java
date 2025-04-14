package tn.esprit.rh.achat.controllers;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

import java.util.List;

@RestController
@Api(tags = "Gestion des opérateurs")
@RequestMapping("/operateur")
@CrossOrigin("*")
public class OperateurController {
	private static final Logger LOG = LoggerFactory.getLogger(OperateurController.class);

	@Autowired
	IOperateurService operateurService;

	@GetMapping("/retrieve-all-operateurs")
	@ResponseBody
	public List<Operateur> getOperateurs() {
		LOG.info("API Appelée : GET /operateur/retrieve-all-operateurs");
		return operateurService.retrieveAllOperateurs();
	}

	@GetMapping("/retrieve-operateur/{operateur-id}")
	@ResponseBody
	public Operateur retrieveOperateur(@PathVariable("operateur-id") Long operateurId) {
		LOG.info("API Appelée : GET /operateur/retrieve-operateur/{}", operateurId);
		return operateurService.retrieveOperateur(operateurId);
	}

	@PostMapping("/add-operateur")
	@ResponseBody
	public Operateur addOperateur(@RequestBody Operateur op) {
		LOG.info("API Appelée : POST /operateur/add-operateur - Données : {}", op);
		return operateurService.addOperateur(op);
	}

	@DeleteMapping("/remove-operateur/{operateur-id}")
	@ResponseBody
	public void removeOperateur(@PathVariable("operateur-id") Long operateurId) {
		LOG.warn("API Appelée : DELETE /operateur/remove-operateur/{}", operateurId);
		operateurService.deleteOperateur(operateurId);
	}

	@PutMapping("/modify-operateur")
	@ResponseBody
	public Operateur modifyOperateur(@RequestBody Operateur operateur) {
		LOG.info("API Appelée : PUT /operateur/modify-operateur - Données : {}", operateur);
		return operateurService.updateOperateur(operateur);
	}
}