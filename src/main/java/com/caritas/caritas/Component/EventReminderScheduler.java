package com.caritas.caritas.Component;

import com.caritas.caritas.model.Publicacion;
import com.caritas.caritas.service.MailSenderImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventReminderScheduler {

    private final MailSenderImpl emailService;

    @Autowired
    public EventReminderScheduler(MailSenderImpl emailService) {
        this.emailService = emailService;
    }


    @Scheduled(cron = "0 * * * * ?")
    public void sendEventReminders() {
        List<Publicacion> publicacions =  emailService.getUpcomingEvents();
        if(publicacions.size()>0){
            String message = "hoy hay un evento en caritas,mira nuetro calendario para ver los detalles";
            emailService.send(message);
        }
        else {

        }

    }
}
