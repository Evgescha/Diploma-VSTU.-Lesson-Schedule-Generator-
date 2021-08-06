package com.hescha.teacher_workload_accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hescha.teacher_workload_accounting.entity.Lesson;
import com.hescha.teacher_workload_accounting.service.LessonService;

@Controller
@RequestMapping("/lessons")
public class LessonController {

  @Autowired
  private LessonService service;

  @GetMapping
  public String getList(Model model) {
    model.addAttribute("list", service.repository.findAll());
    return "lessons";
  }

  @RequestMapping(path = {"/edit", "/edit/{id}"})
  public String edit(Model model, @PathVariable(name = "id", required = false) Long id) {

    if (id != null) {
      Lesson entity = service.read(id);
      model.addAttribute("entity", entity);
    } else {
      model.addAttribute("entity", new Lesson());
    }
    return "lessons-add-edit";
  }

  @RequestMapping(path = "/delete/{id}")
  public String delete(Model model, @PathVariable("id") Long id) {
    service.delete(id);
    return "redirect:/lessons";
  }

  @RequestMapping(path = "/create", method = RequestMethod.POST)
  public String createOrUpdate(Lesson entity) {
    service.create(entity);
    return "redirect:/lessons";
  }
}
