package com.javaproject.customer_springboot.controller.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.dto.ErrorDTO;
import com.javaproject.customer_springboot.errorhandler.RestExceptionHandler;
import com.javaproject.customer_springboot.exception.CustomerException;
import com.javaproject.customer_springboot.exception.UserException;

@ExtendWith(SpringExtension.class)
class ErroHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandlerMock;

    @Mock
    ErrorDTO errorDTO;
    
    @Mock
    CustomerException customerException = new CustomerException();

    @Mock
    UserException userException = new UserException();

    @Mock
    DataIntegrityViolationException dataIntegrityViolationException = new DataIntegrityViolationException(null);

    @Mock
    IllegalArgumentException illegalArgumentException = new IllegalArgumentException();

    @Mock
    DataAccessException dataAccessException;
    
	@BeforeEach
    void setUp() {
    	
		errorDTO = ErrorDTO.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Customer error")
            .details("Teste")
            .developerMessage("Teste")
            .build();
		
    }
    
    @Test
    @DisplayName("Returns customer exception")
    void customerExceptionTest() {
    	
        ResponseEntity<ErrorDTO> error = restExceptionHandlerMock
        		.handleCustomerException(customerException);
        
        assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
        assertEquals(error.getBody(), error.getBody());

        Assertions.assertThat(error).isNotNull();
        Assertions.assertThat(error.getBody()).isEqualTo(error.getBody());
    }
    
    @Test
    @DisplayName("Returns user exception")
    void userExceptionTest() {

        ResponseEntity<ErrorDTO> error = restExceptionHandlerMock
        		.handleUserException(userException);
        
        assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
        assertEquals(error.getBody(), error.getBody());

        Assertions.assertThat(error).isNotNull();
        Assertions.assertThat(error.getBody()).isEqualTo(error.getBody());
    }
    
    @Test
    @DisplayName("Returns integrity violation exception")
    void dataIntegrityViolationExceptionExceptionTest() {

        ResponseEntity<ErrorDTO> error = restExceptionHandlerMock
        		.handleDataIntegrityException(dataIntegrityViolationException);
        
        assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
        assertEquals(error.getBody(), error.getBody());

        Assertions.assertThat(error).isNotNull();
        Assertions.assertThat(error.getBody()).isEqualTo(error.getBody());
    }
    
    @Test
    @DisplayName("Returns illegal argument exception")
    void illegalArgumentExceptionTest() {

        ResponseEntity<ErrorDTO> error = restExceptionHandlerMock
        		.handleIllegalArgumentException(illegalArgumentException);
        
        assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
        assertEquals(error.getBody(), error.getBody());

        Assertions.assertThat(error).isNotNull();
        Assertions.assertThat(error.getBody()).isEqualTo(error.getBody());
    }
    
//    @Test
    @DisplayName("Returns data access exception")
    void dataAccessExceptionTest() {

        ResponseEntity<ErrorDTO> error = restExceptionHandlerMock
        		.handleDataAccessException(dataAccessException);
        
        assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
        assertEquals(error.getBody(), errorDTO);

        Assertions.assertThat(error).isNotNull();
        Assertions.assertThat(error.getBody()).isEqualTo(errorDTO);
    }

}