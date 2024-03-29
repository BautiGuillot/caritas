package com.caritas.caritas.service;

import com.caritas.caritas.model.Email;
import com.caritas.caritas.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
