package com.softwear.webapp5.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender emailSender;

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        
        mailSender.setUsername("softwearDAW@gmail.com");
        mailSender.setPassword("9SEc6FMyIvPB");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        
        return mailSender;
    }

    public MailService(){
        this.emailSender = getJavaMailSender();
    }

    public void send(String receiverMail, String subject, String msg){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("softwearDAW@gmail.com");
        message.setTo(receiverMail); 
        message.setSubject(subject); 
        message.setText(msg);
        emailSender.send(message);
    }

}