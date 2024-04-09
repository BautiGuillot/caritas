package com.caritas.caritas.service;

import com.caritas.caritas.model.Email;
import com.caritas.caritas.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailServiceImp implements EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender mailSender;


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

    @Override
    public void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("mail enviado");


    }

    public void notifySuscribers(String body ){

        String defaultSubject = "Se ha publicado nuevo contenido en la página de cáritas Mercedes/Luján!";

        for(Email email : getAll()){
            sendEmail(email.getEmail(), defaultSubject,body);
        }
    }


}
