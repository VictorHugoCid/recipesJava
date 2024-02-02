package com.tastecamp.api.service;

import org.springframework.stereotype.Service;

import com.tastecamp.api.dtos.UserDTO;
import com.tastecamp.api.errors.UserAlreadyExistsException;
import com.tastecamp.api.models.UserModel;
import com.tastecamp.api.repository.UserRepository;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserModel createUser(UserDTO body){
        if(userRepository.existsByEmail(body.getEmail())){
            throw new UserAlreadyExistsException("Esse email já está sendo utilizado");
        }
        return userRepository.save(new UserModel(body));
    }
    
}
