package com.study.springboot.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.AddUserRequest;
import com.study.springboot.service.RestaurantService;
import com.study.springboot.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class UserApi {
	
	private final UserService userService;
	
	@Operation(summary="회원가입")
	@PostMapping("/api/join")
	 public String join(@RequestBody AddUserRequest request) {
//		 // 이메일 중복 체크
//	        String email = request.getEmail();
//	        if (userService.isEmailAlreadyExists(email)) {
//	            return "이메일이 중복되었습니다.";
//	        }
//
//	        // 중복된 이메일이 없으면 사용자 저장
//	        String userId = userService.join(request);
//	        return "User created with ID: " + userId;
		
		return userService.join(request);
   }
	
	@Operation(summary="로그인/토큰발행")
	@PostMapping("/api/login")
	public String login(
			@RequestBody AddUserRequest request
			) {
		
		return userService.login(request);
		
	}
}
