package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@SpringBootApplication
public class Lec09SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lec09SecurityApplication.class, args);
//		String encoded = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234");
//		System.out.println(encoded);
	}

}
