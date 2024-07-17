package com.javaproject.customer_springboot.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaproject.customer_springboot.model.Contact;
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
public class ContactDTO {

	public ContactDTO(Contact contact) {
		BeanUtils.copyProperties(contact, this);
	}
	
	private Long id;
	
	@JsonProperty("contactNumber")
	private String contactNumber;
	
	@JsonProperty("contactResponsable")
	private String contactResponsable;
		
	@JsonProperty("contactZone")
	private String contactZone;
}
