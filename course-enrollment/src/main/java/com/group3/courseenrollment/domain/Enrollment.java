package com.group3.courseenrollment.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;


@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@FutureOrPresent
	private LocalDate enrolStartDate;
	@Future
	private LocalDate enrolEndDate;

	public Enrollment(LocalDate enrolStartDate, LocalDate enrolEndDate) {
		super();
		this.enrolStartDate = enrolStartDate;
		this.enrolEndDate = enrolEndDate;
	}

	public Enrollment() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getEnrolStartDate() {
		return enrolStartDate;
	}

	public void setEnrolStartDate(LocalDate enrolStartDate) {
		this.enrolStartDate = enrolStartDate;
	}

	public LocalDate getEnrolEndDate() {
		return enrolEndDate;
	}

	public void setEnrolEndDate(LocalDate enrolEndDate) {
		this.enrolEndDate = enrolEndDate;
	}
}




