package com.hcsc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KidsData {

	private Integer kidId;

	private String kName;
	private Integer kAge;
	private String kSsn;
	private Integer caseId;
}