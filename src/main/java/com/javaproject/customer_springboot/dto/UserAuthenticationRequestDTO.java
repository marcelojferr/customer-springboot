package com.javaproject.customer_springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public class UserAuthenticationRequestDTO {
	
	@Size(min = 8,  max = 16, message = "O usuário precisa conter entre 8 e 16 caracteres")
	@JsonProperty("userLogin")
	private String userLogin;
	
    @Size(min = 8, max = 16, message = "A senha precisa conter entre 8 e 16 caracteres")
	@JsonProperty("password")
	private String password;
    
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
