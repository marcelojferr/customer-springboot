package com.javaproject.customer_springboot.controller.security.jwt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.security.WebSecurityConfig;
import com.javaproject.customer_springboot.security.jwt.AuthEntryPointJwt;
import com.javaproject.customer_springboot.security.jwt.AuthFilterToken;

@ExtendWith(SpringExtension.class)
public class WebSecurityConfigTest {

    @InjectMocks
    private WebSecurityConfig webSecurityConfig;

    @Mock
	private AuthEntryPointJwt authEntryPointJwt = new AuthEntryPointJwt();
    
    MockHttpServletResponse response;
    
    MockHttpServletRequest request;
    
    @Mock
    AuthenticationException authException;
    
    @Mock
    private AuthenticationConfiguration authenticationConfiguration;
    
    @Test
    @DisplayName("Returns PasswordEncoder")
    void passwordEncoderTest() {
    	
        PasswordEncoder pass  = webSecurityConfig.passwordEncoder();

        Assertions.assertThat(pass).isNotNull();
        Assertions.assertThat(pass).isEqualTo(pass);
    }
    
    @Test
    @DisplayName("Returns PasswordEncoder")
    void authenticationManagerTest() throws Exception {
    	
    	AuthenticationManager auth  = webSecurityConfig.authenticationManager(authenticationConfiguration);

        Assertions.assertThat(auth).isEqualTo(auth);
    }
    
    @Test
    @DisplayName("Returns AuthFilterToken")
    void authFilterTokenTest() {
    	
    	AuthFilterToken filter  = webSecurityConfig.authFilterToken();

        Assertions.assertThat(filter).isNotNull();
        Assertions.assertThat(filter).isEqualTo(filter);
    }
    
    @Test
    @DisplayName("Returns authEntryPointJwt")
    void authEntryPointJwtTest() throws Exception {
    	
    	Assertions.assertThatCode(() -> authEntryPointJwt
    			.commence(request, response, authException)).doesNotThrowAnyException();
    	
    } 
    
}
