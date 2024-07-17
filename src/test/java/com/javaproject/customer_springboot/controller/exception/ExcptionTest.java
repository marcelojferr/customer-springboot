package com.javaproject.customer_springboot.controller.exception;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.exception.CustomerException;
import com.javaproject.customer_springboot.exception.UserException;

@ExtendWith(SpringExtension.class)
public class ExcptionTest {

	@Test
    @DisplayName("Returns CustomerException")
	void customerExceptionTest() {

	    String expectedMessage = "For input string";
	    Throwable err = new Throwable();
	    
	    CustomerException excption = new CustomerException(expectedMessage, err);
		Assertions.assertThat(excption).isNotNull();
		Assertions.assertThat(excption.getMessage()).isEqualTo(expectedMessage);
	}

	@Test
    @DisplayName("Returns UserException")
	void userExceptionTest() {

	    String expectedMessage = "For input string";
	    Throwable err = new Throwable();
	    
	    UserException excption = new UserException(expectedMessage, err);
		Assertions.assertThat(excption).isNotNull();
		Assertions.assertThat(excption.getMessage()).isEqualTo(expectedMessage);
	}
}
