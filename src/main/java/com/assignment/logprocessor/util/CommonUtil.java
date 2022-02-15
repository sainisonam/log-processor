package com.assignment.logprocessor.util;

import com.assignment.logprocessor.domain.model.LogDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * CommonUtil is a utility class which contains utility methods to be used anywhere required in the
 * application
 */
@Slf4j
@Component
public class CommonUtil {

  public static LogDto parseStringToObject(String jsonLine) {
    try {
      return new ObjectMapper().readValue(jsonLine, LogDto.class);
    } catch (JsonProcessingException exception) {
      log.error("Exception occurred while parsing json from file: {}", exception.getMessage());
    }
    return null;
  }
}