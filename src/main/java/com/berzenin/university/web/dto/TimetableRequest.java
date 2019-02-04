package com.berzenin.university.web.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.berzenin.university.model.persons.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableRequest {
	
	private Person person;
	
	public TimetableRequest(Long id, String name, String surename, LocalDate dateStartSearch, LocalDate dateFinishSearch) {
		super(id, name, surename);
		this.dateStartSearch = dateStartSearch;
		this.dateFinishSearch = dateFinishSearch;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateStartSearch;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateFinishSearch;

}
