package com.hcsc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoResponse {

  private Long recordsProcessed;
  private Long succeCount;
  private Long failureCount;
}
