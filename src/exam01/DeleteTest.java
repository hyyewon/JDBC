package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteTest {

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
			String sql = "delete from dept where deptno = ?";
			
			pstmt = con.prepareStatement(sql);
			// ? 대신에 값 설정
			/*
			 * pstmt.setXXX(?의 위치, 값)
			 * 
			 */
			
			pstmt.setInt(1, 11);

			int num = pstmt.executeUpdate();
			System.out.println("레코드 삭제 개수 :" + num);
			
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
