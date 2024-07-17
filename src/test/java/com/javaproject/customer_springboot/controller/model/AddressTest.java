package com.javaproject.customer_springboot.controller.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.model.Address;
import com.javaproject.customer_springboot.model.Customer;

@ExtendWith(SpringExtension.class)
public class AddressTest {
	
	Address dto;
	Address dto2 = new Address();


	@Test
    @DisplayName("Returns entity")
    void addressTest() {
    	
		dto  = new Address();
		
		dto2.setId(1L);
		dto2.setStreetAddress("Teste");
		dto2.setNumberAddress("10");
		dto2.setDistrictAddress("Teste");
		dto2.setCityAddress("Teste");
		dto2.setZipAddress("0111000");
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
