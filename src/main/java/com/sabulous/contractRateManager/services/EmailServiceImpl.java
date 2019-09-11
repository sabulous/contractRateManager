package com.sabulous.contractRateManager.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport; // required for sending email
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    static Properties props;

    @Override
    public void setupAccount() {
        if(props == null) {
            props = new Properties();
            props.put("mail.smtp.user", Credentials.email);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "true");
        }
    }

    @Override
    public String sendNotification(String to, String subject, String body) {
        
        setupAccount();
        
        try
        {
            Authenticator auth = new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Credentials.email, Credentials.password);
                }
            };

            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText("Hey, this is the testing email.");
            msg.setSubject("Spring Notification TEST");
            msg.setFrom(new InternetAddress(Credentials.email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("s.sahin@thy.com"));
            //Transport.send(msg);

        } catch (MessagingException mex) {
             mex.printStackTrace();
        }
        
        return null;
    }

}