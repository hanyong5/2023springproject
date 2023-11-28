package com.study.springboot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
//	public List<Member> selectAll(){
//		return memberRepository.findAll();
//	}
	
	public Page<Member> selectNameLike(String search, Pageable pageable){
		Page<Member> member = memberRepository.findByNameLike(search, pageable);
		return member;
	}
}
