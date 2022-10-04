package com.example.socialnet.Service;

import com.example.socialnet.Entities.EmailNotification;
import com.example.socialnet.Exceptions.EmailSendingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;
    @Async
    @Override
    public void sendMail(EmailNotification emailNotification) {
        MimeMessagePreparator messagePreparator=mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springyassir@email.com");
            messageHelper.setTo(emailNotification.getRecipient());
            messageHelper.setSubject(emailNotification.getSubject());
            messageHelper.setText(mailContentBuilder.build(emailNotification.getBody()));
        };
        try{
            mailSender.send(messagePreparator);
            log.info("Activation email sent");
        }
        catch(Exception e){
            throw new EmailSendingException("Exception occured while sending the email");

        }

    }
}
