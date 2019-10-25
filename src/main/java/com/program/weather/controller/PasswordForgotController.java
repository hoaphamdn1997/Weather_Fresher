package com.program.weather.controller;


import com.program.weather.entity.PasswordResetToken;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.repository.PasswordResetTokenRepository;
import com.program.weather.service.EmailService;
import com.program.weather.service.UserService;
import com.program.weather.service.dto.request.MailDTO;
import com.program.weather.service.dto.PasswordForgotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The type Password forgot controller.
 */
@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    /**
     * Forgot password dto password forgot dto.
     *
     * @return the password forgot dto
     */
    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDTO forgotPasswordDto() {
        return new PasswordForgotDTO();
    }

    /**
     * Display forgot password page string.
     *
     * @return the string
     */
    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgotpassword";
    }

    /**
     * You can submit a form post to the PasswordForgotController which’ll handle the incoming password forgot request.
     * We use standard hibernate validator annotations on the PasswordForgotDto to validate the incoming request.
     * We create a new unique PasswordResetToken and store it in the database.
     * We forward this token information to the user by email.
     * This email contains a special link to reset his password.
     *
     * @param form    the form
     * @param result  the result
     * @param request the request
     * @return string string
     */
    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDTO form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()) {
            return "forgotpassword";
        }

        UserEntity user = userService.findByUserEmail(form.getEmail());
        if (user == null) {
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgotpassword";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        MailDTO mail = new MailDTO();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "ALL \n" + "contact Hoaphamdn1997@gmail.com ");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }
}
