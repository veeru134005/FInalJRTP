package com.hcsc.pojo;

import java.util.List;

import com.hcsc.entity.DcEducationEntity;
import com.hcsc.entity.DcIncomeEntity;
import com.hcsc.entity.DcKidsEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Summary {

	private DcIncomeEntity income;
	private DcEducationEntity educationDetails;
	private List<DcKidsEntity> kidsEntity;
	
}
