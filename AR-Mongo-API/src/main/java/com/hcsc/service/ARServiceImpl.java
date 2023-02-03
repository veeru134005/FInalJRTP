package com.hcsc.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcsc.entity.CitizenRegistration;
import com.hcsc.repository.UserRegistrationRepo;

public class ARServiceImpl implements ARServiceInter{
	@Autowired
	private UserRegistrationRepo registrationRepo;

	@Override
	public String userRegistration(CitizenRegistration registration) {
		
		return null;
	}

	@Override
	public Integer isValideCitizen(Integer appid) {
		// TODO Auto-generated method stub
		return registrationRepo.findById(appid).block().getAppId();
	}
}
