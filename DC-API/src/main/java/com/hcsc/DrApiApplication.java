package com.hcsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DrApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrApiApplication.class, args);
	}

}
