package com.berzenin.university.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student Not Found")
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	protected void handleThereIsStudentNotFoundException() {
		log.error("Student Not Found");;
	}
}
