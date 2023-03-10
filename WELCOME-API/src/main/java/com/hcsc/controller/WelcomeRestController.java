package com.hcsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcsc.feign.GreetApiClient;

@RestController
public class WelcomeRestController {

	private static final Logger log = LoggerFactory.getLogger(WelcomeRestController.class);

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private GreetApiClient greetApiClient;

	@GetMapping("/welcome")
	public String getInterService() {

		log.info("Before calling Greet Service"+discoveryClient.getServices());

		String greetMsg = greetApiClient.getGreetMsg();
		String welcomeMsg = "Welcome to Veera IT..!!";

		return welcomeMsg + "  " + greetMsg;
	}

}
