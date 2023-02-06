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
@Table(name = "DC_EDUATION")
public class DcEducationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer educationId;

	private String highestDegree;
	private Integer graduationYear;
	private String universityName;
	private Long caseNum;
	

}
