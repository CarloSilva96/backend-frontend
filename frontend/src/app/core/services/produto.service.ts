import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Produto } from "../models/produto.model";

@Injectable({
  providedIn: 'root'
})

export class ProdutoService {
  produtos: Produto[] = [];

  private apiUrlProduto = `${environment.baseUrl}/produtos`;

  constructor (
    private httpCliente: HttpClient
  ) {}

  criarProduto(produto: Produto): Observable<Produto> {
    return this.httpCliente.post<Produto>(this.apiUrlProduto, produto);
  }

  atualizarProduto(produto: Produto): Observable<Produto> {
    return this.httpCliente.put<Produto>(this.getUrl(produto.id), produto);
  }

  getProduto(id: number): Observable<Produto> {
    return this.httpCliente.get<Produto>(this.getUrl(id));
  }

  getProdutos(param: string): Observable<Produto[]> {
    const ordernarPor = (param === "nome") ? param : "";
    return this.httpCliente.get<Produto[]>(this.apiUrlProduto, {params: {ordenar: ordernarPor} });
  }

  excluirProduto(produto: Produto): Observable<any> {
    return this.httpCliente.delete<any>(this.getUrl(produto.id));
  }

  private getUrl(id: Number): string {
    return `${this.apiUrlProduto}/${id}`
  }
}
