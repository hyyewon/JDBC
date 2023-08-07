package com.config;

/*
 * 
 * 
 */

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

	}// end static
	
	//ServiceImpl에 SqlSession 반환하는 메서드
	public static SqlSession getSession() { //객체생성하지 않고 사용하기 위한 용도, class이름으로 접근
		SqlSession session = sqlSessionFactory.openSession(); //sqlSessionFactory에서 openSession을 얻어서 리턴
		return session;
	}
}
