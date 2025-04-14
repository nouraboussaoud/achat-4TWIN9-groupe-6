import { of } from 'rxjs';

export class MockService {
  getAllStocks() {
    return of([
      { idStock: 1, libelleStock: 'Stock 1', qte: 100 },
      { idStock: 2, libelleStock: 'Stock 2', qte: 200 }
    ]);
  }

  addStock(stock: any) {
    return of({ success: true, stock });
  }

  deleteStock(id: number) {
    return of({ success: true });
  }
}
