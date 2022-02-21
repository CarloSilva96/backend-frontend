import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProdutosComponent } from './components/produtos/produtos.component';
import { FormProdutosComponent } from './components/form-produtos/form-produtos.component';
import { MaterialModule } from '../material/material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ProdutosRoutingModule } from './produtos-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import {NgxMaskModule} from "ngx-mask";
import {CurrencyMaskModule} from "ng2-currency-mask";

@NgModule({
  declarations: [
    ProdutosComponent,
    FormProdutosComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    ProdutosRoutingModule,
    FlexLayoutModule,
    FormsModule,
    NgxMaskModule,
    CurrencyMaskModule
  ]
})
export class ProdutosModule { }
