package com.hcsc.repository;

import java.io.Serializable;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcsc.entity.UserRegistration;

public interface CitizenAppRepo extends JpaRepository<UserRegistration,Serializable>{

	public UserRegistration findByAppId(Integer appId);
	
	@Query("select  fname from UserRegistration where appId:appId")
	public String getAccHoldName(@PathParam("appId") Integer appId);

}
