package com.example.myphonestore.services;

import com.example.myphonestore.entities.Carrito;
import com.example.myphonestore.repositories.BaseRepository;
import com.example.myphonestore.repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoServiceImpl extends BaseServiceImpl<Carrito, Long> implements CarritoService{

    @Autowired
    private CarritoRepository carritoRepository;

    public CarritoServiceImpl(BaseRepository<Carrito, Long> baseRepository) {
        super(baseRepository);
    }
}
