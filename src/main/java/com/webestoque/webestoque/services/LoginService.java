package com.webestoque.webestoque.services;

import com.webestoque.webestoque.dtos.LoginDTO;
import com.webestoque.webestoque.dtos.UsuarioResponseDTO;
import com.webestoque.webestoque.entities.Usuario;
import com.webestoque.webestoque.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO  login(LoginDTO loginDTO){
        try{
            Usuario usuario = usuarioRepository.findByEmail(loginDTO.email());
            if(usuario == null){
                throw new RuntimeException("Usuario não encontrado. ");
            }
            if (!passwordEncoder.matches(loginDTO.senha(), usuario.getSenha())) {
                throw new RuntimeException("Senha incorreta.");
            }

            return UsuarioResponseDTO.fromEntity(usuario);

        }catch (Exception e){
            throw new RuntimeException("Erro ao efetuar login: " + e.getMessage());
        }
    }
}
