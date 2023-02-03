package com.hcsc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "KIDS_DETAILS")
public class KidsDetails {

	@Id
	private Integer kidId;

	private String kName;
	private Integer kAge;
	private String kSsn;
	@JoinColumn(name = "CASE_FK")
	@ManyToOne
	private Case caseId;

}
