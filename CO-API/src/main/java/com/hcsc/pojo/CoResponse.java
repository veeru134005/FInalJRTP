package com.hcsc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoResponse {

  private long recordsProcessed;
  private long succeCount;
  private long failureCount;
  @JsonIgnore
  private boolean isEmailSend;
}
