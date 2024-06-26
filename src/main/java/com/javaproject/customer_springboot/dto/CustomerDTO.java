package com.javaproject.customer_springboot.dto;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaproject.customer_springboot.enums.CustomerStatus;
import com.javaproject.customer_springboot.model.Customer;
import com.javaproject.customer_springboot.model.Segment;
import com.javaproject.customer_springboot.validations.AddressValidation;
import com.javaproject.customer_springboot.validations.ContactValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerDTO {
	
	@Builder
	public CustomerDTO(Customer customer) {
		BeanUtils.copyProperties(customer, this);
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	
    @NotBlank(message = "Campo customerName é obrigatório")
	@JsonProperty("customerName")
	private String customerName;
	
    @NotBlank(message = "Campo customerFantasyName é obrigatório")
	@JsonProperty("customerFantasyName")
	private String customerFantasyName;
	
    @CNPJ(message = "Número de CNPJ inválido")
    @NotBlank(message = "Campo customerNumber é obrigatório")
	@JsonProperty("customerNumber")
	private String customerNumber;

    @NotBlank(message = "Campo customerSegment é obrigatório")
	@JsonProperty("customerSegment")
	private Segment customerSegment;
	
    @AddressValidation(message = "O endereço do cliente é obrigatório")
	@JsonProperty("customerAdress")
	private AddressDTO address;
	
    @ContactValidation
	@JsonProperty("customerContact")
	private Set<ContactDTO> contact = new HashSet<>();
	
    @NotBlank(message = "Campo customerStatus é obrigatório")
	@JsonProperty("customerStatus")
	private CustomerStatus customerStatus;
	
    @Override
    public String toString() {
        return "User [id=" + id 
        		+ ", customerName=" + customerName
        		+ ", customerFantasyName=" + customerFantasyName 
        		+ ", customerNumber=" + customerNumber
        		+ ", customerSegment=" + customerSegment 
        		+ ", customerAdress=" + address
        		+ ", customerContact=" + contact 
        		+ ", customerStatus=" + customerStatus + "]";
    }

}
