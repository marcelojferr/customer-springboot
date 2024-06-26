package com.javaproject.customer_springboot.model;

import java.io.Serializable;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import com.javaproject.customer_springboot.dto.ContactDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "CONTATO_CLIENTE")
public class Contact implements Serializable {

	private static final long serialVersionUID = 3188785005158006826L;

	public Contact(Set<ContactDTO> contactDTO) {
		BeanUtils.copyProperties(contactDTO, this);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CONTATO_CLIENTE")
	private String contactNumber;
	
	@Column(name = "RESPONSAVEL_CONTATO")
	private String contactResponsable;
		
	@Column(name = "REGIAO_CONTATO")
	private String contactZone;

	@ManyToOne	
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
}
