package com.example.myphonestore.controllers;

import com.example.myphonestore.entities.Dtos.DtoObjeto;
import com.example.myphonestore.entities.Dtos.DtoPersonaCambioCredenciales;
import com.example.myphonestore.entities.Dtos.DtoPersonaLogin;
import com.example.myphonestore.entities.Dtos.DtoPersonaRegistro;
import com.example.myphonestore.entities.Persona;
import com.example.myphonestore.services.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/personas")

public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl>{
    @Autowired
    private PersonaServiceImpl servicePersona;
    @PutMapping("/{id}/generarToken")
    public ResponseEntity<?> update(@PathVariable Long id){
        try {
            String respuesta = servicePersona.generarCodigoSeguridad(id);
            return ResponseEntity.status(HttpStatus.OK).body("\"response\":\"" + respuesta + "\"");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> update(@RequestBody DtoPersonaLogin credenciales){
        try {
            String respuesta =  servicePersona.login(credenciales);
            return ResponseEntity.status(HttpStatus.OK).body("{\"response\":\"" + respuesta + "\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @PostMapping("/{emailPersona}/agregarAlCarrito")
    public ResponseEntity<?> update(@PathVariable String emailPersona,@RequestBody DtoObjeto id){
        try {
            String respuesta = servicePersona.AddArticleToCart(emailPersona, id.getId());
            return ResponseEntity.status(HttpStatus.OK).body("{\"response\":\"" + respuesta + "\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() +"\"}");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody DtoPersonaRegistro credenciales){
        try{
            String respuesta = servicePersona.registrar(credenciales);
            return ResponseEntity.status(HttpStatus.OK).body("{\"response\":\"" + respuesta + "\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() +"\"}");
        }
    }

    @PostMapping("/actualizarCredenciales")
    public ResponseEntity<?> cambiarContrasenia(@RequestBody DtoPersonaCambioCredenciales credenciales){
        try{
            String respuesta = servicePersona.cambiarContrasenia(credenciales);
            return ResponseEntity.status(HttpStatus.OK).body("{\"response\":\"" + respuesta + "\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() +"\"}");
        }
    }

    @PostMapping("/realizarCompra")
    public ResponseEntity<?> realizarCompra(@RequestBody DtoPersonaCambioCredenciales email){
        try{
            String respuesta = servicePersona.realizarCompra(email.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body("{\"response\":\"" + respuesta + "\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() +"\"}");
        }
    }

    @PostMapping("/limpiarCarrito")
    public ResponseEntity<?> limpiarCarrito(@RequestBody DtoPersonaCambioCredenciales email){
        try{
            String respuesta = servicePersona.limpiarCarrito(email.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body("{\"response\":\"" + respuesta + "\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() +"\"}");
        }
    }

}
