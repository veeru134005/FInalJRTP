package com.hcsc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PLANS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plans {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer planId;

	@Column(name = "PlanName")
	private String planName;

}
