import { Component, OnInit } from '@angular/core';
import { Produto } from 'src/app/core/models/produto.model';
import { ProdutoService } from 'src/app/core/services/produto.service';
import { ConfirmarDialog } from 'src/app/core/models/confirmar-dialog.model';
import { ConfimarDialogComponent } from 'src/app/core/components/confimar-dialog/confimar-dialog.component';
import { MatDialog } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {
  produtos: Produto[] = [];
  atributosProdutos: string[] = ['id', 'nome', 'categoria', 'preco', 'codigoDeBarras', 'editar', 'excluir'];
  ordenar = "id";

  constructor(
    private produtoService: ProdutoService,
    private matDialog: MatDialog,
    private snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    this.setProdutos();
  }

  setProdutos(): void {
    this.produtoService.getProdutos(this.ordenar).subscribe( produtos => {
      this.produtos = produtos
    }
    );
  }

  excluirProduto(produto: Produto): void {
    const DIALOG_DADOS: ConfirmarDialog =  {
      cancelarTexto: "Cancelar",
      confirmarTexto: "Confirmar",
      conteudo: `Deseja realmente excluir o ${produto.nome}?`
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
        this.produtoService.excluirProduto(produto).subscribe(() => {
          this.exibirMensagem("Produto excluído com sucesso!")
          this.setProdutos();
        });
      }
    });
  }

  exibirMensagem(mensagem: string): void {
    this.snackBar.open(mensagem, 'Ok', {
      duration: 3000,
      verticalPosition: 'top',
    });
  }

}
