package com.program.weather.controller;

import com.program.weather.entity.PasswordResetToken;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.repository.PasswordResetTokenRepository;
import com.program.weather.service.UserService;
import com.program.weather.service.dto.PasswordResetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * The type Password reset controller.
 */
@Controller
@RequestMapping("/reset-password")
/**
 * When the user has received his password reset email.
 * He is forwarded to the PasswordResetController mapped to the /reset-password URL.
 * This produces a HTTP GET with the token as request parameter.
 * We read the token and if the token is present and valid we put it in the Model map.
 * When the user posts his PasswordResetDto, the form is validated and executed if no errors occur.
 */
public class PasswordResetController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Password reset password reset dto.
     *
     * @return the password reset dto
     */
    @ModelAttribute("passwordResetForm")
    public PasswordResetDTO passwordReset() {
        return new PasswordResetDTO();
    }

    /**
     * Display reset password page string.
     *
     * @param token the token
     * @param model the model
     * @return the string
     */
    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null) {
            model.addAttribute("error", "Could not find password reset token.");
        } else if (resetToken.isExpired()) {
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        return "password/resetpassword";
    }

    /**
     * Handle password reset string.
     *
     * @param form               the form
     * @param result             the result
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @PostMapping
    @Transactional
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDTO form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/reset-password?token=" + form.getToken();
        }

        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        UserEntity user = token.getUser();
        String updatedPassword = passwordEncoder.encode(form.getPassword());
        userService.updatePassword(updatedPassword, user.getUserId());
        tokenRepository.delete(token);

        return "redirect:/login?resetSuccess";
    }
}
