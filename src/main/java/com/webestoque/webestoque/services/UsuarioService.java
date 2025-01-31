package com.webestoque.webestoque.services;

import com.webestoque.webestoque.entities.Usuario;
import com.webestoque.webestoque.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario createUser(Usuario usuario){
        try{
            if(usuarioRepository.findByEmail(usuario.getEmail())!= null){
                throw new RuntimeException("E-mail já cadastrado");
            }

            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            usuario.setDt_cadastro(LocalDateTime.now());

        return usuarioRepository.save(usuario);

        }catch (Exception e){
            throw new RuntimeException("Erro ao criar usuario: " + e.getMessage());
        }
    }

    public List<Usuario> listUsers() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();

            if (usuarios.isEmpty()) {
                throw new RuntimeException("Não há usuários: ");
            }

            return usuarios;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuarios: " + e.getMessage());
        }
    }

}
