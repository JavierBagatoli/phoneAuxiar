package com.example.myphonestore.controllers;


import com.example.myphonestore.entities.DetalleCarrito;
import com.example.myphonestore.services.DetalleCarritoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/detallesCarrito")

public class DetalleCarritoController extends BaseControllerImpl<DetalleCarrito, DetalleCarritoServiceImpl>{
}
