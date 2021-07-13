package com.hescha.teacher_workload_accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hescha.teacher_workload_accounting.entity.Subject;
import com.hescha.teacher_workload_accounting.service.SubjectService;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

	@Autowired
	private SubjectService service;

	@GetMapping
	public String getList(Model model) {
		model.addAttribute("list", service.repository.findAll());
		return "subjects";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {
		if (id != null) {
			Subject entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Subject());
		}
		return "subjects-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/subjects";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Subject entity) {
		service.create(entity);
		return "redirect:/subjects";
	}

}
