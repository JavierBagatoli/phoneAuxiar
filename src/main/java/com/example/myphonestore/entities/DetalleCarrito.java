package com.example.myphonestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "detalle-carrito")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class DetalleCarrito extends Base{

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "subtotal")
    private int subtotal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;

}
