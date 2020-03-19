package com.group3.courseenrollment.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
public class Offering {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String code;
	@Valid
	@OneToOne(cascade = CascadeType.PERSIST)
	private Course course;
	@Valid
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Block block;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Section>sections = new ArrayList<>();

	public Offering(Course course, Block block) {
		super();
		this.code = course.getCode()+"-"+block.getCode();
		this.course = course;
		this.block = block;
	}

	public Offering() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
}
