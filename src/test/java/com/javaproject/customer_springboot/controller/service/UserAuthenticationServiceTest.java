package com.javaproject.customer_springboot.controller.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Collection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.dto.UserAuthenticationRequestDTO;
import com.javaproject.customer_springboot.dto.UserAuthenticationResponseDTO;
import com.javaproject.customer_springboot.dto.UserDetailsImplDTO;
import com.javaproject.customer_springboot.security.jwt.JwtUtils;
import com.javaproject.customer_springboot.service.UserAuthenticationService;

@ExtendWith(SpringExtension.class)
class UserAuthenticationServiceTest {

	@InjectMocks
    private UserAuthenticationService userAuthenticationService;
	
	@Mock
	private AuthenticationManager authenticatioManageryMock;
	
	@Mock
	private JwtUtils jwtUtilsyMock;
	
    @Mock
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @Mock
	private UserDetailsImplDTO userDetailsImplDTO;

    @Mock 
    private UserAuthenticationRequestDTO userAuthenticationRequestDTO;
    
    @Mock
    private Collection<? extends GrantedAuthority> authorities;
    
    @Mock
    private Authentication authentication;

	@BeforeEach
    void setUp() {
    	
		userAuthenticationRequestDTO = UserAuthenticationRequestDTO.builder()
			.userLogin("Teste")
			.password("Teste")
            .build();
		
		usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
			userAuthenticationRequestDTO.getUserLogin(), userAuthenticationRequestDTO.getPassword());
		
		userDetailsImplDTO = UserDetailsImplDTO.builder()
				.id(1L)
				.userLogin("Teste")
				.userName("Teste")
				.userEmail("Teste")
				.password("Teste")
				.authorities(authorities)
				.build();
		
    }
    
    @Test
    @DisplayName("Returns token for login of user")
    void loginTest() {

        BDDMockito.when(authenticatioManageryMock.authenticate(usernamePasswordAuthenticationToken))
			.thenReturn(authentication);
        
        BDDMockito.when(jwtUtilsyMock.generateTokenFromUserDetailsImpl(userDetailsImplDTO))
        	.thenReturn(ArgumentMatchers.anyString());

        UserAuthenticationResponseDTO response = userAuthenticationService.login(userAuthenticationRequestDTO);
        
        assertEquals(response, response);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(response);
    }    
}