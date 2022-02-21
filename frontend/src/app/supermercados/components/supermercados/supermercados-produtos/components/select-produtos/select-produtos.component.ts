import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {SupermercadoService} from "../../../../../../core/services/supermercado.service";
import {Supermercado} from "../../../../../../core/models/supermercado.model";
import { async, finalize } from 'rxjs';
import {MatSnackBar} from "@angular/material/snack-bar";
import {Produto} from "../../../../../../core/models/produto.model";
import {ProdutoService} from "../../../../../../core/services/produto.service";
import {Location} from "@angular/common";
import {ProdutoAdicionar} from "../../../../../../core/models/produto-adicionar.model";
import {AdicionarEditarProdutosComponent} from "../adicionar-editar-produtos/adicionar-editar-produtos.component";
@Component({
  selector: 'app-select-produtos',
  templateUrl: './select-produtos.component.html',
  styleUrls: ['./select-produtos.component.scss']
})
export class SelectProdutosComponent implements OnInit {
  @Output() submitProdutoAdicionado = new EventEmitter<any>();
  produtos: Produto[] = [];
  atributosProdutos: string[] = ['id', 'nome', 'categoria', 'adicionar'];
  ordenar = "id";
  produtoAdicionar!: ProdutoAdicionar;

  constructor(
    private snackBar: MatSnackBar,
    private matDialog: MatDialog,
    private supermercadoService: SupermercadoService,
    private location: Location,
    private produtoService: ProdutoService,
    public dialogRef: MatDialogRef<SelectProdutosComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Supermercado
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

  adicionarProduto(produto: Produto): void {
    this.produtoAdicionar = {
      idSupermercado: this.data.id,
      idProduto: produto.id,
      preco: 0
    }

    const addProduto = this.matDialog.open(AdicionarEditarProdutosComponent, {
      width: '300px',
      position: {
        top: '100px'
      },
      data: {
        idProduto: this.produtoAdicionar.idProduto,
        idSupermercado: this.produtoAdicionar.idSupermercado,
        preco: this.produtoAdicionar.preco
      }
    });

    addProduto.afterClosed()
      .pipe(finalize(() => {}))
      .subscribe(produtoAdicionar => {
        this.produtoAdicionar = produtoAdicionar;
        this.supermercadoService.adicionarProduto(this.produtoAdicionar)
          .pipe(finalize(() => {}))
          .subscribe( () => {
            this.exibirMensagem("Produto Adicionado ComSucesso!");
            this.submitProdutoAdicionado.emit();
        })
    })
  }

  exibirMensagem(mensagem: string): void {
    this.snackBar.open(mensagem, 'Ok', {
      duration: 3000,
      verticalPosition: 'top',
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  voltar(): void {
    this.location.back();
  }
}
