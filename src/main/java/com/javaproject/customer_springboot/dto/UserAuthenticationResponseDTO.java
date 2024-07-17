package com.javaproject.customer_springboot.dto;

import lombok.Builder;

@Builder
public class UserAuthenticationResponseDTO {
	
	private String token;

	//TODO implementar retornar o usuario e liberacoes (authorities)
	
	public UserAuthenticationResponseDTO(String token) {
		super();
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
