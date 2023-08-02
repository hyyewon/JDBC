package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.dao.DeptDAO;
import com.dto.DeptDTO;
import com.exception.DuplicatedDeptnoException;

public class DeptServiceImpl implements DeptService {
	
	String driver = "oracle.jdbc.driver.OracleDriver"; 
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "SCOTT";
	String passwd = "TIGER";
	
	public DeptServiceImpl() { //객체가 생성되었을 때 Class.forName 해줌
		try {
			Class.forName(driver); //드라이버 로딩 (OracleDriver 클래스 객체 생성)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	//select 기능하는 메서드
	//Connection 까지만 얻는다.
	public List<DeptDTO> findAll(){ //여러개의 DeptDTO반환 , 레코드 개수만큼 DeptDTO가 생성이 되었을 것이고 이를 List바구니에 담음
		
		List<DeptDTO> list = null;
		Connection con = null;
		
		//DAO접근
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			DeptDAO dao = new DeptDAO();
			list = dao.findAll(con); //Connection 전달해줘야 DAO에서 나머지 작업을 수행할 수 있음
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//이때 null값이면 RuntimeException 이기 때문에 조건을 넣어 예외가 발생하는 것을 막아줘야 함.
				if(con != null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
		
	}

	//insert 기능하는 메서드
	//Connection 까지만 얻는다. 나머지 작업은 DAO에서 처리
	@Override
	public int insert(DeptDTO dto) throws DuplicatedDeptnoException { //3.override 신경쓰기
		int n = 0;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			//DAO 접근
			DeptDAO dao = new DeptDAO();
			n = dao.insert(con, dto);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return n;
	}

	//update 기능하는 메서드
	//Connection 까지만 얻는다. 나머지 작업은 DAO에서 처리
	@Override
	public int update(DeptDTO dto) {
		int n = 0;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			
			//DAO 연동
			DeptDAO dao = new DeptDAO();
			n=dao.update(con, dto);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n;
	}
	
	
}
