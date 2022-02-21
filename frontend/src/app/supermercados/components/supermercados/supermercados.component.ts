import { Component, OnInit } from '@angular/core';
import {Supermercado} from "../../../core/models/supermercado.model";
import {MatDialog} from "@angular/material/dialog";
import {SupermercadoService} from "../../../core/services/supermercado.service";
import {ConfirmarDialog} from "../../../core/models/confirmar-dialog.model";
import {ConfimarDialogComponent} from "../../../core/components/confimar-dialog/confimar-dialog.component";

@Component({
  selector: 'app-supermercados',
  templateUrl: './supermercados.component.html',
  styleUrls: ['./supermercados.component.scss']
})
export class SupermercadosComponent implements OnInit {
  supermercados: Supermercado[] = [];
  atributosSupermercados: string[] = ['id', 'nome', 'telefone', 'endereco', 'visualizar','editar', 'excluir'];
  ordenar = "id";

  constructor(
    private matDialog: MatDialog,
    private supermercadoService: SupermercadoService
  ) { }

  ngOnInit(): void {
    this.setSupermercados();
  }

  setSupermercados(): void {
    this.supermercadoService.getSupermercados(this.ordenar).subscribe(
      supermercados => this.supermercados = supermercados
    );
  }

  excluirSupermercado(supermercado: Supermercado): void {
    const DIALOG_DADOS: ConfirmarDialog =  {
      cancelarTexto: "Cancelar",
      confirmarTexto: "Confirmar",
      conteudo: `Deseja realmente excluir o ${supermercado.nome}?`
    }

    const DIALOG_REFERENCIA = this.matDialog.open(ConfimarDialogComponent, {
      position: {
        top: '50px'
      },
      data: DIALOG_DADOS,
      width: '400px'
    });

    DIALOG_REFERENCIA.afterClosed().subscribe(resultado => {
      if (resultado) {
        this.supermercadoService.excluirSupermercado(supermercado).subscribe(() => {
          this.setSupermercados();
        });
      }
    });
  }
}
