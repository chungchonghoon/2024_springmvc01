package com.ict.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.member.dao.MemberDAO;
import com.ict.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public MemberVO getMemberLogin(MemberVO mvo) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.Login(mvo);
	}

}
