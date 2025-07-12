package br.com.nubank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

}
