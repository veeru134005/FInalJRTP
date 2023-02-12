package com.hcsc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ED_ELG_DTL")
public class EligibilityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer edTranceId;
	private Long caseNum;
	private String holderName;
	private String planName;
	private String status;
	private String startDate;
	private String endDate;
	private Double beniftAmout;
	private String deniedReason;

}
