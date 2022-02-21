import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { Location } from "@angular/common";
import { ProdutoAdicionar } from "../../../../../../core/models/produto-adicionar.model";

@Component({
  selector: 'app-adicionar-produtos',
  templateUrl: './adicionar-editar-produtos.component.html',
  styleUrls: ['./adicionar-editar-produtos.component.scss']
})

export class AdicionarEditarProdutosComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<AdicionarEditarProdutosComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ProdutoAdicionar
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    console.log(this.data);
  }

}
