package com.webestoque.webestoque.repositories;

import com.webestoque.webestoque.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    Optional<Fornecedor> findByEmailFornecedor(String email);

    Optional<Fornecedor> findByNomeFornecedor(String nome);

    Optional<Fornecedor> findByCnpj(String cnpj);

}
