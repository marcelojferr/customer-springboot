package com.javaproject.customer_springboot.controller.dto;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.javaproject.customer_springboot.dto.ErrorDTO;

@ExtendWith(SpringExtension.class)
public class ErrorDTOTest {
	
	ErrorDTO dto, dto2;
	ErrorDTO dto3 = new ErrorDTO();

	@Test
    @DisplayName("Returns DTO")
    void errorDTOTest() {
    	
		dto  = new ErrorDTO();
		dto2 = ErrorDTO.builder()
				.title("Teste")
				.details("Teste")
				.status(100)
				.developerMessage("Teste")
				.timestamp(LocalDateTime.now()).build();
		
		dto3.setDetails("Teste");
		dto3.setDeveloperMessage("Teste");
		dto3.setStatus(100);
		dto3.setTimestamp(LocalDateTime.now());
		dto3.setTitle("Teste");
		
		Assertions.assertThat(dto).isNotNull();
		Assertions.assertThat(dto.toString()).isEqualTo(dto.toString());
		Assertions.assertThat(dto.hashCode()).isEqualTo(dto.hashCode());
		Assertions.assertThat(dto.equals(dto));
		
		Assertions.assertThat(dto2).isNotNull();
		Assertions.assertThat(dto2.toString()).isEqualTo(dto2.toString());
		Assertions.assertThat(dto2.hashCode()).isEqualTo(dto2.hashCode());
		Assertions.assertThat(dto2.equals(dto2));
		
		Assertions.assertThat(dto3).isNotNull();
		Assertions.assertThat(dto3.toString()).isEqualTo(dto3.toString());
		Assertions.assertThat(dto3.hashCode()).isEqualTo(dto3.hashCode());
		Assertions.assertThat(dto3.equals(dto3));
    }
}
