package com.id.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.id.springsecurity.models.CustomerUserDetail;
import com.id.springsecurity.models.User;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.id.springsecurity.repos.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{
      @Autowired
	 private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("No User");
		}
		
		
		
		return new CustomerUserDetail(user);
	}

}
