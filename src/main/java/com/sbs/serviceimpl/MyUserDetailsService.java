package com.sbs.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sbs.entity.User;
import com.sbs.exception.UserNameNotFoundException;
import com.sbs.repository.UserRepository;
import com.sbs.security.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByName(username);
		if (user==null) {
			throw new UserNameNotFoundException("user 404");
		}
		return new MyUserDetails(user);
	}

}
