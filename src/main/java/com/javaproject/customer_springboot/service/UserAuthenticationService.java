package com.javaproject.customer_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.javaproject.customer_springboot.dto.UserAuthenticationRequestDTO;
import com.javaproject.customer_springboot.dto.UserAuthenticationResponseDTO;
import com.javaproject.customer_springboot.dto.UserDetailsImplDTO;
import com.javaproject.customer_springboot.exception.UserException;
import com.javaproject.customer_springboot.security.jwt.JwtUtils;

@Service
public class UserAuthenticationService {
 
	@Autowired
	private AuthenticationManager authenticatioManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public UserAuthenticationResponseDTO login(UserAuthenticationRequestDTO authDto) {
		
		try {
			//Cria mecanismo de credencial para o spring
			UsernamePasswordAuthenticationToken userAuth = 
					new UsernamePasswordAuthenticationToken(authDto.getUserLogin(), authDto.getPassword());
			
			//Prepara mecanismo para autenticacao
			Authentication authentication = authenticatioManager.authenticate(userAuth);
			
			//Busca usuario logado
			UserDetailsImplDTO userAuthenticate = (UserDetailsImplDTO)authentication.getPrincipal();
			
			String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
			
			UserAuthenticationResponseDTO accessDto = new UserAuthenticationResponseDTO(token);
		
			return accessDto;
		
		}catch(BadCredentialsException e) {
			new UserException("Usuário inválido");
		}
		
		return new UserAuthenticationResponseDTO("Acesso negado");
	}

}
