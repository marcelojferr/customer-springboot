package com.javaproject.customer_springboot.errorhandler;

import java.time.LocalDateTime;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.javaproject.customer_springboot.dto.ErrorDTO;
import com.javaproject.customer_springboot.exception.CustomerException;
import com.javaproject.customer_springboot.exception.UserException;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleCustomerException(CustomerException customerException) {
    	log.error("Customer error");
        return new ResponseEntity<>(
        		ErrorDTO.builder()
	                .timestamp(LocalDateTime.now())
	                .status(HttpStatus.BAD_REQUEST.value())
	                .title("Customer error")
	                .details(customerException.getMessage())
	                .developerMessage(customerException.getClass().getName())
	                .build(), HttpStatus.BAD_REQUEST);
	    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleUserException(UserException userException) {
    	log.error("User error");
        return new ResponseEntity<>(
        		ErrorDTO.builder()
	                .timestamp(LocalDateTime.now())
	                .status(HttpStatus.BAD_REQUEST.value())
	                .title("User error")
	                .details(userException.getMessage())
	                .developerMessage(userException.getClass().getName())
	                .build(), HttpStatus.BAD_REQUEST);
	    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleDataIntegrityException(DataIntegrityViolationException dataIntegrityViolationException) {
    	log.error("Data Integrity error");
        return new ResponseEntity<>(
        		ErrorDTO.builder()
	                .timestamp(LocalDateTime.now())
	                .status(HttpStatus.BAD_REQUEST.value())
	                .title("Data Integrity error")
	                .details(dataIntegrityViolationException.getMessage())
	                .developerMessage(dataIntegrityViolationException.getClass().getName())
	                .build(), HttpStatus.BAD_REQUEST);
	    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> IllegalArgumentException(IllegalArgumentException  illegalArgumentException ) {
    	log.error("Illegal argument error");
        return new ResponseEntity<>(
        		ErrorDTO.builder()
	                .timestamp(LocalDateTime.now())
	                .status(HttpStatus.BAD_REQUEST.value())
	                .title("Illegal argument error")
	                .details(illegalArgumentException.getMessage())
	                .developerMessage(illegalArgumentException.getClass().getName())
	                .build(), HttpStatus.BAD_REQUEST);
	    }
    
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> dataAccessException(DataAccessException  dataAccessException ) {
    	log.error("Data access error");
        return new ResponseEntity<>(
        		ErrorDTO.builder()
	                .timestamp(LocalDateTime.now())
	                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .title("Data access error")
	                .details(dataAccessException.getMessage())
	                .developerMessage(dataAccessException.getClass().getName())
	                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
     
}
