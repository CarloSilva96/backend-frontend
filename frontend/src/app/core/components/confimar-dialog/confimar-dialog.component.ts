import { Component, Inject } from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ConfirmarDialog} from "../../models/confirmar-dialog.model";

@Component({
  selector: 'app-confimar-dialog',
  templateUrl: './confimar-dialog.component.html',
  styleUrls: ['./confimar-dialog.component.scss']
})
export class ConfimarDialogComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: ConfirmarDialog
  ) { }

}
