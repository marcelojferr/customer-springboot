package com.javaproject.customer_springboot.controller.dto;

import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.dto.UserDetailsImplDTO;
import com.javaproject.customer_springboot.model.User;

@ExtendWith(SpringExtension.class)
public class UserDetailsDTOTest {
	
	UserDetailsImplDTO dto, dto2, dto3;

	@Test
    @DisplayName("Returns DTO")
    void userDetailsDTOTest() {
    	
		dto = new UserDetailsImplDTO();
		
		dto3 = UserDetailsImplDTO.build(new User());
		
		dto2 = UserDetailsImplDTO.builder()
			.id(1L)
			.userEmail("Teste")
			.userLogin("Teste")
			.userName("Teste")
			.password("Teste")
			.authorities(new ArrayList<>())
			.build();
		
		Assertions.assertThat(dto).isNotNull();
		Assertions.assertThat(dto.toString()).isEqualTo(dto.toString());
		Assertions.assertThat(dto.hashCode()).isEqualTo(dto.hashCode());
		Assertions.assertThat(dto.equals(dto));
		
		Assertions.assertThat(dto2).isNotNull();
		Assertions.assertThat(dto2.toString()).isEqualTo(dto2.toString());
		Assertions.assertThat(dto2.hashCode()).isEqualTo(dto2.hashCode());
		Assertions.assertThat(dto2.equals(dto2));
		
		Assertions.assertThat(dto2.getId().equals(null));
		Assertions.assertThat(dto2.getUserEmail().equals(dto2.getUserEmail()));
		Assertions.assertThat(dto2.getUserLogin().equals(dto2.getUserLogin()));
		Assertions.assertThat(dto2.getUsername().equals(dto2.getUsername()));
		Assertions.assertThat(dto2.getPassword().equals(dto2.getPassword()));
		Assertions.assertThat(dto2.getAuthorities().equals(dto2.getAuthorities()));
		
		Assertions.assertThat(dto2.isAccountNonExpired());
		Assertions.assertThat(dto2.isAccountNonLocked());
		Assertions.assertThat(dto2.isCredentialsNonExpired());
		Assertions.assertThat(dto2.isEnabled());
    }
}
