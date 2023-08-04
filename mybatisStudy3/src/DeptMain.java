import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dto.DeptDTO;

public class DeptMain {

	public static void main(String[] args) throws Exception{
		//Configuration.xml 파일 읽기
		
		
		String resource = "com/config/Configuration.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource); //비런타임 계열이므로 예외처리 필수
		SqlSessionFactory sqlSessionFactory =
		new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		//findByDeptno 호출
		DeptDTO dto = session.selectOne("DeptMapper.findByDeptno", 10);
		System.out.println(dto);
		System.out.println("######################################");
		
		
		//findAll 호출
		//자기가 알아서 데이터를 List에 저장해뒀음. 그거 불러오기만 하면 됨
		List<DeptDTO> list = session.selectList("DeptMapper.findAll");
		for(DeptDTO xxx:list) {
			System.out.println(xxx);
		}
		System.out.println("######################################");
		
		//findByDeptnoAndDname 호출
		DeptDTO dto2 = new DeptDTO();
		dto2.setDeptno(20);
		dto2.setDname("A");
		List<DeptDTO> list2 = session.selectList("DeptMapper.findByDeptnoAndDname", dto2);
		for(DeptDTO xxx:list2) {
			System.out.println(xxx);
		}
		
		System.out.println("######################################");
		
		//findByDeptnoAndDnameMap 호출
		HashMap<String,Object> map = new HashMap<>(); //int와 String 두개의 데이터를 한꺼번에 받기위해 value타입을 Object로 지정 
		map.put("xxx",10);
		map.put("yyy","개발");
		System.out.println("######################################");
		
		List<DeptDTO> list3 = session.selectList("DeptMapper.findByDeptnoAndDnameMap", map);
		for(DeptDTO xxx:list3) {
			System.out.println(xxx);
		}
		System.out.println("######################################");
		
		//findAllPage
		List<DeptDTO> list4 = session.selectList("DeptMapper.findAllPage", null, new RowBounds(1, 3));
		for(DeptDTO xxx:list4) {
			System.out.println(xxx);
		}
		
		System.out.println("######################################");
		session.close();
		
		
		
	}

}
