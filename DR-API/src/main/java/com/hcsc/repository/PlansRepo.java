package com.hcsc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.Plans;

public interface PlansRepo extends JpaRepository<Plans, Serializable> {

}
