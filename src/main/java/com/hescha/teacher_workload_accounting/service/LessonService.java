package com.hescha.teacher_workload_accounting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hescha.teacher_workload_accounting.entity.Lesson;
import com.hescha.teacher_workload_accounting.repository.LessonRepository;


@Service
public class LessonService extends CrudImpl<Lesson> {

    public LessonRepository repository;

    @Autowired
    public LessonService(LessonRepository repository) {
        super(repository);
        this.repository = repository;
    }

}