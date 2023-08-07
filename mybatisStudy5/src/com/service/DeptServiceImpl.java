package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dao.DeptDAO;
import com.dto.DeptDTO;

public class DeptServiceImpl implements DeptService {
	static SqlSessionFactory sqlSessionFactory = null;
	static { // 객체 생성하지 않아도 가장 빨리 실행되도록 하려고

		String resource = "com/config/Configuration.xml";
		InputStream inputStream = null; // 블럭 안에서 선언된 로컬 변수라서 초기화선언 해주어야 함
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 비런타임 계열이므로 예외처리 필수
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}// end static

	@Override
	public List<DeptDTO> findAll() {
		List<DeptDTO> list = null;
		// SqlSession 얻기 : 트랜잭션을 서비스에서 진행하기 때문에 서비스에서 얻어와야 함
		SqlSession session = sqlSessionFactory.openSession();
		try {
			////////////////////
			// DAO 연동
			DeptDAO dao = new DeptDAO();
			list = dao.findAll(session);
			////////////////////
		} finally {
			session.close(); // 반드시 수행해야 하는 문장이 무엇인지를 알려주는 용도가 클 때 catch없이 사용
		}
		return list;

	}

	@Override
	public DeptDTO findByDeptno(int deptno) {
		DeptDTO dto = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {

			DeptDAO dao = new DeptDAO();
			dao.findByDeptno(session, deptno);

		} finally {
			session.close();
		}
		return dto;
	}

	@Override
	public int addDept(DeptDTO dto) {
		int n = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// DAO 연동
			DeptDAO dao = new DeptDAO();
			n = dao.addDept(session, dto);
			session.commit(); // 명시적 commit
		} finally {
			session.close();

		}
		return n;
	}

	@Override
	public int updateDept(HashMap<String, Object> map) {
		int n = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DeptDAO dao = new DeptDAO();
			n = dao.updateDept(session, map);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	@Override
	public int deleteDept(int deptno) {
		int n = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			DeptDAO dao = new DeptDAO();
			n = dao.deleteDept(session, deptno);
			session.commit();
		} finally {
			session.close();
		}

		return n;
	}

}
