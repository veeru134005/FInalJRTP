package com.hcsc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CASE_TAB")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Case {

	@Id
	private Integer caseId;

	@Column(name = "PlanName")
	private String planName;

}
