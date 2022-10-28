package com.example.myphonestore.services;

import com.example.myphonestore.entities.DetalleCarrito;
import com.example.myphonestore.repositories.BaseRepository;
import com.example.myphonestore.repositories.DetalleCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleCarritoServiceImpl extends BaseServiceImpl<DetalleCarrito, Long> implements DetalleCarritoService{

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    public DetalleCarritoServiceImpl(BaseRepository<DetalleCarrito, Long> baseRepository) {
        super(baseRepository);
    }
}
