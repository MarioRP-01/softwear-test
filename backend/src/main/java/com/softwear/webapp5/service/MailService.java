package com.softwear.webapp5.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailService {

    Properties prop;
    Session session;

    public MailService(String mailUsername, String mailPassword){

        this.prop = new Properties();

        this.prop.put("mail.transport.protocol", "smtp");
        this.prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        this.prop.put("mail.smtp.auth", true);
        this.prop.put("mail.smtp.starttls.enable", "true");
        this.prop.put("mail.smtp.host", "smtp.gmail.com");
        this.prop.put("mail.smtp.port", "587");
        this.prop.put("mail.smtp.ssl.trust", "*");
        this.session = Session.getInstance(this.prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                //softwearDAW@gmail.com 9SEc6FMyIvPB
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });
    }

    public void send(String receiverMail, String subject, String msg) throws Exception{
   
        Message message = new MimeMessage(this.session);
        message.setFrom(new InternetAddress("softwearDAW@gmail.com"));
        message.setRecipients(
        Message.RecipientType.TO, InternetAddress.parse(receiverMail));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        // Change type to "text/html" in order to send a html body
        mimeBodyPart.setContent(msg, "text/plain; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }


}
