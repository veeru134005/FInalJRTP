package com.hcsc.pojo;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenEligibility {

	private Integer edTranceId;
	private String planName;
	private String status;
	private Double beniftAmout;
	private Long caseNum;
	private String deniedReason;
	private String holderName;
	private String startDate;
	private String endDate;
	private boolean isCitigenEligible;
	
	public String getStartDatewithOneDay() throws ParseException {
		return DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDate.now().plusDays(1));
	}

	public String getEndDateWithThreeMonths() throws ParseException {
		return DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDate.now().plusDays(91));
	}
	
	public Integer getCitiZenAge(Date dob) {
		
		Date now = new Date();
		long timeBetween = now.getTime() - dob.getTime();
		double yearsBetween = timeBetween / 3.15576e+10;
		
		return (int) Math.floor(yearsBetween);
	}

}
