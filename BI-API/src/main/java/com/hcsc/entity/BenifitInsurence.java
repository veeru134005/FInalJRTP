package com.hcsc.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "BI_INFO")
@Entity
@Data
public class BenifitInsurence {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer biId;
	private byte[] csvFile;
	private Long caseNum;

	private LocalDate createDate;

}
