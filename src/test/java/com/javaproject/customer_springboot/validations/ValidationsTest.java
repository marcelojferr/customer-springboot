package com.javaproject.customer_springboot.validations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.dto.AddressDTO;
import com.javaproject.customer_springboot.dto.ContactDTO;

import jakarta.validation.ConstraintValidatorContext;

@ExtendWith(SpringExtension.class)
public class ValidationsTest {
	
	ConstraintValidatorContext constraintValidatorContext;

	@Test
    @DisplayName("Returns entity")
    void addressValidatorTest() {
		
		AddressDTO dto = new AddressDTO(1L,
				"Teste", "Teste", "Teste",
				"Teste", "Teste", "Teste");

		AddressValidator validator = new AddressValidator();
		
		Assertions.assertThat(validator.isValid(dto, constraintValidatorContext));

	}

	@Test
    @DisplayName("Returns entity")
    void contactValidatorTest() {
		
		ContactDTO dto = new ContactDTO(1L,
				"Teste1234", "Teste1234", "Teste1234");

		ContactValidator validator = new ContactValidator();
		
		Assertions.assertThat(validator.isValid(dto, constraintValidatorContext));

	}
}
