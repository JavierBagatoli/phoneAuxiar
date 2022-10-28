package com.example.myphonestore.services;

import com.example.myphonestore.entities.Articulo;
import com.example.myphonestore.repositories.ArticuloRepository;
import com.example.myphonestore.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloServiceImpl extends BaseServiceImpl<Articulo, Long> implements ArticuloService{

    @Autowired
    private ArticuloRepository articuloRepository;

    public ArticuloServiceImpl(BaseRepository<Articulo, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Articulo> search(String filtro) throws Exception {
        try {

            List<Articulo> articulos = articuloRepository.search(filtro);

            return articulos;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Articulo> search(String filtro, Pageable pageable) throws Exception {
        try {

            Page<Articulo> articulos = articuloRepository.search(filtro, pageable);

            return articulos;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

