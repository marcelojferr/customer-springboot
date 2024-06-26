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
public class AddressDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	
	@JsonProperty("streetAddress")
	private String streetAddress;
	
	@JsonProperty("numberAddress")
	private String numberAddress;
	
	@JsonProperty("districtAddress")
	private String districtAddress;
	
	@JsonProperty("cityAddress")
	private String cityAddress;

	@JsonProperty("stateAddress")
	private String stateAddress;
	
	@JsonProperty("zipAddress")
	private String zipAddress;
	
	@JsonProperty("customerNumber")
	private String customerNumber;
	
}
