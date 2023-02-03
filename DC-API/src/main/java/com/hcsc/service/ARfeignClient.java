package com.hcsc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8080",value = "DR-API")
public interface ARfeignClient {
	
	@GetMapping("/user/appid")
	public Integer isValideCitizen(Integer userAppId);

}
