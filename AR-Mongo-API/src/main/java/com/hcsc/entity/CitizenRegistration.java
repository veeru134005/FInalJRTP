package com.hcsc.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "CitizenRegistration")
public class CitizenRegistration {
	@Id
	private Integer appId;
	@Field(name = "UserName")
	private String name;
	@Field(name = "EmailId")
	private String emailId;
	@Field(name = "MobileNumber")
	private Long mobileNumber;
	@Field(name = "Gender")
	private String gender;
	@Field(name = "DOB")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	@Field(name = "SSN")
	private String ssn;
}
