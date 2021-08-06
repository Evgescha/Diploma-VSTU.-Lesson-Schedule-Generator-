package com.hescha.teacher_workload_accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hescha.teacher_workload_accounting.entity.Teacher;
import com.hescha.teacher_workload_accounting.service.SubjectService;
import com.hescha.teacher_workload_accounting.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

  @Autowired
  private TeacherService service;
  @Autowired
  private SubjectService subjectService;

  @GetMapping
  public String getList(Model model) {
    model.addAttribute("list", service.repository.findAll());
    return "teachers";
  }

  @RequestMapping(path = {"/edit", "/edit/{id}"})
  public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {

    if (id != null) {
      Teacher entity = service.read(id);
      model.addAttribute("entity", entity);
    } else {
      model.addAttribute("entity", new Teacher());
    }
    model.addAttribute("subjects", subjectService.repository.findAll());
    return "teachers-add-edit";
  }

  @RequestMapping(path = "/delete/{id}")
  public String delete(Model model, @PathVariable("id") Long id) {
    service.delete(id);
    return "redirect:/teachers";
  }

  @RequestMapping(path = "/create", method = RequestMethod.POST)
  public String createOrUpdate(Teacher entity,
      @RequestParam(value = "subjectId", required = true) int[] subjectId) {
    entity.getSubjects().clear();
    for (int id : subjectId) {
      entity.getSubjects().add(subjectService.read(id));
    }
    service.create(entity);
    return "redirect:/teachers";
  }
}
