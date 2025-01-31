package com.webestoque.webestoque.controllers;

import com.webestoque.webestoque.dtos.LoginDTO;
import com.webestoque.webestoque.dtos.UsuarioResponseDTO;
import com.webestoque.webestoque.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try{
            UsuarioResponseDTO usuarioResponseDTO = loginService.login(loginDTO);
            return ResponseEntity.ok(usuarioResponseDTO);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

}
