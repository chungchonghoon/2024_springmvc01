package com.ict.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.member.vo.MemberVO;



@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public MemberVO Login(MemberVO mvo) throws Exception {
		
		return sqlSessionTemplate.selectOne("member.login", mvo);
	}

}
