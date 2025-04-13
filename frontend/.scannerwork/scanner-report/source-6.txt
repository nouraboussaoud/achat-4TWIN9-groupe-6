import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';

import { ProductsComponent } from './products/products.component';
import { StockComponent } from './stock/stock.component';
import { ReglementComponent } from './reglement/reglement.component';
import { SecteurActiviteComponent } from './secteur-activite/secteur-activite.component';
import { OperateurComponent } from './operateur/operateur.component';
import { FactureComponent } from './facture/facture.component';
import { NavbarComponent } from './navbar/navbar.component';

import { LoggerModule, NgxLoggerLevel } from 'ngx-logger'; // ✅ Ajout ngx-logger

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    StockComponent,
    ReglementComponent,
    SecteurActiviteComponent,
    OperateurComponent,
    FactureComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModalModule,
    RouterModule,
    NgbModule,
    LoggerModule.forRoot({                           // ✅ Configuration du logger
      level: NgxLoggerLevel.DEBUG,
      serverLogLevel: NgxLoggerLevel.ERROR,
      serverLoggingUrl: 'http://localhost:8080/api/logs' // Tu peux le créer plus tard
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
