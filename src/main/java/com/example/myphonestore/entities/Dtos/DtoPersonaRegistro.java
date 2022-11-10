package com.example.myphonestore.entities.Dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class DtoPersonaRegistro {
    private String apellido;
    private String nombre;
    private int dni;
    private String email;
    private Date fechaNacimiento;
    private String contrasenia;
    private String contraseniaRepetida;
}
