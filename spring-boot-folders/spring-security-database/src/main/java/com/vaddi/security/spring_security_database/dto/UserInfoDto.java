package com.vaddi.security.spring_security_database.dto;

public record UserInfoDto(String userName,
		String password,
		String roles) {
}
