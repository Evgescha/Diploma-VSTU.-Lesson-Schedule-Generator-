package com.hescha.teacher_workload_accounting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hescha.teacher_workload_accounting.entity.Teacher;
import com.hescha.teacher_workload_accounting.repository.TeacherRepository;


@Service
public class TeacherService extends CrudImpl<Teacher> {

    public TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        super(repository);
        this.repository = repository;
    }

}