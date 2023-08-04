import java.io.InputStream;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dto.DeptDTO;

public class DeptMain2 {

	public static void main(String[] args) throws Exception{
		//Configuration.xml 파일 읽기
		
		
		String resource = "com/config/Configuration.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource); //비런타임 계열이므로 예외처리 필수
		SqlSessionFactory sqlSessionFactory =
		new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		//DML
		//insert 작업
//		DeptDTO dto = new DeptDTO(50,"Development","Hawaii");
//		int n = session.insert("DeptMapper2.addDept", dto);
//		System.out.println(n+"개 저장됨");
//		session.commit(); //트랜잭션 종료
		
		//update 작업
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("dname", "DEVELOPMENT");
//		map.put("loc", "HAWAII");
//		map.put("deptno", 50);
//		int n = session.update("DeptMapper2.updateDept", map);
//		System.out.println(n+"개 수정됨");
//		session.commit(); //트랜잭션 종료
		
		//delete 작업
		int n = session.delete("DeptMapper2.deleteDept", 51);
		session.commit();
		System.out.println(n+"개 삭제됨"); //트랜잭션 종료
		
		session.close();
		
		
		
	}

}
