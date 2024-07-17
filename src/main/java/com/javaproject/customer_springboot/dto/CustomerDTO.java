package com.javaproject.customer_springboot.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaproject.customer_springboot.enums.CustomerStatus;
import com.javaproject.customer_springboot.exception.UserException;
import com.javaproject.customer_springboot.model.Customer;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
	
	public CustomerDTO(Customer customer) {
		try {
			BeanUtils.copyProperties(customer, this);
			this.address = new AddressDTO(customer.getAddress());
			this.segment = new SegmentDTO(customer.getSegment());
			this.contact.addAll(customer.getContact().stream()
				.map(contact -> new ContactDTO(contact)).collect(Collectors.toSet()));
		}
		catch(BeansException e) {
			new UserException("Erro ao carregar dados do usuário");
		}
	}

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
	private SegmentDTO segment;
	
    @AddressValidation(message = "O endereço do cliente é obrigatório")
	@JsonProperty("customerAdress")
	private AddressDTO address;
	
    @ContactValidation
	@JsonProperty("contact")
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
        		+ ", customerSegment=" + segment 
        		+ ", customerAdress=" + address
        		+ ", customerContact=" + contact 
        		+ ", customerStatus=" + customerStatus + "]";
    }

}
