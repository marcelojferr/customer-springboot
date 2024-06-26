package com.javaproject.customer_springboot.dto;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ContactDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	
	@JsonProperty("contactNumber")
	private String contactNumber;
	
	@JsonProperty("contactResponsable")
	private String contactResponsable;
		
	@JsonProperty("contactZone")
	private String contactZone;

	@JsonProperty("customerNumber")
	private String customerNumber;
}
