package com.study.springboot.service;


import java.time.Duration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.AddUserRequest;
import com.study.springboot.config.jwt.TokenProvider;
import com.study.springboot.entity.User;
import com.study.springboot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final TokenProvider tokenProvider;
	
	
	public Long save(AddUserRequest dto) {
		 User user = User.builder()
		            .email(dto.getEmail())
		            .password(bCryptPasswordEncoder.encode(dto.getPassword()))
		            .build();

		    return userRepository.save(user).getId();
	}


	public boolean isEmailAlreadyExists(String email) {
		return userRepository.existsByEmail(email);
	}


	public String login(AddUserRequest request) {
		User user = userRepository.findByEmail(request.getEmail());
		
		if(user !=null && bCryptPasswordEncoder.matches(request.getPassword(),user.getPassword())) {
			Duration expoirationTime = Duration.ofMinutes(30); //30분
			String token = tokenProvider.generateToken(user,expoirationTime);
			
			return "로그인성공" + token;
		}else {
			return "로그인실패";
		}
	}
}
