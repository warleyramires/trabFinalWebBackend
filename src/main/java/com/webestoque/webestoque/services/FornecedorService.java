package com.webestoque.webestoque.services;

import com.webestoque.webestoque.entities.Fornecedor;
import com.webestoque.webestoque.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor createFornecedor(Fornecedor fornecedor){

        Optional<Fornecedor> fornecedorPorNome = fornecedorRepository.findByNomeFornecedor(fornecedor.getNomeFornecedor());
        Optional<Fornecedor> fornecedorPorEmail = fornecedorRepository.findByEmailFornecedor(fornecedor.getEmailFornecedor());
        Optional<Fornecedor> fornecedorPorCnpj = fornecedorRepository.findByCnpj(fornecedor.getCnpj());

        try{
            if(fornecedorPorEmail.isPresent()){
                throw new RuntimeException("Já existe um fornecedor com este email");
            }
            if(fornecedorPorNome.isPresent()){
                throw new RuntimeException("Já existe um fornecedor com este nome.");
            }
            if(fornecedorPorCnpj.isPresent()){
                throw new RuntimeException("Já existe um fornecedor com este cnpj.");
            }

            return fornecedorRepository.save(fornecedor);

        }catch (Exception e){
            throw new RuntimeException("Erro ao cadastrar fornecedor: "+ e.getMessage());
        }

    }

    public List<Fornecedor> getAllForneceroes(){
        try{
            List<Fornecedor> listFornecedores = fornecedorRepository.findAll();

            return  listFornecedores;
        }catch (Exception e ){
            throw new RuntimeException("Erro ao buscar fornecedores: " + e.getMessage());
        }
    };

    public Fornecedor getFornecedorById(Long id){
        try{
            Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);

            if(fornecedor.isPresent()){
                return fornecedor.get();
            }else{
                throw new RuntimeException("Fornecedor não encontrado com id: " + id);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro ao buscar fornecedor de ID: "+ id + " " + e.getMessage());
        }
    };

    public Fornecedor updateFornecedorById(Fornecedor fornecedor, Long id) {
        Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(id);

        if (optionalFornecedor.isEmpty()) {
            throw new RuntimeException("Fornecedor não encontrado. ID: " + id);
        }

        Fornecedor updateFornecedor = optionalFornecedor.get();

        Optional<Fornecedor> fornecedorPorEmail = fornecedorRepository.findByEmailFornecedor(fornecedor.getEmailFornecedor());
        if (fornecedorPorEmail.isPresent() && !fornecedorPorEmail.get().getId().equals(id)) {
            throw new RuntimeException("Já existe um fornecedor com este email.");
        }

        Optional<Fornecedor> fornecedorPorNome = fornecedorRepository.findByNomeFornecedor(fornecedor.getNomeFornecedor());
        if (fornecedorPorNome.isPresent() && !fornecedorPorNome.get().getId().equals(id)) {
            throw new RuntimeException("Já existe um fornecedor com este nome.");
        }

        Optional<Fornecedor> fornecedorPorCnpj = fornecedorRepository.findByCnpj(fornecedor.getCnpj());
        if (fornecedorPorCnpj.isPresent() && !fornecedorPorCnpj.get().getId().equals(id)) {
            throw new RuntimeException("Já existe um fornecedor com este CNPJ.");
        }

        updateFornecedor.setNomeFornecedor(fornecedor.getNomeFornecedor());
        updateFornecedor.setEmailFornecedor(fornecedor.getEmailFornecedor());
        updateFornecedor.setCnpj(fornecedor.getCnpj());
        updateFornecedor.setTelefone(fornecedor.getTelefone());
        updateFornecedor.setStatus(fornecedor.getStatus());


        return fornecedorRepository.save(updateFornecedor);
    }

    public void deleteByID(Long id){

       try {
           Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);

           if(fornecedor.isPresent()){
               fornecedorRepository.deleteById(id);
           }else{
               throw new RuntimeException("Id não encontrado: " + id);
           }
       }catch (Exception e){
           throw new RuntimeException("Erro ao deletar fornecedor");
       }

    };

}


