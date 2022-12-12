package com.example.glsib.Service;

import javax.mail.MessagingException;

public interface MailSending {
    void send(String to, String subject, String body) throws MessagingException;


}
