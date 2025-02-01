package com.webestoque.webestoque.services;

import com.webestoque.webestoque.entities.Categoria;
import com.webestoque.webestoque.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria createCategory(Categoria categoria) {
        try {
            Optional<Categoria> categoriaOptional = categoriaRepository.findByCategoria(categoria.getCategoria());
            if (categoriaOptional.isPresent()) {
                throw new RuntimeException("Categoria já cadastrada");
            }

            return categoriaRepository.save(categoria);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar categoria: " + e.getMessage());
        }
    };

    public List<Categoria> listCategories(){

        try{
            List<Categoria> categorias = categoriaRepository.findAll();

            return  categorias;
        }catch (Exception e){
            throw new RuntimeException("Erro ao buscar categorias: "+ e.getMessage());
        }
    }

    public Categoria findCategoryByID(Long id){
        try{
            Optional<Categoria> categoria = categoriaRepository.findById(id);

            if(categoria.isPresent()){
                return categoria.get();
            }
            else {
                throw new RuntimeException("Categoria não encontrada com id: "+ id);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro ao buscar categoria: " + e.getMessage());
        }
    };

    public Categoria uptadeCategory(Categoria categoria, Long id) {

        try {
            Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
            if (categoriaOptional.isPresent()) {
                Categoria categoriaAtualizada = categoriaOptional.get();
                categoriaAtualizada.setCategoria(categoria.getCategoria());
                categoriaAtualizada.setStatus(categoria.getStatus());

                return categoriaRepository.save(categoriaAtualizada);
            } else {
                throw new RuntimeException("Categoria não encontrada com id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar categoria: " +e.getMessage());
        }
    };

    public void deleteCategory(Long id){
        try{
            Optional<Categoria> categoria = categoriaRepository.findById(id);

            if(categoria.isPresent()){
                categoriaRepository.deleteById(id);
            }else{
                throw new RuntimeException("Categoria não encontrada");
            }
        }catch (Exception e){
            throw new RuntimeException("Erro ao deletar categoria: " + e.getMessage());
        }
    }
}

