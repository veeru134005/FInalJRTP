package com.hcsc.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.entity.DcKidsEntity;

public interface KidsDetailsRepo extends JpaRepository<DcKidsEntity, Serializable> {


	public List<DcKidsEntity> findByCaseNum(Long caseId);
}
