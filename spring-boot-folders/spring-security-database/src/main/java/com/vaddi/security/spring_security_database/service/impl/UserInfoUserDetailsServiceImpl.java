package com.vaddi.security.spring_security_database.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vaddi.security.spring_security_database.entity.UserInfo;
import com.vaddi.security.spring_security_database.mapper.UserInfoUserDetailsMapper;
import com.vaddi.security.spring_security_database.repository.UserInfoRepository;


@Service
public class UserInfoUserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

			Optional<UserInfo>   userInfo =  userInfoRepository.findByUserName(username);
			return userInfo.map(UserInfoUserDetailsMapper::new)
			.orElseThrow(()-> new UsernameNotFoundException("User : " + username+"not found!" ));
			
	}

}
