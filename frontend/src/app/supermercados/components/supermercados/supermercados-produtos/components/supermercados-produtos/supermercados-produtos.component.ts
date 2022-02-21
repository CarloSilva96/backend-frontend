import { Component, OnInit } from '@angular/core';
import { MatDialog } from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {SupermercadoService} from "../../../../../../core/services/supermercado.service";
import {Supermercado} from "../../../../../../core/models/supermercado.model";
import { async, finalize } from 'rxjs';
import {MatSnackBar} from "@angular/material/snack-bar";
import {SelectProdutosComponent} from "../select-produtos/select-produtos.component";
import {AdicionarEditarProdutosComponent} from "../adicionar-editar-produtos/adicionar-editar-produtos.component";
import {Produto} from "../../../../../../core/models/produto.model";

@Component({
  selector: 'app-supermercados-produtos',
  templateUrl: './supermercados-produtos.component.html',
  styleUrls: ['./supermercados-produtos.component.scss']
})
export class SupermercadosProdutosComponent implements OnInit {
  produtos: Produto[] = [];
  supermercado!: Supermercado;
  atributosSupermercadosProdutos: string[] = ['id', 'nome', 'preco', 'categoria', 'codigoDeBarras', 'editar'];
  ordenar = "id";

  produto!: Produto;

  formSupermercado = this.formBuilder.group({
    id: [{value: '', }],
    nome: [{value: '', disabled: true}],
    telefone: [{value: '', disabled: true}],
    endereco: [{value: '', disabled: true}]
  });

  constructor(
    private formBuilder: FormBuilder,
    private matDialog: MatDialog,
    private route: ActivatedRoute,
    private supermercadoService: SupermercadoService,
    private router: Router,
    private snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    this.getSupermercado();
  }

  getSupermercado(): void {
    const PARAM_ID = this.route.snapshot.paramMap.get('id');
    const ID = Number(PARAM_ID);
    this.supermercadoService.getSupermercado(ID).subscribe( supermercado => {
      this.supermercado = supermercado;
      this.formSupermercado.controls['id'].setValue(supermercado.id);
      this.formSupermercado.controls['nome'].setValue(supermercado.nome);
      this.formSupermercado.controls['endereco'].setValue(supermercado.endereco);
      this.formSupermercado.controls['telefone'].setValue(supermercado.telefone);
      this.getProdutos();
    }, error => {
      this.router.navigate(['/supermercados']);
    });
  }

  getProdutos(): void {
    this.supermercadoService.getProdutos(this.supermercado, this.ordenar).subscribe(
      produtos => this.produtos = produtos
    );
  }

  editarPrecoProduto(produto: Produto): void {
    const dialogEditarPreco = this.matDialog.open(AdicionarEditarProdutosComponent, {
      width: '300px',
      position: {
        top: '100px'
      },
      data: {
        id: produto.id,
        nome: produto.nome,
        categoria: produto.categoria,
        preco: produto.preco,
        codigoDeBarras: produto.codigoDeBarras
      }
    });

    dialogEditarPreco.afterClosed()
      .pipe(finalize(() => {}))
      .subscribe( produto => {
        this.supermercadoService.atualizarProduto(this.supermercado, produto).subscribe( () => {
          this.exibirMensagem("Produto atualizado!");
          this.getProdutos();
        }
        )
      });
  }

  adicionarProduto(): void {
    const addProduto = this.matDialog.open(SelectProdutosComponent, {
      position: {
         top: '100px'
      },
      data: {
        id: this.supermercado.id
      },
      panelClass: 'my-custom-dialog-class'
    });

    addProduto.componentInstance.submitProdutoAdicionado.subscribe( () => {
      this.getProdutos();
    });

    addProduto.afterClosed()
      .pipe(finalize(() => {}))
      .subscribe( () => {
            this.getProdutos();
          }
      );
  }

  exibirMensagem(mensagem: string): void {
    this.snackBar.open(mensagem, 'Ok', {
      duration: 3000,
      verticalPosition: 'top',
    });
  }

}
