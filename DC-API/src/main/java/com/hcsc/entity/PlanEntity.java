package com.hcsc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PLAN_MASTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer planId;

	@Column(name = "PlanName")
	private String planName;

}
