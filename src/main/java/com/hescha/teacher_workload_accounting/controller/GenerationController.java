package com.hescha.teacher_workload_accounting.controller;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hescha.teacher_workload_accounting.entity.Classes;
import com.hescha.teacher_workload_accounting.entity.ClassesSubjectTimeOnWeek;
import com.hescha.teacher_workload_accounting.entity.Lesson;
import com.hescha.teacher_workload_accounting.entity.Subject;
import com.hescha.teacher_workload_accounting.entity.Teacher;
import com.hescha.teacher_workload_accounting.service.ClassesService;
import com.hescha.teacher_workload_accounting.service.ClassesSubjectTimeOnWeekService;
import com.hescha.teacher_workload_accounting.service.SubjectService;
import com.hescha.teacher_workload_accounting.service.TeacherService;

@Controller
@RequestMapping("/generation")
public class GenerationController {

  @Autowired
  private ClassesSubjectTimeOnWeekService classesSubjectTimeOnWeekService;

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private ClassesService classesService;

  @Autowired
  private TeacherService teacherService;

  int iter;

  List<Subject> subjects;
  List<Teacher> teachers;
  List<Classes> classes;
  List<ClassesSubjectTimeOnWeek> subjectTimeOnWeeks;
  // день недели
  // класс
  // урок
  Lesson[][][] lessons = new Lesson[5][11][7];

  Random rand = new Random();


  @GetMapping
  public String getList(Model model) {

    readAllToGeneration();
      readAllToGeneration();
      makeList();

    model.addAttribute("list", lessons);
    model.addAttribute("message1", "Шагов генерации - " + iter);
    model.addAttribute("message2",
        "Осталось предметов " + subjectTimeOnWeeks.size()
            + " (" + subjectTimeOnWeeks + ")");
    return "generation";
  }

  private void readAllToGeneration() {
    lessons = new Lesson[5][11][7];
    subjects = subjectService.readAll();
    teachers = teacherService.readAll();
    classes = classesService.readAll();
    subjectTimeOnWeeks = classesSubjectTimeOnWeekService.readAll();
  }


  private void makeList() {
    iter = 0;
    while (iter < 1_000_000 && subjectTimeOnWeeks.size() > 0) {
      iter++;

      // получаем случайное требование
      int randomLessonConditionId = rand.nextInt(subjectTimeOnWeeks.size());
      ClassesSubjectTimeOnWeek subjectTimeOnWeek = subjectTimeOnWeeks.get(randomLessonConditionId);

      // получаем случайный день недели
      int randomDayOfWeekNumber = rand.nextInt(lessons.length);
      Lesson[][] randomDay = lessons[randomDayOfWeekNumber];

      // получаем все уроки класса
      Lesson[] classLessons = randomDay[subjectTimeOnWeek.getClasses().getNumber() - 1];
      // получаем случайный номер урока по счету выбранного класса в выбранный день
      int lessonOnDayNumber = rand.nextInt(classLessons.length);
      Lesson lesson = classLessons[lessonOnDayNumber];
      // если место не свободно - пропустить
      if (lesson != null) {
        continue;
      }

      // если свободно - выбрать препода, проверить на совместимость
      Teacher teacher = getTeacherBySubject(subjectTimeOnWeek);
      // проверяем, нет ли в этот же день у других классов с этим же преподом урока в
      // это же время
      boolean canSetLesson = checkPossibleSetLesson(randomDay, lessonOnDayNumber, teacher);
      if (!canSetLesson) {
        continue;
      }

      //создаем урок
      Lesson newLesson = createLesson(subjectTimeOnWeek, randomDayOfWeekNumber, lessonOnDayNumber,
          teacher);

      addLessonToArray(randomLessonConditionId, subjectTimeOnWeek, randomDayOfWeekNumber,
          lessonOnDayNumber, newLesson);
    }

  }

  private Teacher getTeacherBySubject(ClassesSubjectTimeOnWeek subjectTimeOnWeek) {
    Set<Teacher> subjectTeachers = subjectTimeOnWeek.getSubject().getTeachers();
    Object[] array = subjectTeachers.toArray();
    Teacher teacher = (Teacher) array[rand.nextInt(array.length)];
    return teacher;
  }

  private void addLessonToArray(int randomLessonConditionId,
      ClassesSubjectTimeOnWeek subjectTimeOnWeek,
      int randomDayOfWeekNumber, int lessonOnDayNumber, Lesson newLesson) {
    lessons[randomDayOfWeekNumber][subjectTimeOnWeek.getClasses().getNumber()
        - 1][lessonOnDayNumber] = newLesson;

    subjectTimeOnWeek.setHoursInWeek(subjectTimeOnWeek.getHoursInWeek() - 1);
    if (subjectTimeOnWeek.getHoursInWeek() == 0) {
      subjectTimeOnWeeks.remove(randomLessonConditionId);
    }
  }

  private Lesson createLesson(ClassesSubjectTimeOnWeek subjectTimeOnWeek, int randomDayOfWeekNumber,
      int lessonOnDayNumber, Teacher teacher) {
    Lesson newLesson = new Lesson();
    newLesson.setClasses(subjectTimeOnWeek.getClasses());
    newLesson.setTeacher(teacher);
    newLesson.setDay(randomDayOfWeekNumber);
    newLesson.setTime(lessonOnDayNumber);
    newLesson.setSubject(subjectTimeOnWeek.getSubject());
    return newLesson;
  }

  private boolean checkPossibleSetLesson(Lesson[][] randomDay, int lessonOnDayNumber,
      Teacher teacher) {
    for (Lesson[] lessonAllClassesInCurrentDay : randomDay) {
      if (lessonAllClassesInCurrentDay[lessonOnDayNumber] != null
          && lessonAllClassesInCurrentDay[lessonOnDayNumber].getTeacher().equals(teacher)) {
        return false;
      }
    }
    return true;
  }


}
