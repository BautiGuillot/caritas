package com.caritas.caritas.service;

import com.caritas.caritas.model.Email;
import com.caritas.caritas.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.TemplateEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
public class EmailServiceImp implements EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    private TemplateEngine templateEngine;

    public EmailServiceImp(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

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

    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String title, String body) throws MessagingException {

        Context context = new Context();
        context.setVariable("titulo",title);
        context.setVariable("cuerpo",body);


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlContent = templateEngine.process("newsletterTemplate",context);
        helper.setText(htmlContent, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setFrom(sender);

        mailSender.send(mimeMessage);

        /*SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(toEmail);
        message.setText(template);
        message.setSubject(subject);

        mailSender.send(message);*/

        System.out.println("mail enviado");


    }

    public void notifySuscribers(String title, String body ){

        String defaultSubject = "Se ha publicado nuevo contenido en la página de cáritas Mercedes/Luján!";

        for(Email email : getAll()){
            try {
                sendEmail(email.getEmail(), defaultSubject,title,body);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
