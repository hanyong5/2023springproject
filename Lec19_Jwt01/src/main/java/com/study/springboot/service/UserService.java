package com.study.springboot.service;


import java.time.ZonedDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.AddUserRequest;
import com.study.springboot.entity.User;
import com.study.springboot.repository.UserRepository;
import com.study.springboot.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JwtProvider jwtProvider;
	
	

	public String join(AddUserRequest dto) {

		if (dto.getPassword() == null) {
			// Handle the case where the password is null (throw an exception, return an
			// error message, etc.)
			return "비밀번호를 입력하세요";
		}
		User user = new User();
		user.setEmail(dto.getEmail());
//		user.setUsername(dto.getEmail());
		user.setCreatedAt(ZonedDateTime.now());
		user.setUpdatedAt(ZonedDateTime.now());
		user.setRole("user");

		// Encode the password before saving it
		String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
		user.setPassword(encodedPassword);

		// Save the user using the repository
		userRepository.save(user);

		// You can return a success message or perform other actions as needed
		return "입력완료";
	}


	public boolean isEmailAlreadyExists(String email) {
		return userRepository.existsByEmail(email);
	}


	public String login(AddUserRequest request) {
		String email = request.getEmail();
		String rawPasswordString = request.getPassword();
		
		User byEmail = userRepository.findByEmail(request.getEmail());
		

		
		if(bCryptPasswordEncoder.matches(rawPasswordString, byEmail.getPassword())) {
			 String jwtToken = jwtProvider.generateJwtToken(
	            		byEmail.getId(), byEmail.getEmail(), byEmail.getUsername(),
	            		byEmail.getRole()
	            		);
			
			return "로그인성공" + jwtToken;
			
					
		}else {
			return "로그인실패";
		}
	}
}
