package com.hcsc.entity;

import java.time.LocalDate;

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
@Table(name = "DC_KIDS")
public class DcKidsEntity {

	@Id
	private Integer kidId;

	private String kidName;
	private LocalDate kidDob;
	private String kSsn;
	private Integer kidSsn;
	private String KidsGender;
	private Long caseNum;

}
