package com.hcsc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.Case;

public interface CaseRepo extends JpaRepository<Case, Serializable>{

}
