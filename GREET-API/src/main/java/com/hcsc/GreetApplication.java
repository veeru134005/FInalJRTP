package com.hcsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class GreetApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetApplication.class, args);
	}
	
	@GetMapping("/greet")
	public String getGreetMsg() {
		return "Msg from Greet Cleint";
	}

}
