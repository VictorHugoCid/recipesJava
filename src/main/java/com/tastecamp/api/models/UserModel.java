package com.tastecamp.api.models;

import com.tastecamp.api.dtos.RecipeDTO;
import com.tastecamp.api.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserModel {

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    public UserModel(UserDTO userDTO){
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
    }
    
}
