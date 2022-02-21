import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SupermercadosRoutingModule } from './supermercados-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SupermercadosComponent} from "./components/supermercados/supermercados.component";
import { FormDetailsSupermercadosComponent } from "./components/form-details-supermercados/form-details-supermercados.component";
import { SupermercadosProdutosComponent } from "./components/supermercados/supermercados-produtos/components/supermercados-produtos/supermercados-produtos.component";
import { SelectProdutosComponent} from "./components/supermercados/supermercados-produtos/components/select-produtos/select-produtos.component";
import { AdicionarEditarProdutosComponent } from "./components/supermercados/supermercados-produtos/components/adicionar-editar-produtos/adicionar-editar-produtos.component";
import { NgxMaskModule } from "ngx-mask";
import { CurrencyMaskModule } from "ng2-currency-mask";

@NgModule({
  declarations: [
    SupermercadosComponent,
    FormDetailsSupermercadosComponent,
    SupermercadosProdutosComponent,
    SelectProdutosComponent,
    AdicionarEditarProdutosComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    SupermercadosRoutingModule,
    FlexLayoutModule,
    FormsModule,
    NgxMaskModule,
    CurrencyMaskModule
  ]
})
export class SupermercadosModule { }
