package com.hcsc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.entity.EdTriggerEntity;
import com.hcsc.pojo.CoResponse;
import com.hcsc.repo.CitizenAppRepo;
import com.hcsc.repo.EdTriggerRepo;
import com.hcsc.repo.EligibilityRepo;

@Service
public class CoServiceImpl implements CoService {

	@Autowired
	private EligibilityRepo eligibilityRepo;

	@Autowired
	private EdTriggerRepo edTriggerRepo;

	@Autowired
	private CitizenAppRepo citizenAppRepo;

	@Override
	public CoResponse trigger() {

		List<EdTriggerEntity> triEntity = edTriggerRepo.findAll();

		List<EdTriggerEntity> approvedNoti = triEntity.stream().filter(t -> t.getStatus() != null)
				.collect(Collectors.toList());
		CoResponse coResponse = new CoResponse();

		if (!approvedNoti.isEmpty()) {
			approvedNoti.stream().forEach(i -> {
				if (i.getStatus().equalsIgnoreCase("Approved"))
					sendApprovedStatus(i, coResponse);
				else
					sendDeniedStatus(i, coResponse);
			});
		}

		return null;
	}

	private void sendDeniedStatus(EdTriggerEntity i, CoResponse coResponse) {

	}

	private void sendApprovedStatus(EdTriggerEntity i, CoResponse coResponse) {

	}

}
