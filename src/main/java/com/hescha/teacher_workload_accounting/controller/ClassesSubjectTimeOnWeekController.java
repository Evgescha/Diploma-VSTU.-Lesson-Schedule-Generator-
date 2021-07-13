package com.hescha.teacher_workload_accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hescha.teacher_workload_accounting.entity.ClassesSubjectTimeOnWeek;
import com.hescha.teacher_workload_accounting.service.ClassesService;
import com.hescha.teacher_workload_accounting.service.ClassesSubjectTimeOnWeekService;
import com.hescha.teacher_workload_accounting.service.SubjectService;

@Controller
@RequestMapping("/classesSubjectTimeOnWeeks")
public class ClassesSubjectTimeOnWeekController {

	@Autowired
	private ClassesSubjectTimeOnWeekService service;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ClassesService classesService;

	@GetMapping
	public String getList(Model model) {
		model.addAttribute("list", service.repository.findAll());
		return "classesSubjectTimeOnWeeks";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id != null) {
			ClassesSubjectTimeOnWeek entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new ClassesSubjectTimeOnWeek());
		}
		model.addAttribute("subjects", subjectService.repository.findAll());
		model.addAttribute("classes", classesService.repository.findAll());
		return "classesSubjectTimeOnWeeks-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {
		ClassesSubjectTimeOnWeek read = service.read(id);
		read.setClasses(null);
		read.setSubject(null);
		service.update(read);
		service.delete(id);
		return "redirect:/classesSubjectTimeOnWeeks";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(ClassesSubjectTimeOnWeek entity, 
			@Param("subjectId") Long subjectId,
			@Param("classesId") Long classesId) {
		entity.setClasses(classesService.read(classesId));
		entity.setSubject(subjectService.read(subjectId));
		service.create(entity);
		return "redirect:/classesSubjectTimeOnWeeks";
	}
}
