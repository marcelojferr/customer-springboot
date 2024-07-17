package com.javaproject.customer_springboot.controller.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.dto.UserAuthenticationResponseDTO;

@ExtendWith(SpringExtension.class)
public class UserAuthenticationResponseDTOTest {
	
	UserAuthenticationResponseDTO dto = new UserAuthenticationResponseDTO("Teste");

	@Test
    @DisplayName("Returns DTO")
    void userAuthenticationResponseDTOTest() {

		Assertions.assertThat(dto).isNotNull();
		Assertions.assertThat(dto.toString()).isEqualTo(dto.toString());
		Assertions.assertThat(dto.hashCode()).isEqualTo(dto.hashCode());
		Assertions.assertThat(dto.equals(dto));
    }
}
