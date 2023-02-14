package com.hcsc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.BenifitInsurence;

public interface BenifitInsurenceRepo extends JpaRepository<BenifitInsurence, Serializable> {

	
}
