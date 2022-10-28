package com.example.myphonestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "celular")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Celular extends Articulo {

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "ram")
    private int ram;

    @Column(name = "camara")
    private float camara;

    @Column(name = "pantalla")
    private float pantalla;

}
