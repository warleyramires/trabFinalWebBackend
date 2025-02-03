package com.webestoque.webestoque.controllers;

import com.webestoque.webestoque.entities.Fornecedor;
import com.webestoque.webestoque.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping("/cria-fornecedor")
    public ResponseEntity<?> registerFornecedor(@RequestBody Fornecedor fornecedor){
        try{
            Fornecedor newFornecedor = fornecedorService.createFornecedor(fornecedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(newFornecedor);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    };

    @GetMapping
    public ResponseEntity<?> getAllFornecedores(){
        try{
            List<Fornecedor> list = fornecedorService.getAllForneceroes();

            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
           Fornecedor fornecedor = fornecedorService.getFornecedorById(id);

           return ResponseEntity.status(HttpStatus.OK).body(fornecedor);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFornecedorByID(@RequestBody Fornecedor fornecedor, @PathVariable Long id){
        try{
            Fornecedor fornecedorAtualizado = fornecedorService.updateFornecedorById(fornecedor, id);

            return  ResponseEntity.status(HttpStatus.OK).body(fornecedorAtualizado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFornecedorByID(@PathVariable Long id){

        try {
            fornecedorService.deleteByID(id);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
