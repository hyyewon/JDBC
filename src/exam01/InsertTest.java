package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertTest {

	public static void main(String[] args) {

		String driver = "oracle.jdbc.driver.OracleDriver"; 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "SCOTT";
		String passwd = "TIGER";
		
		
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "insert into dept (deptno, dname, loc) " //아랫줄로 내릴 때는 공백을 의도적으로 줘서 오류 안나게 하기
					+ " values(?, ?, ?) "; //?:바인딩 변수로서 나중에 값을 설정
			
			pstmt = con.prepareStatement(sql);
			// ? 대신에 값 설정
			/*
			 * pstmt.setXXX(?의 위치, 값)
			 * 
			 */
			
			pstmt.setInt(1, 11); //deptno 값은 중복되지 않도록 확인할 것.
			pstmt.setString(2, "개발");
			pstmt.setString(3, "HAWAII");

			int num = pstmt.executeUpdate();
			System.out.println("레코드 생성 개수 :" + num);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//역순으로 종료해주기
				//이때 null값이면 RuntimeException 이기 때문에 조건을 넣어 예외가 발생하는 것을 막아줘야 함.
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}//end main

}
