package com.example.myphonestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "persona")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Persona extends Base {

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dni")
    private int dni;

    @Column(name = "email")
    private String email;

    @Column(name = "rol")
    private String rol;

    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "codigoSeguridad")
    private int codigoSeguridad;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "persona_carritos",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "carrito_id")
    )
    private List<Carrito> personaCarritos = new ArrayList<>(); ////ToDo cambiar nombre de la variaba carritos


    public void generarCodigoSeguridad(){
        int numero = (int) (Math.random()*999999);
        setCodigoSeguridad(numero);
    }
}
