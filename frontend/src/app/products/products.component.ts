import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Product } from '../shared/Model/Product';
import { ProductService } from '../shared/Service/Product.service';
import { NGXLogger } from 'ngx-logger';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  listProducts: any;
  form: boolean = false;
  product!: Product;
  closeResult!: string;

  constructor(
    private productService: ProductService,
    private modalService: NgbModal,
    private logger: NGXLogger
  ) {}

  ngOnInit(): void {
    this.logger.info('Initialisation du composant ProductsComponent');
    this.getAllProducts();
    this.product = {
      idProduit: null,
      codeProduit: null,
      libelleProduit: null,
      prix: null,
      dateCreation: null,
      dateDerniereModification: null
    };
  }

  getAllProducts() {
    this.logger.debug('Chargement de tous les produits...');
    this.productService.getAllProducts().subscribe(res => {
      this.logger.info('Produits récupérés avec succès');
      this.listProducts = res;
    });
  }

  addProduct(p: any) {
    this.logger.debug('Ajout d’un nouveau produit', p);
    this.productService.addProduct(p).subscribe(() => {
      this.logger.info('Produit ajouté avec succès');
      this.getAllProducts();
      this.form = false;
    });
  }

  editProduct(product: Product) {
    this.logger.debug('Modification du produit', product);
    this.productService.editProduct(product).subscribe(() => {
      this.logger.info(`Produit modifié: ${product.idProduit}`);
    });
  }

  deleteProduct(idProduct: any) {
    this.logger.warn(`Suppression du produit ID=${idProduct}`);
    this.productService.deleteProduct(idProduct).subscribe(() => {
      this.logger.info(`Produit supprimé: ID=${idProduct}`);
      this.getAllProducts();
    });
  }

  open(content: any, action: any) {
    this.logger.debug('Ouverture du modal');
    if (action != null) {
      this.product = action;
      this.logger.debug('Produit sélectionné pour modification', action);
    } else {
      this.product = new Product();
      this.logger.debug('Création d’un nouveau produit');
    }

    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      this.logger.debug(`Modal fermé avec: ${result}`);
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      this.logger.debug(`Modal fermé (raison): ${this.closeResult}`);
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'en appuyant sur ÉCHAP';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'en cliquant sur l’arrière-plan';
    } else {
      return `avec: ${reason}`;
    }
  }

  cancel() {
    this.form = false;
    this.logger.info('Annulation de l’action, formulaire masqué');
  }
}
