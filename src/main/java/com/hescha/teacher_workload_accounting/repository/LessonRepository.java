package com.hescha.teacher_workload_accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hescha.teacher_workload_accounting.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}