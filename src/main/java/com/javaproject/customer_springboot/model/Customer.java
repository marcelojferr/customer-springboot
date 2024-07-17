package com.javaproject.customer_springboot.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.javaproject.customer_springboot.dto.AddressDTO;
import com.javaproject.customer_springboot.dto.ContactDTO;
import com.javaproject.customer_springboot.dto.CustomerDTO;
import com.javaproject.customer_springboot.dto.SegmentDTO;
import com.javaproject.customer_springboot.enums.CustomerStatus;
import com.javaproject.customer_springboot.exception.UserException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class Customer implements Serializable {

	private static final long serialVersionUID = 4933790906258654972L;

	public Customer(CustomerDTO customerDTO) {
		try {
			BeanUtils.copyProperties(customerDTO, this);
			this.address = new Address(customerDTO.getAddress());
			this.segment = new Segment(customerDTO.getSegment());
			this.contact.addAll(customerDTO.getContact().stream()
				.map(contact -> new Contact(contact)).collect(Collectors.toSet()));
		}
		catch(BeansException e) {
			new UserException("Erro ao carregar dados do usuário");
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME_CLIENTE", nullable = false)
	private String customerName;
	
	@Column(name = "NOME_FANTASIA_CLIENTE", nullable = false)
	private String customerFantasyName;
	
	@Column(name = "CNPJ_CLIENTE", nullable = false, unique = true)
	private String customerNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEGMENTO_CLIENTE_id", nullable = false)
	private Segment segment;
	
	@OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Contact> contact = new HashSet<>();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS_CLIENTE", nullable = false)
	private CustomerStatus customerStatus;

}
