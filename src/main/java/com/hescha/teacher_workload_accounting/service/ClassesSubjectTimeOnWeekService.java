package com.hescha.teacher_workload_accounting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hescha.teacher_workload_accounting.entity.ClassesSubjectTimeOnWeek;
import com.hescha.teacher_workload_accounting.repository.ClassesSubjectTimeOnWeekRepository;


@Service
public class ClassesSubjectTimeOnWeekService extends CrudImpl<ClassesSubjectTimeOnWeek> {

    public ClassesSubjectTimeOnWeekRepository repository;

    @Autowired
    public ClassesSubjectTimeOnWeekService(ClassesSubjectTimeOnWeekRepository repository) {
        super(repository);
        this.repository = repository;
    }

}