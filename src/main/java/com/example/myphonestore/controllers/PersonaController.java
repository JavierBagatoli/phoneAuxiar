package com.example.myphonestore.controllers;

import com.example.myphonestore.entities.Persona;
import com.example.myphonestore.services.PersonaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/personas")

public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl>{
    @PutMapping("/{id}/generarToken")
    public ResponseEntity<?> update(@PathVariable Long id){
        try {

            return ResponseEntity.status(HttpStatus.OK).body("\"response\":\"Token generado exitosamente\"");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
}
