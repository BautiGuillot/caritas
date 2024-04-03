package com.caritas.caritas.service;

import com.caritas.caritas.model.Email;
import java.util.List;

public interface EmailService {
    void upload(String email);

    List<Email> getAll();

    void delete(Long id);
}
