package com.hescha.teacher_workload_accounting.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ClassesSubjectTimeOnWeek extends AbstractEntity {
	@ManyToOne( cascade = CascadeType.DETACH)
	@JoinColumn(name = "classes_id")
	private Classes classes;

	@ManyToOne( cascade = CascadeType.DETACH)
	@JoinColumn(name = "subject_id")
	private Subject subject;

	private int hoursInWeek;

	@Override
	public String toString() {
		return "ClassesSubjectTimeOnWeek [classes=" + classes.getNumber() + ", subject=" + subject.getName() + ", hoursInWeek=" + hoursInWeek
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassesSubjectTimeOnWeek other = (ClassesSubjectTimeOnWeek) obj;
		return Objects.equals(classes, other.classes) && hoursInWeek == other.hoursInWeek
				&& Objects.equals(subject, other.subject);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(classes, hoursInWeek, subject);
		return result;
	}

}
