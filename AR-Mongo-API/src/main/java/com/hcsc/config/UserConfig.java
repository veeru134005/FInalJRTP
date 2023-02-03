package com.hcsc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserConfig {

	public WebClient.Builder getWebClient() {
		return null;
	}
}
