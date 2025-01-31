package com.webestoque.webestoque.dtos;

import com.webestoque.webestoque.entities.Usuario;

public record UsuarioResponseDTO(Long id, String nomeUsuario, String email, Integer permissao, Boolean status ) {

    public static UsuarioResponseDTO fromEntity(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeUsuario(),
                usuario.getEmail(),
                usuario.getPermissao(),
                usuario.getStatus()
        );
    }
}
