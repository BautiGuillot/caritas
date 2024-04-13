package com.caritas.caritas.repository;

import com.caritas.caritas.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query
    Admin findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE admins SET intentos_fallidos = ?1 WHERE email = ?2", nativeQuery = true)
    @Transactional
    void updateFailedAttempts(int failAttempts, String email);

}
