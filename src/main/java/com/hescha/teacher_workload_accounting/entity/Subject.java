package com.hescha.teacher_workload_accounting.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Data
@Entity
public class Subject extends AbstractEntity {

  private String name;

  @ManyToMany(mappedBy = "subjects", fetch = FetchType.EAGER)
  private Set<Teacher> teachers = new HashSet<>();
  @Fetch(value = FetchMode.SELECT)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "subject", cascade = CascadeType.PERSIST)
  private List<ClassesSubjectTimeOnWeek> subjectTimeOnWeeks = new ArrayList<>();
  @Fetch(value = FetchMode.SELECT)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "subject", cascade = CascadeType.PERSIST)
  private List<Lesson> lessons = new ArrayList<>();

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
    Subject other = (Subject) obj;
    return Objects.equals(name, other.name);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(name);
    return result;
  }

}
