package com.program.weather.service;


import com.program.weather.service.dto.request.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    /**
     * we create an EmailService which is responsible for creating and sending the email.
     * We used an HTML email template and added the email meta-data to the Model.
     * This email email-template.html is located in the src/main/resources/email folder.
     * We can use the values provided in the Model to fill our e-mail template.
     * We include a password reset link with the unique token that the user can use to reset his password.
     *
     * @param mail
     */
    public void sendEmail(MailDTO mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process("email-template", context);
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
