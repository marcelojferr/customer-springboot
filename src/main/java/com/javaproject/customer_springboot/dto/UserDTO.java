package com.javaproject.customer_springboot.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaproject.customer_springboot.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO {
	
	public UserDTO(User user) {
		BeanUtils.copyProperties(user, this);
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;

	@Size(min = 8,  max = 16, message = "O usuário precisa conter entre 8 e 16 caracteres")
	@JsonProperty("userLogin")
	private String userLogin;

    @Size(min = 8, max = 16, message = "A senha precisa conter entre 8 e 16 caracteres")
	@JsonProperty("password")
	private String password;

    @NotBlank(message = "Campo userName é obrigatório")
	@JsonProperty("userName")
	private Long userName;

    @NotBlank(message = "Campo email é obrigatório")
	@JsonProperty("email")
	private String email;
	
    @Override
    public String toString() {
        return "User [id=" + id 
        		+ ", userLogin=" + userLogin
        		+ ", userName=" + userName 
        		+ ", email=" + email + "]";
    }
}
