package com.webestoque.webestoque.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_usuario")
    private String nomeUsuario;
    private String email;
    private String senha;
    private Integer permissao;
    private Boolean status;
    @Column(name = "data_cadastro")
    private LocalDateTime dt_cadastro;
}
