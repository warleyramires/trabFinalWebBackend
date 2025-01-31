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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
