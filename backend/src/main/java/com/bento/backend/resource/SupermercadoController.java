package com.bento.backend.resource;

import com.bento.backend.domain.DTO.*;
import com.bento.backend.domain.Supermercado;
import com.bento.backend.domain.SupermercadoProduto;
import com.bento.backend.service.SupermercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("supermercados")
public class SupermercadoController {
    @Autowired
    private SupermercadoService supermercadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SupermercadoDTO> find(@PathVariable(value = "id") Integer id) {
        SupermercadoDTO supermercadoDTO = new SupermercadoDTO(supermercadoService.findId(id));
        return ResponseEntity.ok().body(supermercadoDTO);
    }

    @GetMapping
    public ResponseEntity<List<SupermercadoDTO>> findAllSupermercados(@RequestParam(value = "ordenar", defaultValue = "") String ordenar) {
        List<SupermercadoDTO> supermercados = supermercadoService.findAllSupermercado(ordenar);
        return ResponseEntity.ok().body(supermercados);
    }

    @PostMapping
    public ResponseEntity<SupermercadoDTO> create(@RequestBody @Valid SupermercadoDTO supermercadoDTO) {
        Supermercado supermercado = supermercadoService.converterParaSupermercado(supermercadoDTO);
        supermercadoDTO = supermercadoService.insert(supermercado);
        return ResponseEntity.ok().body(supermercadoDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SupermercadoDTO> update(@RequestBody @Valid SupermercadoUpdateDTO supermercadoUpdateDTO, @PathVariable(value = "id") Integer id) {
        Supermercado supermercado = supermercadoService.converterParaSupermercado(supermercadoUpdateDTO);
        supermercado.setId(id);
        SupermercadoDTO supermercadoDTO = supermercadoService.update(supermercado);
        return ResponseEntity.ok().body(supermercadoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> destroy(@PathVariable(value = "id") Integer id){
        supermercadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** Produtos Supermercados **/

    @GetMapping(value = "/{id}/produtos/{produto-id}")
    public ResponseEntity<SupermercadoProdutoViewDTO> findSupermercadoProduto(@PathVariable(value = "id") Integer id,
                                                                    @PathVariable(value = "produto-id") Integer produtoId) {
        SupermercadoProduto supermercadoProduto = supermercadoService.findProduto(id, produtoId);
        SupermercadoProdutoViewDTO spvDTO = new SupermercadoProdutoViewDTO(supermercadoProduto);
        return ResponseEntity.ok().body(spvDTO);
    }

    @PostMapping(value = "/{id}/produtos")
    public ResponseEntity<Void> addProdutoSupermercado(@RequestBody @Valid SupermercadoProdutoDTO supermercadoProdutoDTO) {
        SupermercadoProduto sp = supermercadoService.insertProduto(supermercadoProdutoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(sp.getProduto().getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}/produtos/{produto-id}")
    public ResponseEntity<SupermercadoProdutoViewDTO> updateProduto(@RequestBody @Valid SupermercadoProdutoUpdateDTO spuDTO,
                                                                    @PathVariable(value = "id") Integer id,
                                                                    @PathVariable(value = "produto-id") Integer produtoId) {
        SupermercadoProduto supermercadoProduto = supermercadoService.updateProdutoSupermercado(id, produtoId, spuDTO);
        SupermercadoProdutoViewDTO spvDTO = new SupermercadoProdutoViewDTO(supermercadoProduto);
        return ResponseEntity.ok().body(spvDTO);
    }

    @GetMapping(value = "/{id}/produtos")
    public ResponseEntity<List<SupermercadoProdutoViewDTO>> findAllProdutos(@PathVariable(value = "id") Integer id,
                                                                     @RequestParam(value = "ordenar", defaultValue = "") String ordenar) {
        List<SupermercadoProdutoViewDTO> produtosCadastrados = supermercadoService.findAllProdutosSupermercado(id, ordenar);
        return ResponseEntity.ok().body(produtosCadastrados);
    }

}
