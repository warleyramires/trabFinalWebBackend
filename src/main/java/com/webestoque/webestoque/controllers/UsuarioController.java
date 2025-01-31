package com.webestoque.webestoque.controllers;

import com.webestoque.webestoque.entities.Usuario;
import com.webestoque.webestoque.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @PostMapping("/cria-user")
    public ResponseEntity<?> createUser(@RequestBody Usuario usuario){
        try{
            Usuario newUser = usuarioService.createUser(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        try{
            List<Usuario>  listUsers = usuarioService.listUsers();
            return ResponseEntity.status(HttpStatus.OK).body(listUsers);
        }catch (RuntimeException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
