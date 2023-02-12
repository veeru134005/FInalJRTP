package com.hcsc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ED_TRIGEGERS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EdTriggerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer triggerId;
	private Long caseNum;
	private byte[] pdf;
	private String status;

}
