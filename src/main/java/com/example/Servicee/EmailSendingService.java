package com.example.Servicee;

import com.example.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSendingService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSendingService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendNotificaitoin(String to,String s,String b) throws MailException {
        // send email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(s);
        mail.setText(b);

        javaMailSender.send(mail);
    }

}




