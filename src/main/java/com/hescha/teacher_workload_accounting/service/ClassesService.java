package com.hescha.teacher_workload_accounting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hescha.teacher_workload_accounting.entity.Classes;
import com.hescha.teacher_workload_accounting.repository.ClassesRepository;


@Service
public class ClassesService extends CrudImpl<Classes> {

    public ClassesRepository repository;

    @Autowired
    public ClassesService(ClassesRepository repository) {
        super(repository);
        this.repository = repository;
    }

}