package com.hescha.teacher_workload_accounting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hescha.teacher_workload_accounting.entity.Subject;
import com.hescha.teacher_workload_accounting.repository.SubjectRepository;


@Service
public class SubjectService extends CrudImpl<Subject> {

  public SubjectRepository repository;

  @Autowired
  public SubjectService(SubjectRepository repository) {
    super(repository);
    this.repository = repository;
  }

}