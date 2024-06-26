package com.javaproject.customer_springboot.model;
import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.javaproject.customer_springboot.dto.AddressDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENDERECO_CLIENTE")
public class Address implements Serializable {

	private static final long serialVersionUID = 5840641625637338485L;

	public Address(AddressDTO addressDTO) {
		BeanUtils.copyProperties(addressDTO, this);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "LOGRADOURO")
	private String streetAddress;
	
	@Column(name = "NUMERO")
	private String numberAddress;
	
	@Column(name = "BAIRRO")
	private String districtAddress;
	
	@Column(name = "CIDADE")
	private String cityAddress;

	@Column(name = "ESTADO")
	private String stateAddress;
	
	@Column(name = "CEP")
	private String zipAddress;
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
}
