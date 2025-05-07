package com.vaddi.security.spring_security_database.mapper;


import com.vaddi.security.spring_security_database.dto.BookDto;
import com.vaddi.security.spring_security_database.dto.UserInfoDto;
import com.vaddi.security.spring_security_database.entity.Book;
import com.vaddi.security.spring_security_database.entity.UserInfo;

public class UserInfoMapper {

	public static UserInfoDto toDto(UserInfo userInfoRepo) {

		UserInfoDto userInfoDto = new UserInfoDto(userInfoRepo.getUserName(), 
				userInfoRepo.getPassword(), userInfoRepo.getRoles());

		return userInfoDto;
	}

	public static UserInfo  toEntity(UserInfoDto userInfoDto) {

		UserInfo userInfoRepo = new UserInfo(userInfoDto.userName(), 
				userInfoDto.password(), userInfoDto.roles());
		return userInfoRepo;
	}
}
