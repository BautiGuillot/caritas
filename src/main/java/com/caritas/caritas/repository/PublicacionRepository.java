package com.caritas.caritas.repository;

import com.caritas.caritas.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

}
