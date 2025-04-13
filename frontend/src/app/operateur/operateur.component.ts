import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Operateur } from '../shared/Model/Operateur';
import { OperateurService } from '../shared/Service/Operateur.service';

@Component({
  selector: 'app-operateur',
  templateUrl: './operateur.component.html',
  styleUrls: ['./operateur.component.css']
})
export class OperateurComponent implements OnInit {

  listOperateurs: any;
  form: boolean = false;
  operateur!: Operateur;
  closeResult!: string;

  constructor(private operateurService: OperateurService, private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.getAllOperateurs();
    this.operateur = {
      idOperateur:null,
      nom:null,
      prenom:null,
      password:null
    }
  }

  getAllOperateurs() {
    console.log('Tentative de récupération des opérateurs');
    this.operateurService.getAllOperateurs().subscribe((res) => {
      console.log('Opérateurs récupérés avec succès', res); // Log après la récupération des opérateurs
      this.listOperateurs = res;
    },
    (error) => {
      console.error('Erreur lors de la récupération des opérateurs', error); // Log en cas d'erreur
    }
  );
  }


  addOperateur(o: Operateur) {
    console.log('Tentative d\'ajout d\'un opérateur', o); // Log avant l'ajout
    this.operateurService.addOperateur(o).subscribe(
      (res) => {
        console.log('Opérateur ajouté avec succès', res); // Log après l'ajout
        this.getAllOperateurs(); // Récupérer à nouveau la liste
        this.form = false; // Fermer le formulaire
      },
      (error) => {
        console.error('Erreur lors de l\'ajout de l\'opérateur', error); // Log en cas d'erreur
      }
    );
  }

  editOperateur(operateur: Operateur) {
    console.log('Tentative de modification de l\'opérateur', operateur); // Log avant la modification
    this.operateurService.editOperateur(operateur).subscribe(
      (res) => {
        console.log('Opérateur modifié avec succès', res); // Log après la modification
        this.getAllOperateurs();
      },
      (error) => {
        console.error('Erreur lors de la modification de l\'opérateur', error); // Log en cas d'erreur
      }
    );
  }


  deleteOperateur(idOperateur: number) {
    console.log('Tentative de suppression de l\'opérateur avec ID', idOperateur); // Log avant la suppression
    this.operateurService.deleteOperateur(idOperateur).subscribe(
      () => {
        console.log('Opérateur supprimé avec succès'); // Log après la suppression
        this.getAllOperateurs();
      },
      (error) => {
        console.error('Erreur lors de la suppression de l\'opérateur', error); // Log en cas d'erreur
      }
    );
  }
  open(content: any, action: any) {
    if (action != null)
      this.operateur = action
    else
      this.operateur = new Operateur();
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  cancel() {
    this.form = false;
  }
}
