package com.vershasKitchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vershasKitchen.payload.ClientDetails;
import com.vershasKitchen.services.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendDetails")
	public ResponseEntity<String> sendDetails(@RequestBody ClientDetails clientDetails) {
		try {
			emailService.sendCLientDetails(clientDetails);
			return ResponseEntity.status(HttpStatus.OK).body("Details Sent");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Details Can not be mailed");
		}
	}
	
}
