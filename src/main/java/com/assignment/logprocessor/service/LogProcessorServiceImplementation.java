package com.assignment.logprocessor.service;

import static com.assignment.logprocessor.util.CommonUtil.parseStringToObject;

import com.assignment.logprocessor.domain.model.LogDto;
import com.assignment.logprocessor.mapper.LogDtoToLogEntityMapper;
import com.assignment.logprocessor.repository.LogProcessorRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LogProcessorServiceImplementation is the service class which contains business logic to parse a
 * text file, retrieve json objects, process those objects and store them in hsqldb
 */
@Slf4j
@Service
public class LogProcessorServiceImplementation implements LogProcessorService {

  @Autowired
  private LogProcessorRepository processorRepository;

  @Autowired
  private LogDtoToLogEntityMapper mapper;

  @Override
  public void parseLogFile(String fileName) {
    log.info("In LogProcessorServiceImplementation: processing of file started");
    File file = new File(fileName);
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      Map<String, LogDto> objectMap = new HashMap<>();
      String line;

      while ((line = reader.readLine()) != null) {
        LogDto logObject = parseStringToObject(line);

        if (logObject != null) {
          if (objectMap.containsKey(logObject.getId())) {
            processorRepository.save(mapper.mapLogsToEntity(logObject, objectMap));
            log.info("Event details of id: {} inserted in database", logObject.getId());
          } else {
            log.debug("New log entry found with id:{}", logObject.getId());
            objectMap.put(logObject.getId(), logObject);
          }
        }else{
          log.debug("Object could not be parsed");
        }
      }

      reader.close();
    } catch (IOException ex) {
      log.error("File: {} not found at specified path: {}", file.getName(), file.getPath());
    }
  }
}