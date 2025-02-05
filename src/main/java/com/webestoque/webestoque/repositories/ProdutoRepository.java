package com.webestoque.webestoque.repositories;

import com.webestoque.webestoque.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long > {

    Optional<Produto> findByCodigo(Long codigo);
}
