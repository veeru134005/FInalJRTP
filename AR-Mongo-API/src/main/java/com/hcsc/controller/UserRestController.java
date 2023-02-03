package com.hcsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcsc.entity.CitizenRegistration;
import com.hcsc.service.ARServiceInter;

@RestController
public class UserRestController {

	@Autowired
	private ARServiceInter userRegInter;

	@GetMapping("/save")
	public String save(@RequestBody CitizenRegistration registration) {

		return userRegInter.userRegistration(registration);

	}

	@GetMapping("/user/appid")
	public Integer isValideCitizen(@PathVariable Integer appid) {

		return userRegInter.isValideCitizen(appid);

	}
}
