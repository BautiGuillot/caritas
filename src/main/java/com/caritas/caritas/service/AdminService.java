package com.caritas.caritas.service;

import com.caritas.caritas.model.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface AdminService extends UserDetailsService{
    public Admin create(Admin admin,boolean isAdmin);

    public List<Admin> getAllAdmins();

    public void delete(Long id);

    Admin findById(Long id);

    void update(Admin admin, Long id);
}
