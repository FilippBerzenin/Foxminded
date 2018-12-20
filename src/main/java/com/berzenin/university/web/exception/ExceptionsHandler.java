package com.berzenin.university.web.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
//@Controller
public class ExceptionsHandler {

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student Not Found")
	@ExceptionHandler(NotFoundException.class)
//	@ResponseBody
	protected ModelAndView handleThereIsStudentNotFoundException(HttpServletRequest req, NotFoundException e) {
		log.error("Student Not Found");
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("index");
	    return mav;
	}
}
