import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pagina-nao-encontrada',
  template: `
    <mat-card>
      <mat-card-title>404: Página não encontrada!</mat-card-title>
      <mat-card-content>
        Não foi possível encontrar essa página nem com visão de raio-X
      </mat-card-content>
      <mat-card-actions>
        <button mat-raised-button color="primary" [routerLink]="['']" >
          Voltar para o início
        </button>
      </mat-card-actions>
    </mat-card>
  `,
  styles: [`
    :host {
      text-align: center;
    }
  `
  ]
})
export class PaginaNaoEncontradaComponent  {

}
