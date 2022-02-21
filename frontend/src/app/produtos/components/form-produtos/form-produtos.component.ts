import { Component, OnInit } from '@angular/core';
import { Produto } from 'src/app/core/models/produto.model';
import { FormBuilder, Validators } from "@angular/forms";
import { ProdutoService } from 'src/app/core/services/produto.service';
import { ActivatedRoute, Router } from '@angular/router';
import {CurrencyPipe, Location} from "@angular/common";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-details-produtos',
  templateUrl: './form-produtos.component.html',
  styleUrls: ['./form-produtos.component.scss']
})
export class FormProdutosComponent implements OnInit {
  editar = false;
  produto!: Produto;

  formProduto = this.formBuilder.group({
    id: [''],
    nome: ['', [Validators.required, Validators.minLength(3)]],
    categoria: ['', [Validators.required, Validators.minLength(3)]],
    codigoDeBarras: ['', [Validators.required, Validators.minLength(13), Validators.maxLength(13)]],
    preco: [{value: '', disabled: false}]
  });

  constructor(
    private formBuilder: FormBuilder,
    private produtoService: ProdutoService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private snackBar: MatSnackBar,
    private currencyPipe: CurrencyPipe
  ) { }

  ngOnInit(): void {
    this.getProduto();
  }

  getProduto(): void {
    const PARAM_ID = this.route.snapshot.paramMap.get('id');
    if (PARAM_ID !== "new") {
      this.editar = true;
      const ID = Number(PARAM_ID);
      this.produtoService.getProduto(ID).subscribe( produto => {
        this.produto = produto;
        this.formProduto.controls['id'].setValue(produto.id);
        this.formProduto.controls['nome'].setValue(produto.nome);
        this.formProduto.controls['codigoDeBarras'].setValue(produto.codigoDeBarras);
        this.formProduto.controls['categoria'].setValue(produto.categoria);
        this.formProduto.controls['preco'].setValue(produto.preco);
      }, error => {
        this.router.navigate(['/produtos']);
      });
    }
  }


  criarProduto(): void {
    const { valid, value } = this.formProduto;
    if(valid) {
      const PRODUTO: Produto = {
        nome: value.nome,
        codigoDeBarras: value.codigoDeBarras,
        categoria: value.categoria,
        preco: value.preco
      } as Produto

      this.produtoService.criarProduto(PRODUTO).subscribe( produto => {
        this.exibirMensagem("Produto criado com sucesso!");
        this.voltar();
      });
    }
    else {
      this.exibirMensagem("Erros no formulário.")
    }
  }

  atualizarProduto(): void {
    const { valid, value } = this.formProduto;
    if (valid) {
      const PRODUTO: Produto = {
        id: this.produto.id,
        nome: value.nome,
        codigoDeBarras: value.codigoDeBarras,
        categoria: value.categoria,
        preco: value.preco
      }

      this.produtoService.atualizarProduto(PRODUTO).subscribe(() => {
        this.exibirMensagem("Produto Atualizado com sucesso!");
        this.voltar();
      });
    }
  }

  voltar(): void {
    this.location.back();
  }

  exibirMensagem(mensagem: string): void {
    this.snackBar.open(mensagem, 'Ok', {
      duration: 2000,
      verticalPosition: 'top',
    });
  }
}
