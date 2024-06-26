package com.caritas.caritas.service;

import com.caritas.caritas.model.Publicacion;
import java.util.List;

public interface PublicacionService {
    List<Publicacion> getAll();

    void create(Publicacion publicacion);

    void delete(Long id);

    void update(Publicacion publicacion, Long id);

    Publicacion findById(Long id);

    List<Publicacion> getPublicacionesCorrectas();

    List<Publicacion> getPublicacionesBorradas();

    void reciclarPublicacion(Long id);

    void restorePublicacion(Long id);

    List<Publicacion> getUltimas3PublicacionesCorrectas();

}
