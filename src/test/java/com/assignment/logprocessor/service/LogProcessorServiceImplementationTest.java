package com.assignment.logprocessor.service;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.assignment.logprocessor.domain.entity.LogEntity;
import com.assignment.logprocessor.mapper.LogDtoToLogEntityMapper;
import com.assignment.logprocessor.repository.LogProcessorRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LogProcessorServiceImplementationTest {

  @Mock
  private LogProcessorRepository repository;

  @Spy
  private LogDtoToLogEntityMapper mapper;

  @InjectMocks
  private LogProcessorServiceImplementation logProcessorServiceImplementation;

  private static final String FILENAME = "D:\\logprocessor\\src\\test\\resources\\logfile.txt";

  //Please uncomment @Test annotation and provide absolute path of the file placed in resources to run this test
  //@Test
  public void parseLogFileStoresDataInDBWhenCorrectPathAndInputFileAreProvide() {
    ArgumentCaptor<LogEntity> argumentCaptor = ArgumentCaptor.forClass(LogEntity.class);
    logProcessorServiceImplementation.parseLogFile(FILENAME);
    verify(repository, times(3)).save(argumentCaptor.capture());
    List<LogEntity> logEntityList = argumentCaptor.getAllValues();
    assertEquals("scsmbstgra", logEntityList.get(0).getEventId());
    assertEquals("12345", logEntityList.get(0).getHost());
    assertEquals("APPLICATION_LOG", logEntityList.get(0).getType());
    assertEquals("5ms", logEntityList.get(0).getEventDuration());
    assertEquals(TRUE, logEntityList.get(0).getAlert());
  }

  @Test
  public void parseLogFileGetsExceptionWhenIncorrectPathIsProvided() {
    ArgumentCaptor<LogEntity> argumentCaptor = ArgumentCaptor.forClass(LogEntity.class);
    logProcessorServiceImplementation.parseLogFile("xyz-path");
    verify(repository, times(0)).save(any(LogEntity.class));
  }
}
