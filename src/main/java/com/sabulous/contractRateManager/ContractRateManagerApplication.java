package com.sabulous.contractRateManager;

import com.sabulous.contractRateManager.services.ContractService;
import com.sabulous.contractRateManager.services.EmailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ContractRateManagerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ContractRateManagerApplication.class, args);
		System.out.println("hey");

		EmailService emailService = (EmailService)ctx.getBean(EmailService.class);
		emailService.sendNotification("","","");
	}

}
