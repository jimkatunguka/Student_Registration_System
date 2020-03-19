package com.group3.courseenrollment.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Data
@Entity
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Enrollment> enrollmentList = new ArrayList<>();
	@Valid
	@OneToOne(cascade = CascadeType.PERSIST)
	private Faculty faculty;
	public Section(Faculty faculty) {
		super();
		this.faculty = faculty;
	}
	
	public Section() {
		
	}

}
