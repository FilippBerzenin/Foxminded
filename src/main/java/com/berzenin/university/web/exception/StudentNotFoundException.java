package com.berzenin.university.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student Not Found") // 404
public class StudentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3332292346834265371L;

	public StudentNotFoundException() {
		super();
	}
	
	public StudentNotFoundException(String s) {
		super(s);
	}

	public StudentNotFoundException(Long id) {
		super(id.toString());
	}
}
