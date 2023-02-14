package com.hcsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcsc.service.BenifitInsurenceService;
import com.itextpdf.text.DocumentException;

@RestController
public class BenifitIssurenceController {
	
	@Autowired
	private BenifitInsurenceService insuenceService;
	
	
	@GetMapping(value = "/store")
	public void storeBiData() throws Exception {
		insuenceService.storeBiData();
	}
	
	

}
