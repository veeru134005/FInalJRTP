package com.hcsc.pojo;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanSelection {

	private Long caseNum;
	private Integer planId;
	private Map<Integer, String> plansInfo;
	private String status;

	private Integer appId;

}
