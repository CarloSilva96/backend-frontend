import { Component, OnInit } from '@angular/core';
import {Produto} from "../../../core/models/produto.model";
import {Supermercado} from "../../../core/models/supermercado.model";
import {FormBuilder, Validators} from "@angular/forms";
import {SupermercadoService} from "../../../core/services/supermercado.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-form-details-supermercados',
  templateUrl: './form-details-supermercados.component.html',
  styleUrls: ['./form-details-supermercados.component.scss']
})
export class FormDetailsSupermercadosComponent implements OnInit {
  editar = false;
  supermercado!: Supermercado;

  formSupermercado = this.formBuilder.group({
    id: [{value: ''}],
    nome: ['', [Validators.required, Validators.minLength(3)]],
    telefone: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(11)]],
    endereco: ['', [Validators.required, Validators.minLength(10)]]
  });

  constructor(
    private formBuilder: FormBuilder,
    private supermercadoService: SupermercadoService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    this.getSupermercado();
  }

  getSupermercado(): void {
    const PARAM_ID = this.route.snapshot.paramMap.get('id');
    if (PARAM_ID !== "new") {
      this.editar = true;
      const ID = Number(PARAM_ID);
      this.supermercadoService.getSupermercado(ID).subscribe( supermercado => {
        this.supermercado = supermercado;
        this.formSupermercado.controls['id'].setValue(supermercado.id);
        this.formSupermercado.controls['nome'].setValue(supermercado.nome);
        this.formSupermercado.controls['endereco'].setValue(supermercado.endereco);
        this.formSupermercado.controls['telefone'].setValue(supermercado.telefone);
      }, error => {
        this.router.navigate(['/supermercados']);
      });
    }
  }

  criarSupermercado(): void {
    const { valid, value } = this.formSupermercado;
    if(valid) {
      const SUPERMERCADO: Supermercado = {
        nome: value.nome,
        endereco: value.endereco,
        telefone: value.telefone
      } as Supermercado

      this.supermercadoService.criarSupermercado(SUPERMERCADO).subscribe( produto => {
        this.exibirMensagem("Supermercado criado com sucesso!");
        this.voltar();
      });
    }
    else {
      this.exibirMensagem("Erros no formulário.")
    }
  }

  atualizarSupermercado(): void {
    const { valid, value } = this.formSupermercado;
    if (valid) {
      const SUPERMERCADO: Supermercado = {
        id: this.supermercado.id,
        nome: value.nome,
        endereco: value.endereco,
        telefone: value.telefone
      }

      this.supermercadoService.atualizarSupermercado(SUPERMERCADO).subscribe(() => {
        this.exibirMensagem("Supermercado Atualizado com sucesso!");
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
