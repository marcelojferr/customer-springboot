package com.javaproject.customer_springboot.dto;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.javaproject.customer_springboot.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDetailsImplDTO implements UserDetails{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String userName;
	
	private String userLogin;
	
	private String userEmail;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UserDetailsImplDTO build(User user) {
		
		return new UserDetailsImplDTO(
				user.getId(), 
				user.getUserName(), 
				user.getUserLogin(),
				user.getPassword(),
				user.getUserEmail(), 
				new ArrayList<>());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}
	
	public String getUserLogin() {
		return userLogin;
	}

	public String getUserEmail() {
		return userEmail;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
