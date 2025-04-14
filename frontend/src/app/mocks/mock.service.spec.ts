import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { StockService } from '../services/stock.service';
import { MockService } from '../mocks/mock.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MockNgbModal } from '../mocks/mock-ngb-modal.service';
import {StockComponent} from "../stock/stock.component";

describe('StocksComponent', () => {
  let component: StockComponent;
  let fixture: ComponentFixture<StockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StockComponent],
      imports: [HttpClientTestingModule],
      providers: [
        { provide: StockService, useClass: MockService },
        { provide: NgbModal, useClass: MockNgbModal }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
