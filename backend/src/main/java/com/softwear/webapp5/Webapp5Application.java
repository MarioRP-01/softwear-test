package com.softwear.webapp5;

import com.softwear.webapp5.service.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@SpringBootApplication
public class Webapp5Application {

	@Bean
	public MailService mailService() throws IOException {
		System.out.print("Enter password for softwearDAW@gmail.com: ");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String pass =  input.readLine();
		return new MailService("softwearDAW@gmail.com", pass);
	}

	public static void main(String[] args) {
		SpringApplication.run(Webapp5Application.class, args);
	}
}