package com.greatlearning.springbootstudentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.springbootstudentapp.repository.*;
import com.greatlearning.springbootstudentapp.entity.*;
import com.greatlearning.springbootstudentapp.security.*;

// This will be used by Spring Security to get details of a user who is trying to login to the app
// Therefore we need to write this method in a way that Spring Security expects
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername( username );
		
		if( user == null ) {
			throw new UsernameNotFoundException( "Could not find user" );
		}
		
		return new MyUserDetails( user );
	}
}
