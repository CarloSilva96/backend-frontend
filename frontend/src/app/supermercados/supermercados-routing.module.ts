import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SupermercadosComponent} from "./components/supermercados/supermercados.component";
import {FormDetailsSupermercadosComponent} from "./components/form-details-supermercados/form-details-supermercados.component";
import {SupermercadosProdutosComponent} from "./components/supermercados/supermercados-produtos/components/supermercados-produtos/supermercados-produtos.component";

const ROTAS: Routes = [
 { path: '', component: SupermercadosComponent },
 { path: ':id', component: FormDetailsSupermercadosComponent },
 { path: ':id/produtos', component: SupermercadosProdutosComponent },
 { path: ':id/produtos/:produto-id' }
];

@NgModule({
  imports: [RouterModule.forChild(ROTAS)],
  exports: [RouterModule]
})

export class SupermercadosRoutingModule { }
