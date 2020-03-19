package com.group3.courseenrollment.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Entry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Size(max=45)
	private String name;
	@FutureOrPresent
	private LocalDate startDate;
	@FutureOrPresent
	private LocalDate enrolStartDate;
	@Future
	private LocalDate enrolEndDate;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Student> studentList = new ArrayList<Student>();
	private Boolean hasWriteAccess;

	public Entry(String name, LocalDate startDate, LocalDate enrolStartDate,LocalDate enrolEndDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.enrolStartDate = enrolStartDate;
		this.enrolEndDate = enrolEndDate;
		this.hasWriteAccess = true;
	}

	public Entry() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEnrolStartDate() {
		return enrolStartDate;
	}

	public void setEnrolStartDate(LocalDate enrolStartDate) {
		this.enrolStartDate = enrolStartDate;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Student> getStudents() {
		return studentList;
	}

	public void setStudents(List<Student> students) {
		this.studentList = students;
	}

	public void addStudent(Student student) {
		studentList.add(student);
	}

	public void setHasWriteAccess(Boolean hasWriteAccess) {
		this.hasWriteAccess = hasWriteAccess;
	}

	public Boolean getHasWriteAccess() {
		return hasWriteAccess;
	}

	public LocalDate getEnrolEndDate() {
		return enrolEndDate;
	}

	public void setEnrolEndDate(LocalDate enrolEndDate) {
		this.enrolEndDate = enrolEndDate;
	}
}
