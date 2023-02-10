package com.hcsc.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcsc.entity.UserRegistration;

public interface CitizenAppRepo extends JpaRepository<UserRegistration,Serializable>{

	public UserRegistration findByAppId(Integer appId);
	
	@Query("select fname from UserRegistration where appId=:appId")
	public String getAccHoldName(@Param("appId") Integer appId);

}
