package com.caritas.caritas.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="admins")
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Publicacion> publicaciones;

    @Column
    private boolean isAdmin=false;

    @Column(nullable = false)
    private Boolean enabledAccount;

    @Column(nullable = false)
    private Boolean expiredAccount;

    @Column(nullable = false)
    private Boolean lockedAccount;

    @Column(nullable = false)
    private Boolean credentialsExpired;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Boolean getEnabledAccount() {
        return enabledAccount;
    }

    public void setEnabledAccount(Boolean enabledAccount) {
        this.enabledAccount = enabledAccount;
    }

    public Boolean getExpiredAccount() {
        return expiredAccount;
    }

    public void setExpiredAccount(Boolean expiredAccount) {
        this.expiredAccount = expiredAccount;
    }

    public Boolean getLockedAccount() {
        return lockedAccount;
    }

    public void setLockedAccount(Boolean lockedAccount) {
        this.lockedAccount = lockedAccount;
    }

    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(isAdmin()){
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !getExpiredAccount();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getLockedAccount();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !getCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return getEnabledAccount();
    }


}
