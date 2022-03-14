package com.softwear.webapp5;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class Webapp5Application {

	public static void main(String[] args) {
		SpringApplication.run(Webapp5Application.class, args);
	}

} 