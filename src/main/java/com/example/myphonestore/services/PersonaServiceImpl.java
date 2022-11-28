package com.example.myphonestore.services;

import com.example.myphonestore.entities.*;
import com.example.myphonestore.entities.Dtos.DtoPersonaCambioCredenciales;
import com.example.myphonestore.entities.Dtos.DtoPersonaLogin;
import com.example.myphonestore.entities.Dtos.DtoPersonaRegistro;
import com.example.myphonestore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public String generarCodigoSeguridad(Long id) throws Exception {
        try{
            Optional<Persona> personaTraida = personaRepository.findById(id);
            if (personaTraida.isPresent()){
                Persona persona = personaTraida.get();
                persona.generarCodigoSeguridad();
                personaRepository.save(persona);
                return Integer.toString(persona.getCodigoSeguridad());
            }
            return "Fallo al generar";
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

    private String comprobarCampoVacio (String campo, String nombreCampo){
        if (campo.isEmpty())
            {return nombreCampo + " no puede ser vacío.";}
        return "no vacio";
    }
    public String registrar(DtoPersonaRegistro credenciales) throws Exception{
        String vectorErrores[] = new String[5];
        try{
            vectorErrores[0] = comprobarCampoVacio(credenciales.getApellido(), "Apellido");
            vectorErrores[1] = comprobarCampoVacio(credenciales.getNombre(),"Nombre");
            vectorErrores[2] = comprobarCampoVacio(credenciales.getEmail(), "EMail");
            vectorErrores[3] = comprobarCampoVacio(credenciales.getContrasenia(), "Contrasena");
            vectorErrores[4] = comprobarCampoVacio(credenciales.getContraseniaRepetida(), "Contrasena repetida");
            int contadorErrores = 0;
            for(int i = 0; i < vectorErrores.length; i++){
                if (!vectorErrores[i].equals("no vacio")){
                    contadorErrores++;
                }
            }
            if (contadorErrores == 0){
                Persona persona = new Persona();
                persona.setApellido(credenciales.getApellido());
                persona.setNombre(credenciales.getNombre());
                persona.setEmail(credenciales.getEmail());
                persona.setContrasenia(credenciales.getContrasenia());
                List<Carrito> listaCarritosNueva = new ArrayList<>();
                listaCarritosNueva.add(new Carrito());
                persona.setPersonaCarritos(listaCarritosNueva);
                personaRepository.save(persona);
                return "persona registrada";
            }else{
                return "Fallo al registrar";
            }

        }catch (Exception e){

            throw new Exception("Fallo al registrar persona " + e.getMessage());
        }
    }

    public String AddArticleToCart(String EmailPersona, String idArticulo) throws  Exception{
        try {
            Persona personaTraida = personaRepository.searchByEmail(EmailPersona);

            Articulo articulo1 = articuloRepository.searchByid(idArticulo);
            articuloRepository.save(articulo1);
            System.out.println("llega1");

            DetalleCarrito detalleCarrito = new DetalleCarrito();
            detalleCarrito.setSubtotal(150);
            detalleCarrito.setCantidad(1);
            detalleCarrito.setArticulo(articulo1);

            detalleCarritoRepository.save(detalleCarrito);

            ///Encontrar ultimo carrito
            int tamanioListaCarritos = personaTraida.getPersonaCarritos().size()-1;
            if (tamanioListaCarritos < 0){
                tamanioListaCarritos = 0;
            }
            Carrito carritoActual = personaTraida.getPersonaCarritos().get(tamanioListaCarritos);

            List<DetalleCarrito> listaDeDetalles = carritoActual.getDetalleCarritos();
            listaDeDetalles.add(detalleCarrito);

            carritoActual.setDetalleCarritos(listaDeDetalles);
            carritoRepository.save(carritoActual);

            return "Retorno";
        }catch (Exception e){
            throw new Exception("Fallo al agregar articulo al carrito: " + e);
        }
    }

    public String cambiarContrasenia(DtoPersonaCambioCredenciales credenciales) throws  Exception{
        try{
            Persona persona = personaRepository.searchByEmail(credenciales.getEmail());
            System.out.println(credenciales.getContraseniaRepetida() + " " + credenciales.getContrasenia());


            if(!credenciales.getContrasenia().equals(credenciales.getContraseniaRepetida()) ){
                return "Las contrañas deben ser identicas";
            }

            if(credenciales.getEmail() != credenciales.getNuevoEmail()){
                Persona persona2 = personaRepository.searchByEmail(credenciales.getNuevoEmail());
                System.out.println("llegaInterno");
                if (persona2 == null){
                    System.out.println("llegaRomep");
                    persona.setEmail(credenciales.getNuevoEmail());
                }else{
                    return "No se puede cambiar a el email indicado";
                }
            }

            if (persona.getCodigoSeguridad() == credenciales.getCodigo()){
                persona.setContrasenia(credenciales.getContrasenia());
                personaRepository.save(persona);
                return "modificacion de credenciales exitosa";
            }else{
                return "El codigo es incorrecto";
            }
        }catch (Exception e){
            throw new Exception("Fallo al actualizar credenciales");
        }
    }

    public String realizarCompra(String email)throws Exception{
        Persona persona = personaRepository.searchByEmail(email);
        System.out.println(persona.getEmail());
        if (persona == null){
            return "No existe la persona al mail asociado";
        }

        int tamanioCarrito = persona.getPersonaCarritos().size() -1;
        Carrito carrito = persona.getPersonaCarritos().get(tamanioCarrito);
        carrito.setEstado("Comprado");

        carritoRepository.save(carrito);


        List<Carrito> nuevaListaDeCarritos = persona.getPersonaCarritos();
        nuevaListaDeCarritos.add(new Carrito());
        persona.setPersonaCarritos(nuevaListaDeCarritos);

        personaRepository.save(persona);


        return "Compra realizada";
    }

    public String limpiarCarrito(String email)throws Exception{
        Persona persona = personaRepository.searchByEmail(email);

        if (persona == null){
            return "No existe la persona al mail asociado";
        }

        int tamanioCarrito = persona.getPersonaCarritos().size() -1;
        Carrito carrito = persona.getPersonaCarritos().get(tamanioCarrito);

        carrito.setEstado("vacio");
        List<DetalleCarrito> listaBorrada = carrito.getDetalleCarritos();
        listaBorrada.clear();

        carrito.setDetalleCarritos(listaBorrada);

        carritoRepository.save(carrito);

        return "carrito limpiado";
    }

    private Carrito buscarCarritoActivo(ArrayList<Carrito> listaCarritos){
        return new Carrito();
    }
}
