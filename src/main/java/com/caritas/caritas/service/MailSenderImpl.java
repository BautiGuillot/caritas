package com.caritas.caritas.service;


import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailSenderImpl implements MailSender{
    @Autowired
    private JavaMailSender emailSender;



    @Autowired
    private UserService userService;

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public MailSenderImpl(JavaMailSender emailSender, UserService userService) {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    public void send(String message) {
        var mail = new SimpleMailMessage();
        mail.setFrom("fiorigabriel6@gmail.com");
        for(User user : userService.getAll()){
            if(user.getEmail() !=null){
                mail.setTo(user.getEmail());
                mail.setSubject("Nueva publicacion");
                mail.setText(message);
                emailSender.send(mail);
            }
        }


    }

    public List<Publicacion> getUpcomingEvents() {
        List<Publicacion> events = userService.Publi();
        List<Publicacion> upcomingEvents = new ArrayList<>();

        for (Publicacion p : events) {
            LocalDate fechaEvento = p.getFechaEvento();
            if (fechaEvento != null && fechaEvento.equals(LocalDate.now())) {
                upcomingEvents.add(p);
            }
        }

        return upcomingEvents;
    }



}
