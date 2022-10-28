package com.example.myphonestore.services;

import com.example.myphonestore.entities.Parlante;
import com.example.myphonestore.repositories.BaseRepository;
import com.example.myphonestore.repositories.ParlanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParlanteServiceImpl extends BaseServiceImpl<Parlante, Long> implements ParlanteService{

    @Autowired
    private ParlanteRepository parlanteRepository;

    public ParlanteServiceImpl(BaseRepository<Parlante, Long> baseRepository) {
        super(baseRepository);
    }
}
