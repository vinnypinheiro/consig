import { BrowserModule } from '@angular/platform-browser';
import { NgModule  } from '@angular/core';

import { DataTablesModule } from 'angular-datatables';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxMaskModule} from 'ngx-mask'
import { CurrencyMaskModule } from "ng2-currency-mask";
import { CurrencyMaskConfig, CURRENCY_MASK_CONFIG } from "ng2-currency-mask/src/currency-mask.config";

import {MatFormFieldModule, MatInputModule, MatAutocompleteModule, MatButtonModule} from '@angular/material';

export const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
  align: "right",
  allowNegative: true,
  decimal: ",",
  precision: 2,
  prefix: "R$ ",
  suffix: "",
  thousands: "."
};

import { AppComponent } from './/app.component';
import { AppRoutingModule } from './/app-routing.module';
import { LayoutModule } from './/layouts/layout.module';
import { ScriptLoaderService } from './_services/script-loader.service';

import {SharedModule} from "./shared/shared.module";
import {OcorrenciaModule} from "./entidades/ocorrencia/ocorrencia.module";
import {SearchService} from "./search.service";

//pt-br pipes
import { LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';

registerLocaleData(localePt);

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    DataTablesModule,
    FormsModule,
    AppRoutingModule,
    LayoutModule,
    SharedModule,
    OcorrenciaModule,
    NgxMaskModule.forRoot(),
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot({
      timeOut: 5000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
    }), // ToastrModule added

    ReactiveFormsModule,

    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    MatButtonModule,

    CurrencyMaskModule
  ],
  providers: [      { provide: LOCALE_ID, useValue: 'pt-BR' },
  SearchService,ScriptLoaderService, { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig },],
  bootstrap: [AppComponent],

})
export class AppModule { }
