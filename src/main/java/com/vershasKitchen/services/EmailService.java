package com.vershasKitchen.services;

import com.vershasKitchen.payload.ClientDetails;

public interface EmailService {

	public void sendCLientDetails(ClientDetails details);

}
