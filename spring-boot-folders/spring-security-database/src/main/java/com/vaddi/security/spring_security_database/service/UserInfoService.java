package com.vaddi.security.spring_security_database.service;

import com.vaddi.security.spring_security_database.dto.UserInfoDto;

public interface UserInfoService {
	public UserInfoDto createUser(UserInfoDto userInfoDto);
}
