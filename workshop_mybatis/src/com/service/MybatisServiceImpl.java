package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MyBatisDAO;
import com.dto.StudentDTO;

public class MybatisServiceImpl implements MybatisService {

	@Override
	public List<StudentDTO> selectAllStudent() {
		List<StudentDTO> list = null; //리턴타입이 있으면, 리턴타입에 맞는 변수 생성해서 리턴
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			MyBatisDAO dao = new MyBatisDAO();
			list = dao.selectAllStudent(session);
		}finally {
			session.close();
		}
		return list;
	}

	
}
