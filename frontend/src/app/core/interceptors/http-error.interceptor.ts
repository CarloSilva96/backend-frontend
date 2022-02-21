import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { MatDialog } from "@angular/material/dialog";
import { ConfirmarDialog } from "../models/confirmar-dialog.model";
import { ConfimarDialogComponent } from "../components/confimar-dialog/confimar-dialog.component";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(
    private errorBackEnd: MatDialog
  ) {}

  private exibirErro(msg: string): void {
    const DIALOG_DADOS: ConfirmarDialog =  {
      cancelarTexto: "",
      confirmarTexto: "Cancelar",
      conteudo: msg
    }

    this.errorBackEnd.open(ConfimarDialogComponent, {
      position: {
        top: '50px'
      },
      data: DIALOG_DADOS,
    });
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => {
        let errorMsg = '';

        if (err.error.errors){
          let errosApi = err.error.errors;
          let msgs = '';
          for (let i = 0; i < errosApi.length; i++ ) {
            msgs += ` ${errosApi[i].mensagem}`;
          }
          this.exibirErro(msgs);
        }
        else if (err.error.msg) {
          this.exibirErro(err.error.msg)
        }
        else if (err.error instanceof ErrorEvent) {
          this.exibirErro(err.error.message);
        }
        return throwError(() => new Error(errorMsg))
      })
    );
  }
}
