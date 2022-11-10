package com.example.myphonestore.entities.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DtoPersonaCambioCredenciales {
    private String email;
    private int codigo;
    private String contrasenia;
    private String contraseniaRepetida;
    private String nuevoEmail;

}
