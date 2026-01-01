package com.erkanozturk.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.erkanozturk"}) // katmanlı mimaryi bulması 
@EntityScan(basePackages = {"com.erkanozturk"})
@EnableJpaRepositories(basePackages = {"com.erkanozturk"})
@SpringBootApplication
public class GalleristApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(GalleristApplicationStarter.class, args);
	}

}
