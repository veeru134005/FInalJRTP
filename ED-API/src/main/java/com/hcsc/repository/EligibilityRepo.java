package com.hcsc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.EligibilityEntity;

public interface EligibilityRepo extends JpaRepository<EligibilityEntity, Serializable> {

}
