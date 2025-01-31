package com.webestoque.webestoque.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fornecedor")
    private String nomeFornecedor;
    @Column(name = "email_fornecedor")
    private String emailFornecedor;
    private String cnpj;
    private String telefone;
    private Boolean status;
}
