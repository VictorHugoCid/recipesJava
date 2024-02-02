package com.tastecamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tastecamp.api.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{
    Boolean existsByEmail(String email);
    
}
