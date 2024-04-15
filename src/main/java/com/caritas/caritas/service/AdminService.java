package com.caritas.caritas.service;

import com.caritas.caritas.model.Admin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface AdminService extends UserDetailsService{

    //const
    public static final int MAX_FAILED_ATTEMPTS = 3;

    public static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 horas en milisegundos

    //--------
    public Admin create(Admin admin,boolean isAdmin);

    public List<Admin> getAllAdmins();

    public void delete(Long id);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Admin findById(Long id);

    void update(Admin admin, Long id, boolean locked);

    public void increaseFailedAttempts(Admin admin);

    public void resetFailedAttempts(String email);

    public void lock(Admin admin);

    public boolean unlockWhenTimeExpired(Admin admin);
}
