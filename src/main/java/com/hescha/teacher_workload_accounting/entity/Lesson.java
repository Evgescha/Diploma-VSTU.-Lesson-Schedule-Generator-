package com.hescha.teacher_workload_accounting.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Lesson extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "classes_id")
	private Classes classes;
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;

	private int day;

	private int time;

	@Override
	public String toString() {
		return "Lesson [classes=" + classes.getNumber() 
		+ ", teacher=" + teacher.getName() 
		+ ", subject=" + subject.getName() 
		+ ", day=" + day
		+ ", time=" + time + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return Objects.equals(classes, other.classes) && day == other.day && Objects.equals(subject, other.subject)
				&& Objects.equals(teacher, other.teacher) && time == other.time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(classes, day, subject, teacher, time);
		return result;
	}

}
