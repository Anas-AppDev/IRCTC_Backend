package com.example.irctcBackend;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class IrctcBackendApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	public static void main(String[] args) {

		SpringApplication.run(IrctcBackendApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("shruti1415 : "+ passwordEncoder.encode("shruti1415"));
		System.out.println("anas2002 : "+ passwordEncoder.encode("anas2002"));
		System.out.println("aman2001 : "+ passwordEncoder.encode("aman2001"));
	}
}

