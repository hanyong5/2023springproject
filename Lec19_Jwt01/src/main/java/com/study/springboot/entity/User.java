package com.study.springboot.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
	@SequenceGenerator(name="users_seq", sequenceName = "users_SEQ", allocationSize=1)
	private Long id;
	
	private String email;
	private String password;
	
	
	@Override //권한반환
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("user"));
	}
	@Override // 사용자의 id를 반환
	public String getUsername() {
		
		return email;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override //계정만료여부
	public boolean isAccountNonExpired() {
		return true; // 만료되지 않음
	}
	@Override //계정 잠금여부
	public boolean isAccountNonLocked() {
		return true; // 잠금되지 않음
	}
	@Override //패스워드 만료여부
	public boolean isCredentialsNonExpired() {
		return true; // 만료되지않음
	}
	@Override //계정 상용가능여부
	public boolean isEnabled() {
		return true; //사용가능
	}
	
	
}
