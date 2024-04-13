package com.caritas.caritas.service;

import com.caritas.caritas.model.Admin;
import com.caritas.caritas.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        admin.setFailedAttempt(0);
        admin.setLockTime(null);
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

    @Override
    public Admin findById(Long id) {
        List<Admin> admins = getAllAdmins();
        for (Admin a: admins){
            if (a.getId() == id){
                return a;
            }
        }
        return null;
    }

    @Override
    public void update(Admin admin,Long id) {
        Admin adminDB = repository.findById(id).get();
        adminDB.setNombre(admin.getNombre());
        adminDB.setApellido(admin.getApellido());
        repository.save(adminDB);
    }

    @Override
    public void increaseFailedAttempts(Admin admin) { //incrementa los intentos fallidos
        int newAttemp = admin.getFailedAttempt() + 1;
        repository.updateFailedAttempts(newAttemp, admin.getEmail());

    }

    @Override
    public void resetFailedAttempts(String email) {
        repository.updateFailedAttempts(0,email);
    }

    @Override
    public void lock(Admin admin) {
        admin.setLockedAccount(true);
        admin.setLockTime(new Date());
        repository.save(admin);
    }

    @Override
    public boolean unlockWhenTimeExpired(Admin admin) {
        long lockTimeInMillis = admin.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis){
            admin.setLockedAccount(false);
            admin.setLockTime(null);
            admin.setFailedAttempt(0);

            repository.save(admin);

            return true;
        }
        return false;
    }

}
