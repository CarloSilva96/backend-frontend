import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from 'src/app/material/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RouterModule } from '@angular/router';
import { ConfimarDialogComponent } from "./components/confimar-dialog/confimar-dialog.component";
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpErrorInterceptor } from './interceptors/http-error.interceptor';
import { PaginaNaoEncontradaComponent } from "./components/pagina-nao-encontrada/pagina-nao-encontrada.component";
import { ToolbarComponent } from "./components/toolbar/toolbar.component";
import { NgxMaskModule } from "ngx-mask";
import { CurrencyPipe } from "@angular/common";

import ptBr from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { DEFAULT_CURRENCY_CODE, LOCALE_ID } from '@angular/core';
import {CURRENCY_MASK_CONFIG, CurrencyMaskConfig, CurrencyMaskModule} from "ng2-currency-mask";
registerLocaleData(ptBr);

export const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
  align: "left",
  allowNegative: false,
  decimal: ',',
  precision: 2,
  prefix: 'R$',
  suffix: '',
  thousands: '.'
}

const COMPONENTS = [
  ConfimarDialogComponent,
  PaginaNaoEncontradaComponent,
  ToolbarComponent
];

const MODULES = [
  MaterialModule,
  FlexLayoutModule,
  RouterModule,
];

@NgModule({
  declarations: [COMPONENTS],
  imports: [
    CommonModule,
    MODULES,
    NgxMaskModule.forChild(),
    CurrencyMaskModule
  ],
  exports: [MODULES, COMPONENTS],
  providers: [
    CurrencyPipe,
    {
      provide: CURRENCY_MASK_CONFIG,
      useValue: CustomCurrencyMaskConfig
    },
    {
      provide: LOCALE_ID, useValue: 'pt'
    },
    {
     provide: DEFAULT_CURRENCY_CODE, useValue: 'BRL'
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    }
  ]
})

export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule?: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule já foi carregado. Já foi importado em AppModule');
    }
  }
}
