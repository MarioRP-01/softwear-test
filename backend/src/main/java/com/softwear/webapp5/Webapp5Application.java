package com.softwear.webapp5;

import com.softwear.webapp5.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@SpringBootApplication
public class Webapp5Application {

	@Autowired
	private Environment env;

	@Bean
	public MailService mailService() throws IOException {
		String email = env.getProperty("mailer.email");
		String pass = env.getProperty("mailer.pass");
		if(pass == null || pass.equals("")){
			System.out.print("Enter password for " + email + ": ");
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			pass = input.readLine();
		}
		return new MailService(email, pass);
	}

	public static void main(String[] args) {
		SpringApplication.run(Webapp5Application.class, args);
	}
}