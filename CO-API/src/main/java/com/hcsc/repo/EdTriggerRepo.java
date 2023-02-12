package com.hcsc.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.EdTriggerEntity;

public interface EdTriggerRepo extends JpaRepository<EdTriggerEntity, Serializable>{

	
	public List<EdTriggerEntity> findByStatus(String status);
}
