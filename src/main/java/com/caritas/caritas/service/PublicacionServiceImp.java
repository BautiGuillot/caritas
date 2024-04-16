package com.caritas.caritas.service;


import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PublicacionServiceImp implements PublicacionService {

    @Autowired
    private EmailServiceImp emailServiceImp;

    @Autowired
    private PublicacionRepository repository;

    @Override
    public List<Publicacion> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Publicacion publicacion) {
        publicacion.setReciclada(false);
        emailServiceImp.notifySuscribers(publicacion.getTitulo(),publicacion.getDescripcion());
        repository.save(publicacion);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Publicacion publicacion, Long id) {
        Publicacion publicacionDB = repository.findById(id).get();
        publicacionDB.setTitulo(publicacion.getTitulo());
        publicacionDB.setDescripcion(publicacion.getDescripcion());
        repository.save(publicacionDB);
    }

    @Override
    public Publicacion findById(Long id) {
        List<Publicacion> publicacions= getAll();
        for(Publicacion p : publicacions){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Publicacion> getPublicacionesCorrectas() {
        List<Publicacion> publicaciones = this.getAll();
        List<Publicacion> publicacionesCorrectas = new ArrayList<>();
        for(Publicacion p : publicaciones){
            if(!p.isReciclada()){
                publicacionesCorrectas.add(p);
            }
        }
        Collections.reverse(publicacionesCorrectas);
        return publicacionesCorrectas;

    }

    public List<Publicacion> getPublicacionesBorradas(){
        List<Publicacion> publicaciones = this.getAll();
        List<Publicacion> publicacionesBorradas = new ArrayList<>();
        for(Publicacion p : publicaciones){
            if(p.isReciclada()){
                publicacionesBorradas.add(p);
            }
        }
        return publicacionesBorradas;
    }

    public void reciclarPublicacion(Long id){
        Publicacion publicacion = this.findById(id);
        publicacion.setReciclada(true);
        this.update(publicacion, id);
    }

    public void restorePublicacion(Long id){
        Publicacion publicacion = this.findById(id);
        publicacion.setReciclada(false);
        this.update(publicacion, id);
    }


    @Override
    public List<Publicacion> getUltimas3PublicacionesCorrectas() {
        List<Publicacion> publicaciones = this.getPublicacionesCorrectas();
        List<Publicacion> ultimas3Publicaciones = new ArrayList<>();
        if(publicaciones.size() <= 3){
            return publicaciones;
        }else{
            for(int i = 0; i < 3; i++){
                ultimas3Publicaciones.add(publicaciones.get(i));
            }
            return ultimas3Publicaciones;
        }
    }
}
