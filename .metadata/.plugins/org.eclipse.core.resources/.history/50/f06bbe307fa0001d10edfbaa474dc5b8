package com.hcsc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcsc.entity.UserRegistration;
import com.hcsc.repository.UserRegistrationRepo;

@Service
public class UserRegistrationService implements UserRegInter {

	@Value("${ssaweb.url}")
	private String SSAUrl;

	@Autowired
	private UserRegistrationRepo repo;

	@Autowired
	private RestTemplate rt;

	@Override
	public String userRegistration(UserRegistration userRegistration) {

		//Fetching Data from SSA web site to get user state
		String userState = rt.getForEntity(SSAUrl + userRegistration.getSsn(), String.class).getBody();
		
		// if user is is eligible then storing data
		if (!(userState.isBlank() && userState.isEmpty()) ? userState.trim().equalsIgnoreCase("Rhode Island") : false) {

			repo.save(userRegistration);

			return "Your Registration is Success";
		}

		return "You Are not eligible for this Plan";
	}

}
