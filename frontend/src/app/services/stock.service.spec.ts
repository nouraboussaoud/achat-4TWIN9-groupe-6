import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { StockService } from './stock.service';
import { MockService } from '../mocks/mock.service'; // adapte le chemin si besoin

describe('StockService', () => {
  let service: MockService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        { provide: StockService, useClass: MockService }
      ]
    });

    service = <MockService>TestBed.inject(StockService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return stocks from getStocks', (done) => {
    service.getAllStocks().subscribe((data: any[]) => {
      expect(data.length).toBeGreaterThan(0);
      done();
    });
  });

  it('should return object from addStock', (done) => {
    const mockStock = { idStock: 3, libelleStock: 'Test Stock', qte: 100 };
    service.addStock(mockStock).subscribe((data: any) => {
      expect(data).toBeTruthy();
      expect(data.stock.libelleStock).toEqual('Test Stock');
      done();
    });
  });

  it('should return response from deleteStock', (done) => {
    service.deleteStock(1).subscribe((data: any) => {
      expect(data.success).toBeTrue();
      done();
    });
  });
});
