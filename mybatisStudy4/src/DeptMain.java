import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
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
		
		//Dynamic SQL
		//1-1. multi select
		//select * from dept where deptno IN (값, 값2, ...)
		List<Integer> deptnoList = Arrays.asList(10,20,30);
		
		List<DeptDTO> list = session.selectList("DeptDynamicMapper.selectByDeptnoMulti", deptnoList);
		for(DeptDTO dto:list) {
			System.out.println(dto);
		}
		
		//1-2. multi select2
		//DeptDTO에 deptno에 저장
		List<DeptDTO> deptnoList2 = Arrays.asList(new DeptDTO(10,"",""),
												  new DeptDTO(20,"",""),
												  new DeptDTO(40,"",""));
		
		List<DeptDTO> list2 = session.selectList("DeptDynamicMapper.selectByDeptnoMulti2", deptnoList2);
		for(DeptDTO dto:list2) {
			System.out.println(dto);
		}
		
		//2. multi delete
		//delete from dept where deptno IN (값, 값2,...)
		List<Integer> deptnoList3 = Arrays.asList(50);
		int n = session.delete("DeptDynamicMapper.deleteMulti",deptnoList3);
		System.out.println(n+"개가 삭제됨");
		session.commit();
		
		//3. multi update
		//update dept set dname = '개발' where deptno IN (값, 값2,...)
		List<Integer> deptnoList4 = Arrays.asList(80);
		int n2 = session.update("DeptDynamicMapper.updateMulti", deptnoList4);
		System.out.println(n+"개가 수정됨");
		session.commit();
		
		//4. multi insert
		/*
		 * insert all
		 * 	into dept ~
		 * 	into dept ~
		 * subquery;
		 */
		
//		List<DeptDTO> deptnoList5 = Arrays.asList(new DeptDTO(50,"개발","서울"),
//				  								  new DeptDTO(60,"개발","서울"),
//				  								  new DeptDTO(70,"개발","서울"),
//				  								  new DeptDTO(80,"개발","서울"));
//		
//		int n3 = session.insert("DeptDynamicMapper.insertMulti",deptnoList5);
//		System.out.println(n3+"개가 저장됨");
//		session.commit();
		
		//5. 조건 ( 단일if문 지원)
		//dname 파라미터 값이 null여부에 따라 다음 2가지 SQL문 하나가 만들어진다.
		//select * from dept where dname = 값; 또는 select * from dept
//		String  dname = "개발";
		String  dname = null;
		List<DeptDTO> list4 = session.selectList("DeptDynamicMapper.selectAllorDname",dname);
		
		for(DeptDTO dto:list4) {
			System.out.println(dto);
		}
		
		//6.다중 조건 ( if~else 지원안됨, choose문 지원-조건 여러개, switch문과 비슷 )
		/*
		 * dname = 값 에 따라 select 결과가 달라짐
		 * dname 값이 'ACCOUNTING' ==> 10,20,30 검색
		 * 				where deptno IN (10,20,30)
		 * dname 값이 'RESEARCH' ==> 40만 검색
		 * 				where deptno = 40
		 * dname 값이 모두 만족하지 않으면 ==> 60,70,80 검색
		 * 				where deptno IN (60,70,80)
		 */
		
		HashMap<String, String> map = new HashMap<>();
		map.put("dname","xxx"); 
		
		List<DeptDTO> list5 = session.selectList("DeptDynamicMapper.selectByDnameChoose",map);
		
		for(DeptDTO dto:list5) {
			System.out.println(dto);
		}
		
		
		session.close();
		
	}

}
