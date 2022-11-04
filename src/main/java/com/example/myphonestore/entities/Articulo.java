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
@Table(name = "articulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Articulo extends Base{

    @Column(name = "denominacion")
    private String denominacion;

    @Column(name = "marca")
    private String marca;

    @Column(name = "precio")
    private int precio;

    @Column(name = "bateria")
    private String bateria;

    @Column(name = "sumergible")
    private boolean sumergible;
}
