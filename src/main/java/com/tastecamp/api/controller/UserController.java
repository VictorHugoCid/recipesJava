package com.tastecamp.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tastecamp.api.dtos.UserDTO;
import com.tastecamp.api.errors.UserAlreadyExistsException;
import com.tastecamp.api.models.UserModel;
import com.tastecamp.api.service.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/users")
public class UserController {
    
    final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid UserDTO body) {
        try {
            userService.createUser(body);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
        } catch (UserAlreadyExistsException error) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage());
        }

        // return new UserModel(Id);
    }
    
}
