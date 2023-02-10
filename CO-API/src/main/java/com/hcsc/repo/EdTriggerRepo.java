package com.hcsc.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.EdTriggerEntity;

public interface EdTriggerRepo extends JpaRepository<EdTriggerEntity, Serializable>{

}
