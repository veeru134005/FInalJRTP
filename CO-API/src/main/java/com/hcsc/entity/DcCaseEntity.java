package com.hcsc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "DC_CASES")
public class DcCaseEntity {

	@Id
	private Long caseNum;
	private Integer planId;
	private Integer appId;
	
	

}
