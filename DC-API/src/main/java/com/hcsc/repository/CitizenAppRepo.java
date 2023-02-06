package com.hcsc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.UserRegistration;

public interface CitizenAppRepo extends JpaRepository<UserRegistration,Serializable>{

}
