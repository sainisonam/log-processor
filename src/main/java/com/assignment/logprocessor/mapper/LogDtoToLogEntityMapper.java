package com.assignment.logprocessor.mapper;

import static java.lang.Math.abs;

import com.assignment.logprocessor.domain.entity.LogEntity;
import com.assignment.logprocessor.domain.model.LogDto;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogDtoToLogEntityMapper {

  public LogEntity mapLogsToEntity(LogDto logObject, Map<String, LogDto> objectMap) {
    log.debug("In LogDtoToLogEntityMapper: preparing entity to store in database");
    LogEntity logEntity = new LogEntity();
    logEntity.setEventId(logObject.getId());
    long eventDuration = abs(objectMap.get(logObject.getId()).getTimestamp() -
        logObject.getTimestamp());
    logEntity.setEventDuration(eventDuration + "ms");
    logEntity.setAlert(eventDuration > 4);
    logEntity.setType(logObject.getType());
    logEntity.setHost(logObject.getHost());
    return logEntity;
  }
}
