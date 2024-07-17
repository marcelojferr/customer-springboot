package com.javaproject.customer_springboot.controller.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.dto.SegmentDTO;

@ExtendWith(SpringExtension.class)
public class SegmentDTOTest {
	
	SegmentDTO dto, dto2;

	@Test
    @DisplayName("Returns DTO")
    void segmentDTOTest() {
    	
		dto  = new SegmentDTO();
		dto2 = new SegmentDTO(1L,
				"Teste", "Teste");
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
