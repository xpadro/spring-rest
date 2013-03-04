package xpadro.spring.rest.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import xpadro.spring.rest.exception.PersonNotFoundException;
import xpadro.spring.rest.validator.PersonValidator;

@ControllerAdvice
public class CentralControllerHandler {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new PersonValidator());
    }
	
	@ExceptionHandler({PersonNotFoundException.class})
	public ResponseEntity<String> handleProductNotFound(PersonNotFoundException pe) {
		return new ResponseEntity<String>(pe.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException pe) {
		return new ResponseEntity<String>(pe.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
