import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MockService } from '../mocks/mock.service';
import { StockService } from '../shared/Service/Stock.service';

// Define interface for your response structure
interface StockResponse {
  stock?: {
    idStock: number;
    libelleStock: string;
    qte: number;
  };
  success?: boolean;
}

describe('StockService', () => {
  let service: StockService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        { provide: StockService, useClass: MockService }
      ]
    });

    service = TestBed.inject(StockService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return stocks from getStocks', (done) => {
    service.getAllStocks().subscribe((data: any) => {
      expect(data.length).toBeGreaterThan(0);
      done();
    });
  });

  it('should return object from addStock', (done) => {
    const mockStock = { idStock: 3, libelleStock: 'Test Stock', qte: 100 };
    service.addStock(mockStock).subscribe((data: StockResponse) => {
      expect(data).toBeTruthy();
      expect(data.stock?.libelleStock).toEqual('Test Stock');
      done();
    });
  });

  it('should return response from deleteStock', (done) => {
    service.deleteStock(1).subscribe((data: StockResponse) => {
      expect(data.success).toBeTrue();
      done();
    });
  });
});
