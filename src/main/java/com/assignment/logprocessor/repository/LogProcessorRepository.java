package com.assignment.logprocessor.repository;

import com.assignment.logprocessor.domain.entity.LogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogProcessorRepository extends CrudRepository<LogEntity, String> {

}