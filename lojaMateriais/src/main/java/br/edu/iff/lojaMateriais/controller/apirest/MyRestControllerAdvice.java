package br.edu.iff.lojaMateriais.controller.apirest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MyRestControllerAdvice {

	@ExceptionHandler(Exception.class)
	
	public ResponseEntity erroException(Exception e, HttpServletRequest request)
	{
		
		Error erro = new Error();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		
	}
}
