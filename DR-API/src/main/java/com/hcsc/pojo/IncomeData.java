package com.hcsc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeData {

	private Integer incomeId;
	private Double monthlySal;
	private Double rent;
	private Double propertyIncome;

	private Integer caseId;

}
