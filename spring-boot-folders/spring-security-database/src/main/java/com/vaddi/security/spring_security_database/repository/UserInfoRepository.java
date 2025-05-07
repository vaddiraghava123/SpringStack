package com.vaddi.security.spring_security_database.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vaddi.security.spring_security_database.entity.UserInfo;

public interface UserInfoRepository extends MongoRepository<UserInfo, String>{

	Optional<UserInfo> findByUserName(String userName);
	
}
