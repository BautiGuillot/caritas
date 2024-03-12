package com.caritas.caritas.service;


import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.model.User;
import com.caritas.caritas.repository.PublicacionRepository;
import com.caritas.caritas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public List<Publicacion> Publi(){

        return publicacionRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
