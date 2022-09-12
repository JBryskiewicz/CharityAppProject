package pl.coderslab.charity.service.email_sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.domain.VerificationToken;
import pl.coderslab.charity.service.verification_token.VerificationTokenService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;

@Service
public class EmailSenderService {

    private final JavaMailSender mailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final String fromEmail = "[email_placeholder]";

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
    @Async
    public void sendConfirmationEmail(String toEmail, String email){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(toEmail);
            helper.setSubject("Weryfikacja mailowa konta 'Oddam w dobre ręce'.");
            helper.setFrom(fromEmail);
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            LOGGER.error("Niepowiodło się", e);
            throw new IllegalStateException("Niepowiodło się aby wysłać emaila");
        }
    }
}
