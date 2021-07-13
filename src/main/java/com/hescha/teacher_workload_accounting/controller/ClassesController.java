package com.hescha.teacher_workload_accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hescha.teacher_workload_accounting.entity.Classes;
import com.hescha.teacher_workload_accounting.service.ClassesService;

@Controller
@RequestMapping("/classess")
public class ClassesController {

	@Autowired
	private ClassesService service;

	@GetMapping
	public String getList(Model model) {
		model.addAttribute("list", service.repository.findAll());
		return "classess";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {

		if (id != null) {
			Classes entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Classes());
		}
		return "classess-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/classess";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Classes entity) {
		service.create(entity);
		return "redirect:/classess";
	}

}
