package com.example.myphonestore.services;

import com.example.myphonestore.entities.*;
import com.example.myphonestore.entities.Dtos.DtoPersonaLogin;
import com.example.myphonestore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona,Long> implements PersonaService{

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    @Autowired
    private CarritoRepository carritoRepository;
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
                persona.generarCodigoSeguridad();
                personaRepository.save(persona);
            }
        }catch(Exception e){
            throw new Exception("Fallo al generar token de seguridad");
        }
    }

    public String login(DtoPersonaLogin credenciales) throws Exception{
        try{
            Persona personaTraida = personaRepository.searchByEmail(credenciales.getEmail());
            if (personaTraida.equals(null)){
                return "Persona no encontrada";
            }
            if (personaTraida.getContrasenia().equals(credenciales.getPassword())){
                return "Acceso concedido";
            }else{
                return "Credenciales incorrectas";
            }
        }catch(Exception e){
            throw new Exception("Fallo al encontrar persona");
        }
    }

    public String AddArticleToCart(String EmailPersona, String idArticulo) throws  Exception{
        //try {
            Persona personaTraida = personaRepository.searchByEmail(EmailPersona);

            Parlante articulo1 = new Parlante( "ParlanteTest",123,true);
            articulo1.setDescripcion("descripcion");
            articulo1.setDenominacion("EsUnparlante");
            articuloRepository.save(articulo1);

            DetalleCarrito detalleCarrito = new DetalleCarrito();
            detalleCarrito.setSubtotal(150);
            detalleCarrito.setCantidad(1);
            detalleCarrito.setArticulo(articulo1);

            detalleCarritoRepository.save(detalleCarrito);

            List<DetalleCarrito> listaDeDetalles = new ArrayList<>();
            listaDeDetalles.add(detalleCarrito);

            Carrito nuevoCarrito = new Carrito(new Date(), "nuevo", 123,123,21,listaDeDetalles);


            nuevoCarrito.setDetalleCarritos(listaDeDetalles);
            carritoRepository.save(nuevoCarrito);

            //Lista de carritos
            List<Carrito> listaCarritos = personaTraida.getPersonaCarritos();
            listaCarritos.add(nuevoCarrito);

            personaTraida.setPersonaCarritos(listaCarritos);

            personaRepository.save(personaTraida);





            return "Retorno";
        //}catch (Exception e){
        //    throw new Exception("Fallo al agregar articulo al carrito");
        //}
    }
}
