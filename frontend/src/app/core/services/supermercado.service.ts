import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import {Supermercado} from "../models/supermercado.model";
import { Produto } from "../models/produto.model";
import {ProdutoAdicionar} from "../models/produto-adicionar.model";
import { environment } from "src/environments/environment.prod";

@Injectable({
  providedIn: 'root'
})

export class SupermercadoService {
  supermercados: Supermercado[] = [];

  private apiUrlSupermercado = `${environment.baseUrl}/supermercados`;

  constructor (
    private httpCliente: HttpClient
  ) {}

  criarSupermercado(supermercado: Supermercado): Observable<Supermercado> {
    return this.httpCliente.post<Supermercado>(this.apiUrlSupermercado, supermercado);
  }

  getSupermercado(id: number): Observable<Supermercado> {
    return this.httpCliente.get<Supermercado>(this.getUrl(id));
  }

  getSupermercados(param: string): Observable<Supermercado[]> {
    const ordernarPor = (param === "nome") ? param : "";
    return this.httpCliente.get<Supermercado[]>(this.apiUrlSupermercado, {params: {ordenar: ordernarPor} });
  }

  atualizarSupermercado(supermercado: Supermercado): Observable<Supermercado> {
    return this.httpCliente.put<Supermercado>(this.getUrl(supermercado.id), supermercado);
  }

  excluirSupermercado(supermercado: Supermercado): Observable<any> {
    return this.httpCliente.delete<any>(this.getUrl(supermercado.id));
  }

  getProdutos(supermercado: Supermercado, param: string): Observable<Produto[]> {
    const ordernarPor = (param === "nome") ? param : "";
    return this.httpCliente.get<Produto[]>( `${this.getUrl(supermercado.id)}/produtos`, {params: {ordenar: ordernarPor} } )
  }

  atualizarProduto(supermercado: Supermercado, produto: Produto): Observable<Produto> {
    const url = this.getUrlUpdateProduto(supermercado, produto);
    return this.httpCliente.put<Produto>(url, produto);
  }

  adicionarProduto(produtoAdicionar: ProdutoAdicionar): Observable<string> {
    const url = this.getUrl(produtoAdicionar.idSupermercado) + "/produtos";
    return this.httpCliente.post<string>(url, produtoAdicionar);
  }

  private getUrl(id: Number): string {
    return `${this.apiUrlSupermercado}/${id}`
  }

  private getUrlUpdateProduto(supermercado: Supermercado, produto: Produto): string {
    return `${this.apiUrlSupermercado}/${supermercado.id}/produtos/${produto.id}`;
  }
}
