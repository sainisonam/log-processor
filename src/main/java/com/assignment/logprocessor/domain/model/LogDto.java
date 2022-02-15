package com.assignment.logprocessor.domain.model;

import lombok.Data;

@Data
public class LogDto {

  private String id;

  private String state;

  private String type;

  private String host;

  private Long timestamp;
}