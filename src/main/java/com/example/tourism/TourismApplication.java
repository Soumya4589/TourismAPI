package com.example.tourism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TourismApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourismApplication.class, args);
	}

}
