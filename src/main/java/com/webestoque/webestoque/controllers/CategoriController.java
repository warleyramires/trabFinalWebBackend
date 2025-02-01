package com.webestoque.webestoque.controllers;

import com.webestoque.webestoque.entities.Categoria;

import com.webestoque.webestoque.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/cria-categoria")
    public ResponseEntity<?> createCategoria(@RequestBody Categoria categoria){
        try{
            Categoria newCategoria = categoriaService.createCategory(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories(){
        try{
            List<Categoria> listCategories = categoriaService.listCategories();
            return ResponseEntity.status(HttpStatus.OK).body(listCategories);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        try{
            Categoria categoria = categoriaService.findCategoryByID(id);
            return ResponseEntity.status(HttpStatus.OK).body(categoria);
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> uptadeCategoryById(@RequestBody Categoria categoria, @PathVariable Long id){
        try{
            Categoria updatedCategory = categoriaService.uptadeCategory(categoria, id);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        try{
            categoriaService.deleteCategory(id);
        }catch (Exception e){
            throw new RuntimeException("Erro ao excluir categoria de id: " + id + e.getMessage());
        }
    }

}
