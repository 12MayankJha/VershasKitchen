package com.vershasKitchen.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.vershasKitchen.Helper.ClientHelper;
import com.vershasKitchen.payload.ClientDetails;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendCLientDetails(ClientDetails details) {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;

		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(ClientHelper.VERSHASKITCHEN_EMAIL_ID);
			helper.setSubject(ClientHelper.VERSHASKITCHEN_EMAIL_SUBJECT);

			File resource = new ClassPathResource("/ClientDetails.html").getFile();
			String clientDetailsHtml = new String(Files.readAllBytes(resource.toPath()));

			clientDetailsHtml = clientDetailsHtml
					.replace("{name}", details.getName())
					.replace("{phonenumber}", details.getPhoneNumber())
					.replace("{email}", details.getEmail())
					.replace("{message}", details.getMessage());

			helper.setText(clientDetailsHtml, true);
			emailSender.send(message);

		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}

	}

}
