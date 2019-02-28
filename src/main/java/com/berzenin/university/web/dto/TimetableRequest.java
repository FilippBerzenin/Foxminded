package com.berzenin.university.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimetableRequest {	
	
	private long id;
	
	@NotNull
	@Size(min=2, max=50)
	private String name;
	
	@NotNull
	@Size(min=2, max=50)
	private String surename;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateStartSearch;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFinishSearch;
	
	public TimetableRequest(Long id, String name, String surename, LocalDate dateStartSearch, LocalDate dateFinishSearch) {
		this.id=id;
		this.name=name;
		this.surename=surename;
		this.dateStartSearch = dateStartSearch;
		this.dateFinishSearch = dateFinishSearch;
	}

	public TimetableRequest() {
	}

}
