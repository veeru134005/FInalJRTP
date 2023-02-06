package com.hcsc.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserRegistration")
public class UserRegistration {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "UserName")
	private String fname;
	@Column(name = "EmailId")
	private String email;
	@Column(name = "MobileNumber")
	private Long mobileNum;
	@Column(name = "Gender")
	private String gender;
	@Column(name = "DOB")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	@Column(name = "SSN")
	private Long ssn;
	
	@CreationTimestamp
	private LocalDate createDate;
	@CreationTimestamp
	private LocalDate updateDate;
	
	private String createdBy;
	private String updatedBy;
	
	
}
