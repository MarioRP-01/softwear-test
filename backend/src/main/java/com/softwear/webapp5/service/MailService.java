package com.softwear.webapp5.service;

import com.softwear.webapp5.data.CouponView;
import com.softwear.webapp5.data.TransactionView;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.model.Transaction;

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
        this.prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        this.session = Session.getInstance(this.prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
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

    private String createCartSummary(Transaction cart) {
        StringBuilder products = new StringBuilder();
        TransactionView cartView = new TransactionView(cart);
        for(int i = 0; i < cartView.getTransactionEntries().size(); i++) {
            TransactionView.TransactionViewEntry entry = cartView.getTransactionEntries().get(i);
            products.append(String.format("\t#%d\t%s - %s\t$%01.02f x %d\t->\t$%01.02f\n",
                    i + 1, entry.getProduct().getName(), entry.getProduct().getSize(), entry.getProduct().getPrice(), entry.getQuantity(), entry.getTotalPrice()));
        }
        if(cartView.getCoupon() != null) {
            CouponView coupon = cartView.getCoupon();
            products.append(String.format("\tCoupon: %s\t\t\t- $%01.02f\n", coupon.getCode(), coupon.getDiscount()));
        }
        products.append(String.format("Total: $%01.02f\n", cartView.getTotalPrice()));
        return String.format("Transaction ID: %d\nDate: %s\nStatus: %s\nShipping Address: %s\nSummary:\n%s", cart.getId(), cart.getDate(), cart.getType(), cart.getUser().getAddress(), products.toString());
    }

    public void sendPurchaseMail(Transaction cart) {
        ShopUser user = cart.getUser();
        StringBuilder msg = new StringBuilder();
        msg.append(String.format("Hi Mr./Ms. %s,\n", user.getLastName()));
        msg.append("We have recived the payment of your new oder. It will be processed soon\n");
        msg.append("Order details:\n");
        msg.append(createCartSummary(cart));
        try {
            send(user.getEmail(), "Your order has been paid", msg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}