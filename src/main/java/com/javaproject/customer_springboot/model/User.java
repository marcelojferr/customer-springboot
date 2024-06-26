package com.javaproject.customer_springboot.model;

import java.io.Serializable;
import org.springframework.beans.BeanUtils;
import com.javaproject.customer_springboot.dto.UserDTO;
import com.javaproject.customer_springboot.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
public class User implements Serializable {

	private static final long serialVersionUID = -356595128487711727L;

	@Builder
	public User(UserDTO userDTO) {
		BeanUtils.copyProperties(userDTO, this);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "LOGIN_USUARIO", nullable = false, unique=true)
	private String userLogin;

	@Column(name = "SENHA_USUARIO", nullable = false)
	private String password;

	@Column(name = "NOME_USUARIO", nullable = false)
	private String userName;

	@Email
	@Column(name = "EMAIL_USUARIO", nullable = false)
	private String userEmail;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS_USUARIO", nullable = false)
	private UserStatus userStatus;
}
