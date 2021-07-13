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
import com.hescha.teacher_workload_accounting.service.LessonService;
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
		lessons = new Lesson[5][11][7];
		subjects = subjectService.readAll();
		teachers = teacherService.readAll();
		classes = classesService.readAll();
		subjectTimeOnWeeks = classesSubjectTimeOnWeekService.readAll();
		
		makeList();
		model.addAttribute("list", lessons);
		
		return "generation";
	}
	

	private void makeList() {
		int iter = 0;
		while (iter <= 1_000_000 && subjectTimeOnWeeks.size() > 0) {
			iter++;

			if(iter%1000==0) {
				System.out.println("iter: "+iter);
			}
			
			
			// получаем случайное требование
			int int1 = rand.nextInt(subjectTimeOnWeeks.size());
			ClassesSubjectTimeOnWeek subjectTimeOnWeek = subjectTimeOnWeeks.get(int1);

			// получаем случайный день недели
			int numberOfCurrentDay = rand.nextInt(lessons.length);
			Lesson[][] randomDay = lessons[numberOfCurrentDay];

			// получаем все уроки класса
			Lesson[] classLessons = randomDay[subjectTimeOnWeek.getClasses().getNumber()-1];
			// получаем случайный номер урока по счету выбоанного класса в выбранный день
			int lessonOnDayNumber = rand.nextInt(classLessons.length);
			Lesson lesson = classLessons[lessonOnDayNumber];
			// если место не свободно - пропустить
			if (lesson != null) {
				continue;
			}

			// если свободно - выбрать препода, проверить на совместимость
			Set<Teacher> subjectTeachers = subjectTimeOnWeek.getSubject().getTeachers();
			Object[] array = subjectTeachers.toArray();
			Teacher teacher = (Teacher) array[rand.nextInt(array.length)];
			// проверяем, нет ли в этот же день у других классов с этим же преподом урока в
			// это же время
			boolean canSetLesson = checkPossibleSetLesson(randomDay, lessonOnDayNumber, teacher);
			if (!canSetLesson) {
				continue;
			}
			
			
			//создаем урок
			Lesson newLesson = new Lesson();
			newLesson.setClasses(subjectTimeOnWeek.getClasses());
			newLesson.setTeacher(teacher);
			newLesson.setDay(numberOfCurrentDay);
			newLesson.setTime(lessonOnDayNumber);
			newLesson.setSubject(subjectTimeOnWeek.getSubject());
			lessons[numberOfCurrentDay][subjectTimeOnWeek.getClasses().getNumber()-1][lessonOnDayNumber] = newLesson;


			subjectTimeOnWeek.setHoursInWeek(subjectTimeOnWeek.getHoursInWeek()-1);
			if(subjectTimeOnWeek.getHoursInWeek()==0) {
				subjectTimeOnWeeks.remove(int1);
			}
		}

		System.out.println("iteration: "+iter);

	}

	private boolean checkPossibleSetLesson(Lesson[][] randomDay, int lessonOnDayNumber, Teacher teacher) {
		for (Lesson[] lesonAllClassesInCurrentDay : randomDay) {
			if (lesonAllClassesInCurrentDay[lessonOnDayNumber] != null
					&& lesonAllClassesInCurrentDay[lessonOnDayNumber].getTeacher().equals(teacher)) {
				return false;
			}
		}
		return true;
	}


}
