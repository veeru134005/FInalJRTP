package com.hcsc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {

	private Integer educationId;

	private String highestDegree;
	private Integer graduationYear;
	private String universityName;
	
	private Integer caseId;
	
}
