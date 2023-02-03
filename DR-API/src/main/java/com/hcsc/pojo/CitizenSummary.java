package com.hcsc.pojo;

import com.hcsc.entity.EducationDetails;
import com.hcsc.entity.IncomeDetails;
import com.hcsc.entity.KidsDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CitizenSummary {

	private IncomeDetails income;
	private EducationDetails educationDetails;
	private KidsDetails kidsDetails;
	
}
