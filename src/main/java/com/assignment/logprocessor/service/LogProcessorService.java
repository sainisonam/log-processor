package com.assignment.logprocessor.service;

import org.springframework.stereotype.Component;

@Component
public interface LogProcessorService {

  void parseLogFile(String fileName);

}