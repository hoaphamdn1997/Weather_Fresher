package com.program.weather.controller;


import com.program.weather.entity.PasswordResetToken;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.repository.PasswordResetTokenRepository;
import com.program.weather.service.EmailService;
import com.program.weather.service.UserService;
import com.program.weather.service.dto.MailDTO;
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

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

	@Autowired 
	private UserService userService;

	@Autowired 
	private PasswordResetTokenRepository tokenRepository;

	@Autowired 
	private EmailService emailService;

	@ModelAttribute("forgotPasswordForm")
	public PasswordForgotDTO forgotPasswordDto() {
		return new PasswordForgotDTO();
	}

	@GetMapping
	public String displayForgotPasswordPage() {
		return "forgot-password";
	}

	@PostMapping
	public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDTO form,
											BindingResult result,
											HttpServletRequest request) {

		if (result.hasErrors()){
			return "forgot-password";
		}

		UserEntity user = userService.findByUserEmail(form.getEmail());
		if (user == null){
			result.rejectValue("email", null, "We could not find an account for that e-mail address.");
			return "forgot-password";
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
		model.put("signature", "https://memorynotfound.com");
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
		mail.setModel(model);
		emailService.sendEmail(mail);

		return "redirect:/forgot-password?success";

	}
}
