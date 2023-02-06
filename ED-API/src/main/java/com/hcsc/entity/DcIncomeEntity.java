package com.hcsc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DC_INCOME")
public class DcIncomeEntity {

	@Id
	private Integer incomeId;
	private Double salaryIncome;
	private Double rentIncome;
	private Double propertyIncome;

	private Long caseNum;

}
