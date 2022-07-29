package br.com.lexcoins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LexCoinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LexCoinsApplication.class, args);
		System.out.printf(new BCryptPasswordEncoder().encode("senha123"));
	}

}
