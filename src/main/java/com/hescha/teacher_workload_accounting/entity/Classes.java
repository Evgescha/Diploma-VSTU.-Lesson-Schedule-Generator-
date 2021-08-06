package com.hescha.teacher_workload_accounting.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Data
@Entity
public class Classes extends AbstractEntity {

  private int number;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "classes", cascade = CascadeType.PERSIST)
  private List<ClassesSubjectTimeOnWeek> subjectTimeOnWeeks = new ArrayList<>();

  @Fetch(value = FetchMode.SELECT)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "classes", cascade = CascadeType.PERSIST)
  private List<Lesson> lessons = new ArrayList<>();

  @Override
  public String toString() {
    return number + "";
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
    Classes other = (Classes) obj;
    return number == other.number;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(number);
    return result;
  }


}
