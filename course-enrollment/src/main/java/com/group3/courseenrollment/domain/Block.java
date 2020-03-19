package com.group3.courseenrollment.domain;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Block {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Size(min=3, max=7)
	private String code;
	@NotBlank
	@Size(max=45)
	private String name;
	@NotBlank
	@Size(max=45)
	private String semester;
	@NotBlank
	@Size(max=5)
	private String blockSeqNum;
	@FutureOrPresent
	private LocalDate startDate;
	@Future
	private LocalDate endDate;

	public Block(String code, String name, String semester, String blockSeqNum, LocalDate startDate, LocalDate endDate) {
		super();
		this.code = code;
		this.name = name;
		this.semester = semester;
		this.blockSeqNum = blockSeqNum;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Block() {

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getBlockSeqNum() {
		return blockSeqNum;
	}

	public void setBlockSeqNum(String blockSeqNum) {
		this.blockSeqNum = blockSeqNum;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
