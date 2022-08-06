package br.com.lexcoins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

@SpringBootApplication
public class LexCoinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LexCoinsApplication.class, args);
		System.out.printf("senha: " + new BCryptPasswordEncoder().encode("senha") + " final");
	}

}
