import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PaginaNaoEncontradaComponent} from "./core/components/pagina-nao-encontrada/pagina-nao-encontrada.component";
import {AppComponent} from "./app.component";

const ROTAS: Routes = [
  {
    path: '', redirectTo: '/supermercados',  pathMatch: 'full'
  },
  {
    path: 'supermercados',
    loadChildren: () => import('./supermercados/supermercados.module').then((modulo) => (modulo.SupermercadosModule))
  },
  {
    path: 'produtos',
    loadChildren: () => import('./produtos/produtos.module').then((modulo) => (modulo.ProdutosModule))
  },
  {
    path: '**',
    component: PaginaNaoEncontradaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(ROTAS)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
