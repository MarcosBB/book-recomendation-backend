package com.imd.br.bookRecomendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
public class BookRecomendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookRecomendationApplication.class, args);
	}

}
