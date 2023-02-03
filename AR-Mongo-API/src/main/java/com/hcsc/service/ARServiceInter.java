package com.hcsc.service;

import com.hcsc.entity.CitizenRegistration;

public interface ARServiceInter {

	String userRegistration(CitizenRegistration registration);

	public Integer isValideCitizen(Integer appid);

}
