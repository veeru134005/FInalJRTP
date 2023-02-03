package com.hcsc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("GREET-API")
public interface GreetApiClient {

	@GetMapping("/greet")
	public String getGreetMsg();
	
}
