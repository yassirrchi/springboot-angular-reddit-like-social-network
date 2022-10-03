package com.example.socialnet.Service;

import com.example.socialnet.Entities.EmailNotification;

public interface MailService {
    public void sendMail(EmailNotification emailNotification);
}
