package com.javaproject.customer_springboot.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaproject.customer_springboot.model.Segment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SegmentDTO {
	
	public SegmentDTO(Segment segment) {
		BeanUtils.copyProperties(segment, this);
	}
	private Long id;
	
	@JsonProperty("segmentDescription")
	private String segmentDescription;
	
	@JsonProperty("segmentPercentualValue")
	private String segmentPercentualValue;
}
