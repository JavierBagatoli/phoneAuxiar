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
@Table(name = "carrito")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Carrito extends Base {

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "estado")
    private String estado;

    @Column(name = "numero")
    private int numero;

    @Column(name = "total")
    private int total;

    @Column(name = "descuento")
    private int descuento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "carrito_detalleCarrito",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "detalle_carrito_id")
    )
    private List<DetalleCarrito> detalleCarritos = new ArrayList<>();

}
