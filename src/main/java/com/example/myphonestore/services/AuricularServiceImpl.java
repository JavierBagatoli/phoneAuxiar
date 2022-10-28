package com.example.myphonestore.services;

import com.example.myphonestore.entities.Auricular;
import com.example.myphonestore.repositories.AuricularRepository;
import com.example.myphonestore.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuricularServiceImpl extends BaseServiceImpl<Auricular, Long> implements AuricularService{

    @Autowired
    private AuricularRepository auricularRepository;

    public AuricularServiceImpl(BaseRepository<Auricular, Long> baseRepository) {
        super(baseRepository);
    }
}
