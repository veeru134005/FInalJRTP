package com.hcsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcsc.pojo.CoResponse;
import com.hcsc.service.CoService;

@RestController
public class CorespondenceController {

	@Autowired
	public CoService service;

	@GetMapping("/notification")
	public CoResponse sendPdfAndMail() {

		return service.sendPdfAndMail();
	}

}
