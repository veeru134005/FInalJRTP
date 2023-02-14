package com.hcsc.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.entity.BenifitInsurence;
import com.hcsc.entity.EligibilityEntity;
import com.hcsc.entity.RepoUtility;
import com.hcsc.entity.UserRegistration;
import com.hcsc.repository.BenifitInsurenceRepo;

@Service
public class BenifitInsurenceServiceImpl implements BenifitInsurenceService {

	@Autowired
	private RepoUtility repoUtility;

	@Autowired
	private BenifitInsurenceRepo benifitInsurenceRepo;

	@Override
	public void storeBiData() throws Exception {
		System.out.println(repoUtility.getEligibleMembers());

		String path = "D:\\Acadamy\\PracticeJava\\FInalJRTP\\BI-API\\src\\main\\resources\\BiData.txt";

		List<EligibilityEntity> eliData = repoUtility.getEligibleMembers();

		String headers = "Case Number,Case Number,FName,SSN,PlanName,BenefitAmout";

		FileWriter write = new FileWriter(new File(path), false);
		write.write(headers + "\n");

		eliData.stream().forEach(el -> {

			UserRegistration userData = repoUtility.getUserData(el.getCaseNum());

			String body = el.getCaseNum() + "," + userData.getFname() + "," + userData.getSsn() + "," + el.getPlanName()
					+ "," + el.getBeniftAmout();
			try {
				write.write(body + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		write.close();

		BenifitInsurence insurence = new BenifitInsurence();

		insurence.setCaseNum(77L);
		insurence.setCreateDate(LocalDate.now());
		insurence.setCsvFile(Files.readAllBytes(Paths.get(path)));

		benifitInsurenceRepo.save(insurence);

		System.out.println(write);

	}

}
