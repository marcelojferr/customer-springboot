package com.javaproject.customer_springboot.controller.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.model.Contact;
import com.javaproject.customer_springboot.model.Customer;

@ExtendWith(SpringExtension.class)
public class ContactTest {
	
	Contact dto;
	Contact dto2 = new Contact();

	@Test
    @DisplayName("Returns entity")
    void contactTest() {
    	
		dto  = new Contact();
		
		dto2.setId(1L);
		dto2.setContactNumber("Teste");
		dto2.setContactResponsable("Teste");
		dto2.setContactZone("Teste");
		dto2.setCustomer(new Customer());
		
		Assertions.assertThat(dto).isNotNull();
		Assertions.assertThat(dto.toString()).isEqualTo(dto.toString());
		Assertions.assertThat(dto.hashCode()).isEqualTo(dto.hashCode());
		Assertions.assertThat(dto.equals(dto));
		
		Assertions.assertThat(dto2).isNotNull();
		Assertions.assertThat(dto2.toString()).isEqualTo(dto2.toString());
		Assertions.assertThat(dto2.hashCode()).isEqualTo(dto2.hashCode());
		Assertions.assertThat(dto2.equals(dto2));
    }
}
