package com.familytree.controller.advice;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.familytree.common.exception.NotFoundException;

//@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler({NotFoundException.class, NoResultException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(Exception e, Model model) {
		model.addAttribute("msg", e.getMessage());
	    return "Error";
	  }
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleHttpRequestMethodNotSupportedException(Exception e, Model model) {
		model.addAttribute("msg", "Nhập sai");
	    return "Error";
	  }
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException(Exception e, Model model) {
		model.addAttribute("msg", e);
	    return "Error";
	  }
}
