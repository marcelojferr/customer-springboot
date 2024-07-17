package com.javaproject.customer_springboot.controller.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.model.Segment;

@ExtendWith(SpringExtension.class)
public class SegmentTest {
	
	Segment dto;
	Segment dto2 = new Segment();

	@Test
    @DisplayName("Returns entity")
    void segmentTest() {
    	
		dto  = new Segment();
		
		dto2.setId(1L);
		dto2.setSegmentDescription("Teste");
		dto2.setSegmentPercentualValue("Teste");
		
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
