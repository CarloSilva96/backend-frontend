import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProdutosComponent } from './components/produtos/produtos.component';
import { FormProdutosComponent } from './components/form-produtos/form-produtos.component';

const ROTAS: Routes = [
 { path: '', component: ProdutosComponent },
 { path: ':id', component: FormProdutosComponent }
];

@NgModule({
  imports: [RouterModule.forChild(ROTAS)],
  exports: [RouterModule]
})

export class ProdutosRoutingModule { }
