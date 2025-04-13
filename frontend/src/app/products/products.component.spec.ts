import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ProductsComponent } from './products.component';
import { ProductService } from '../services/product.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MockProductService } from '../mocks/mock-product.service';
import { NGXLogger, NgxLoggerLevel, INGXLoggerConfig, TOKEN_LOGGER_CONFIG } from 'ngx-logger';

// Create a MockNgbModal class
export class MockNgbModal {
  open() {
    return {
      result: Promise.resolve(true)
    };
  }
}

// Create a mock logger
export class MockNGXLogger {
  debug() {}
  info() {}
  log() {}
  warn() {}
  error() {}
  fatal() {}
  trace() {}
}

// Create a mock logger config
const mockLoggerConfig: INGXLoggerConfig = {
  level: NgxLoggerLevel.DEBUG,
  serverLogLevel: NgxLoggerLevel.OFF
};

describe('ProductsComponent', () => {
  let component: ProductsComponent;
  let fixture: ComponentFixture<ProductsComponent>;
  let productService: ProductService;
  let modalService: NgbModal;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProductsComponent],
      imports: [HttpClientTestingModule],
      providers: [
        { provide: ProductService, useClass: MockProductService },
        { provide: NgbModal, useClass: MockNgbModal },
        { provide: NGXLogger, useClass: MockNGXLogger },
        { provide: TOKEN_LOGGER_CONFIG, useValue: mockLoggerConfig }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductsComponent);
    component = fixture.componentInstance;
    productService = TestBed.inject(ProductService);
    modalService = TestBed.inject(NgbModal);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});