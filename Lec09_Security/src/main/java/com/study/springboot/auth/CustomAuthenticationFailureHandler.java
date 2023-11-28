package com.study.springboot.auth;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException 
	{
		String loginid = request.getParameter("j_username");
		String errormsg = "";
		
		if(exception instanceof BadCredentialsException) {
			loginFailureCount(loginid);
				errormsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인하세요";
		} else if( exception instanceof InternalAuthenticationServiceException) {
			errormsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인하세요";
		} else if(exception instanceof DisabledException ) {
			errormsg = "계정 비활성, 관리자문의";
		}
		
		request.setAttribute("username", loginid);
		request.setAttribute("error_message", errormsg);
		request.getRequestDispatcher("/loginForm?error=true").forward(request, response);
		
	}

	private void loginFailureCount(String username) {
//		userDao.countFailure(username);
//		int cnt = userDao.checkFailuerCount(username);
//		if(cnt == 3) {
//			userDao.disableUsername(username);
//		}
	}


	
}
