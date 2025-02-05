package com.webestoque.webestoque.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigo;
    private String produto;
    private Integer saldo;
    private String status;
    @Column(name = "preco_compra")
    private Double precoCompra;

    @Column(name = "data_cadastro")
    private LocalDateTime dtCadastro;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
}
