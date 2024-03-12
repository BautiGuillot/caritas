package com.caritas.caritas.service;


import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();

    void create(User user);

    void delete(Long id);

    List<Publicacion> Publi();
}
