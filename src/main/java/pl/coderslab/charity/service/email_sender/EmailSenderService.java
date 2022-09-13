package pl.coderslab.charity.service.email_sender;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);
    private static final String FROM_EMAIL = "[email_placeholder]";
    private static final String ENCODING = "utf-8";
    private static final String SUBJECT = "Weryfikacja mailowa konta 'Oddam w dobre ręce'.";
    private static final String ERROR_MESSAGE = "Niepowiodło się aby wysłać emaila";
    private final JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
    @Async
    public void sendConfirmationEmail(String toEmail, String email){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, ENCODING);
            helper.setText(email, true);
            helper.setTo(toEmail);
            helper.setSubject(SUBJECT);
            helper.setFrom(FROM_EMAIL);
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            LOGGER.error(ERROR_MESSAGE, e);
            throw new IllegalStateException(ERROR_MESSAGE);
        }
    }
}
