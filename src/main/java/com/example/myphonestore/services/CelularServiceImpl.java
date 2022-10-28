package com.example.myphonestore.services;

import com.example.myphonestore.entities.Celular;
import com.example.myphonestore.repositories.BaseRepository;
import com.example.myphonestore.repositories.CelularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CelularServiceImpl extends BaseServiceImpl<Celular, Long> implements CelularService{

    @Autowired
    private CelularRepository celularRepository;

    public CelularServiceImpl(BaseRepository<Celular, Long> baseRepository) {
        super(baseRepository);
    }
}
