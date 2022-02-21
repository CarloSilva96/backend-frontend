package com.bento.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "supermercados_produtos")
public class SupermercadoProduto implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SupermercadoProdutoPK id = new SupermercadoProdutoPK();

    @Column(nullable = false)
    private Double precoProduto;

    public SupermercadoProduto() {}

    public SupermercadoProduto(Supermercado supermercado, Produto produto, Double precoProduto) {
        id.setSupermercado(supermercado);
        id.setProduto(produto);
        this.precoProduto = precoProduto;
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setSupermercado(Supermercado supermercado) {
        id.setSupermercado(supermercado);
    }

    @JsonIgnore
    public Supermercado getSupermercado() {
        return id.getSupermercado();
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupermercadoProduto that = (SupermercadoProduto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, precoProduto);
    }
}
