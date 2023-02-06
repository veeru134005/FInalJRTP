package com.hcsc.pojo;

import java.util.List;

import com.hcsc.entity.DcKidsEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KidsInfo {

	private Long caseNum;
	private List<DcKidsEntity> kidsInfo;
	
}
