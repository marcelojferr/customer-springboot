package com.javaproject.customer_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.javaproject.customer_springboot.dto.UserDetailsImplDTO;
import com.javaproject.customer_springboot.model.User;
import com.javaproject.customer_springboot.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = userRepository.findByUserLogin(username).get();
		return UserDetailsImplDTO.build(usuario);
	}

}
