package com.webestoque.webestoque.controllers;

import com.webestoque.webestoque.entities.Produto;
import com.webestoque.webestoque.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/cria-produto")
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto){
        try{
            Produto newProduct = produtoService.createProduct(produto);

            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarProdutos(){
        try{
            List<Produto> produtos = produtoService.getAllProdutos();
            return ResponseEntity.status(HttpStatus.OK).body(produtos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  atualizarProduto(@RequestBody Produto produto, @PathVariable Long id){
        try{
            Produto produtoAtualizado = produtoService.updateProdutoById(produto, id);
            return ResponseEntity.status(HttpStatus.OK).body(produtoAtualizado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaProduto(@PathVariable Long id){
        try{
            produtoService.deleteProdutoByID(id);
            return ResponseEntity.status(HttpStatus.OK).body("");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao apagar produto: " +e.getMessage());
        }
    }
}
