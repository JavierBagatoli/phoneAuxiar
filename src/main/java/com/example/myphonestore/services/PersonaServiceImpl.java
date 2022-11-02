package com.example.myphonestore.services;

import com.example.myphonestore.entities.Persona;
import com.example.myphonestore.repositories.BaseRepository;
import com.example.myphonestore.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona,Long> implements PersonaService{

    @Autowired
    private PersonaRepository personaRepository;

    public PersonaServiceImpl(BaseRepository<Persona,Long> baseRepository){
        super(baseRepository);
    }

    @Override
    public List<Persona> search(String filtro) throws Exception {
        try {
            //List<Persona> personas = personaRepository.findByNombreContainingOrApellidoContaining(filtro,filtro);
            //List<Persona> personas=personaRepository.search(filtro);
            List<Persona> personas = personaRepository.search(filtro);
            return personas;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Persona> search(String filtro, Pageable pageable) throws Exception {
        try {
            //Page<Persona> personas = personaRepository.findByNombreContainingOrApellidoContaining(filtro,filtro, pageable);
            //Page<Persona> personas=personaRepository.search(filtro, pageable);
            Page<Persona> personas=personaRepository.search(filtro, pageable);
            return personas;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void generarCodigoSeguridad(Long id) throws Exception {
        try{
            Optional<Persona> personaTraida = personaRepository.findById(id);
            if (personaTraida.isPresent()){
                Persona persona = personaTraida.get();
                personaRepository.save(persona);
            }
        }catch(Exception e){
            throw new Exception("Fallo al generar token de seguridad");
        }
    }
}
