import { of } from 'rxjs';

export class MockProductService {
  getAllProducts() {
    return of([
      {
        idProduit: 1,
        codeProduit: 'P001',
        libelleProduit: 'Mock Product 1',
        prix: 50,
        dateCreation: new Date(),
        dateDerniereModification: new Date()
      },
      {
        idProduit: 2,
        codeProduit: 'P002',
        libelleProduit: 'Mock Product 2',
        prix: 100,
        dateCreation: new Date(),
        dateDerniereModification: new Date()
      }
    ]);
  }
}
