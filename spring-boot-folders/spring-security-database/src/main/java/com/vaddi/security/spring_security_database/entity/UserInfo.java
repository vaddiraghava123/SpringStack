package com.vaddi.security.spring_security_database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	
	public String userName;
	public String password;
	public String roles;

}
