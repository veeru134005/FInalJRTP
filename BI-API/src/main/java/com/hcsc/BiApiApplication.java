package com.hcsc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hcsc.service.BenifitInsurenceService;

@SpringBootApplication
public class BiApiApplication  implements CommandLineRunner{

	@Autowired
	private BenifitInsurenceService insuenceService;
	
	public static void main(String[] args) {
		SpringApplication.run(BiApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
			//insuenceService.storeBiData();
		
			System.out.println("Exe completed .....");
		
	}

}
