package com.example.myphonestore.entities.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DtoPersonaLogin {
    private String email;
    private String password;
}
