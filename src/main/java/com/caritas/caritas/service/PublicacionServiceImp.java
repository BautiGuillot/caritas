package com.caritas.caritas.service;


import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PublicacionServiceImp implements PublicacionService {

    @Autowired
    private PublicacionRepository repository;

    @Override
    public List<Publicacion> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(Publicacion publicacion) {
        repository.save(publicacion);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
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
    public List<Publicacion> getPublicacionesRecientes() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDosDiasAntes = fechaActual.minus(2, ChronoUnit.DAYS);

        List<Publicacion> publicacionesRecientes = repository.findPublicacionesRecientes(
                fechaDosDiasAntes, fechaActual
        );

        if (publicacionesRecientes.size() >= 3) {

            return publicacionesRecientes.subList(0, 3);
        } else {
            return publicacionesRecientes;
        }
    }
}
