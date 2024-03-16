package com.caritas.caritas.service;

import com.caritas.caritas.model.Admin;
import com.caritas.caritas.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService{

    @Autowired
    private AdminRepository repository;

    @Override
    public Admin create(Admin admin, boolean isAdmin) {
        if (repository.findByEmail(admin.getEmail()) == null) {  //si no existe el usuario lo creo
            admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
            this.setDefaultPermissions(admin,isAdmin);
            repository.save(admin);
        }
        return admin;
    }

    public void setDefaultPermissions(Admin admin, boolean isAdmin){
        admin.setEnabledAccount(true);
        admin.setCredentialsExpired(false);
        admin.setExpiredAccount(false);
        admin.setLockedAccount(false);
        admin.setAdmin(isAdmin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = repository.findByEmail(username);
        if (admin == null)
            throw new UsernameNotFoundException("User not found");
        return admin;
    }
}
