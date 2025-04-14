import {Component, OnInit} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {SecteurActivite} from '../shared/Model/Secteur-activite';
import {SecteurActiviteService} from '../shared/Service/Secteur-activite.service';
import { NGXLogger } from 'ngx-logger';

@Component({
  selector: 'app-secteur-activite',
  templateUrl: './secteur-activite.component.html',
  styleUrls: ['./secteur-activite.component.css']
})
export class SecteurActiviteComponent implements OnInit {

  listSec: any;
  form: boolean = false;
  sec!: SecteurActivite;
  closeResult!: string;

  constructor(
    private secteurActiviteService: SecteurActiviteService,
    private modalService: NgbModal,
    private logger: NGXLogger
  ) {}

  ngOnInit(): void {
    this.logger.info('SecteurActiviteComponent initialized');
    this.getAllSec();
    this.sec = {
      idSecteurActivite: null,
      codeSecteurActivite: null,
      libelleSecteurActivite: null
    };
  }

  getAllSec() {
    this.logger.debug('Fetching all SecteurActivites...');
    this.secteurActiviteService.getAllSecteurActivites().subscribe(res => {
      this.logger.info('Successfully fetched SecteurActivites');
      this.listSec = res;
    });
  }

  addSec(p: any) {
    this.logger.debug('Adding new SecteurActivite', p);
    this.secteurActiviteService.addSecteurActivite(p).subscribe(() => {
      this.logger.info('SecteurActivite added');
      this.getAllSec();
      this.form = false;
    });
  }

  editSec(sec: SecteurActivite) {
    this.logger.debug('Editing SecteurActivite', sec);
    this.secteurActiviteService.editSecteurActivite(sec).subscribe(() => {
      this.logger.info(`SecteurActivite updated: ID=${sec.idSecteurActivite}`);
    });
  }

  deleteSec(idSec: any) {
    this.logger.warn(`Deleting SecteurActivite ID=${idSec}`);
    this.secteurActiviteService.deleteSecteurActivite(idSec).subscribe(() => {
      this.logger.info(`SecteurActivite deleted: ID=${idSec}`);
      this.getAllSec();
    });
  }

  open(content: any, action: any) {
    if (action != null) {
      this.sec = action;
      this.logger.debug('Opening modal to edit SecteurActivite', action);
    } else {
      this.sec = new SecteurActivite();
      this.logger.debug('Opening modal to add new SecteurActivite');
    }

    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      this.logger.debug(`Modal closed with result: ${result}`);
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      this.logger.debug(`Modal dismissed with reason: ${this.closeResult}`);
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
    this.logger.info('Action canceled, hiding form');
    this.form = false;
  }
}

