package com.hcsc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcsc.pojo.CaseData;
import com.hcsc.pojo.EducationData;
import com.hcsc.pojo.IncomeData;
import com.hcsc.pojo.KidsData;
import com.hcsc.service.DataCollectionServiceInter;

/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
*/

@RestController
//@Api(produces = "Application/json", value = "Opeartion to collect the Citizen data")
@RequestMapping(value = "/datacollection")
public class DataCollectController {

	@Autowired
	private DataCollectionServiceInter dataService;

	@GetMapping("/{appId}")
	//@ApiOperation(value = "Based on user Application id to validate user and generate  Randome number for create case")
	public String genRandomCaseIdSearchByUsrId(@PathVariable Integer appId) {

		return dataService.genRandomCaseIdSearchByUsrId(appId);

	}

	@GetMapping("/getPlans")
	//@ApiOperation(value = "Fetch All the Existing Plan in Rhod Iland")
	public Map<Integer, String> getPlans() {
		return dataService.getPlans();
	}
	
	@PostMapping("/plan")
	//@ApiOperation(value = "Save Citizen selected Plan details with case id")
	public String savePlanSelection(@RequestBody CaseData casedata) {
		
		return dataService.savePlanSelection(casedata);
		
	}
	
	@PostMapping("/income")
	//@ApiOperation(value = "Save Citizen Income details with case id")
	public String saveIncomeDetails(@RequestBody IncomeData incomeData) {
		
		return dataService.saveIncomeDetails(incomeData);
		
	}
	
	@PostMapping(value  = "/education")
	//@ApiOperation(value = "Save Citizen Education details with case id")
	public String saveEducationDetails(@RequestBody EducationData education) {
		
		return dataService.saveEducationDetails(education);
		
	}
	
	@PostMapping(value = "/kids")
	//@ApiOperation(value = "Save Citizen Kids details with case id")
	public String saveKidsDetails(@RequestBody KidsData kids) {
		
		return dataService.saveKidsDetails(kids);
	}
	
	@GetMapping(value = "/summary/{caseid}")
	//@ApiOperation(value = "Fetch the User Summary")
	public Map<String, Object> getsummary(@PathVariable Integer caseid) {
		return dataService.getsummary(caseid);
	}
	
	

}
