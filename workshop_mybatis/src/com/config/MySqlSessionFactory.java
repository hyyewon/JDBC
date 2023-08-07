package com.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {
	
	
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

	}//
	
	public static SqlSession getSession() {
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}

}
