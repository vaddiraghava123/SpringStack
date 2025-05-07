package com.vaddi.security.spring_security_database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaddi.security.spring_security_database.dto.UserInfoDto;
import com.vaddi.security.spring_security_database.entity.UserInfo;
import com.vaddi.security.spring_security_database.mapper.UserInfoMapper;
import com.vaddi.security.spring_security_database.repository.UserInfoRepository;
import com.vaddi.security.spring_security_database.service.UserInfoService;

@Service
public class UserInfoImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDto createUser(UserInfoDto userInfoDto) {
        UserInfo userInfo = UserInfoMapper.toEntity(userInfoDto);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return UserInfoMapper.toDto(userInfo);
    }
}
