package com.example.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.payloads.APIResponse;

@RestControllerAdvice // this will make this class as exception handler for global exception
public class HandleException {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> resouceNotFoundException(ResourceNotFoundException ex){
		String msg = ex.getMessage();
		APIResponse apiResponse = new APIResponse(msg,false);
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((e)->{
			String fieldName =  ((FieldError)e).getField();
			String msg = e.getDefaultMessage();
			resp.put(fieldName, msg);
		});
		
		return new ResponseEntity<Map<String, String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	
}
