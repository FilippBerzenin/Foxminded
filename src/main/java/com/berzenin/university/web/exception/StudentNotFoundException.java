package com.berzenin.university.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student Not Found") // 404
public class StudentNotFoundException extends Exception {
	private static final long serialVersionUID = -3332292346834265371L;
	
	public StudentNotFoundException() {
	}

	public StudentNotFoundException(String id) {
		super(id);
	}
}
