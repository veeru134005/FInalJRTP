package com.hcsc.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "INCOME_DETAILS")
public class IncomeDetails {

	private Integer incomeId;
	private Double monthlySal;
	private Double rent;
	private Double propertyIncome;

	@JoinColumn(name = "CASE_FK")
	@ManyToOne(fetch = FetchType.EAGER)
	private Case caseId;

}