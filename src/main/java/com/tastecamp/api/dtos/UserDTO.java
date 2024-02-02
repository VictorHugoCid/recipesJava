package com.tastecamp.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank
    @Size(max = 100)
    private String username;

    @NotBlank
    @Email 
    private String email;

}
