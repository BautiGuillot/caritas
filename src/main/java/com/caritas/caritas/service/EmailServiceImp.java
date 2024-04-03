package com.caritas.caritas.service;

import com.caritas.caritas.model.Email;
import com.caritas.caritas.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private EmailRepository repository;


    @Override
    public void upload(String email) {
        Email emailDB = new Email();
        emailDB.setEmail(email);
        repository.save(emailDB);
    }

    @Override
    public List<Email> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
