package com.assignment.logprocessor.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "logs")
public class LogEntity {

  @Id
  private String eventId;

  private String eventDuration;

  private String type;

  private String host;

  private Boolean alert;
}