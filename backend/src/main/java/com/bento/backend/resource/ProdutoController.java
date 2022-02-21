package com.bento.backend.resource;

import com.bento.backend.domain.DTO.ProdutoDTO;
import com.bento.backend.domain.DTO.ProdutoUpdateDTO;
import com.bento.backend.domain.Produto;
import com.bento.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> find(@PathVariable(value = "id") Integer id) {
        ProdutoDTO produtoDTO = new ProdutoDTO(produtoService.findId(id));
        return ResponseEntity.ok().body(produtoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAllProdutos(@RequestParam(value = "ordenar", defaultValue = "") String ordenar) {

        List<ProdutoDTO> produtos = produtoService.findAllProdutos(ordenar);

        return ResponseEntity.ok().body(produtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@RequestBody @Valid ProdutoDTO produtoDTO) {
        Produto produto = produtoService.converterParaProduto(produtoDTO);
        produtoDTO = produtoService.insert(produto);
        return ResponseEntity.ok().body(produtoDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@RequestBody @Valid ProdutoUpdateDTO produtoUpdateDTO, @PathVariable(value = "id") Integer id) {
        Produto produto = produtoService.converterParaProduto(produtoUpdateDTO);
        produto.setId(id);
        ProdutoDTO produtoDTO = produtoService.update(produto);
        return ResponseEntity.ok().body(produtoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> destroy(@PathVariable(value = "id") Integer id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
