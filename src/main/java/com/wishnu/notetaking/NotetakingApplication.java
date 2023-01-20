package com.wishnu.notetaking;

import com.wishnu.notetaking.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class NotetakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfiguration.class, args);
	}



}
