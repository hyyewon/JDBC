import java.util.HashMap;
import java.util.List;

import com.dto.DeptDTO;
import com.service.DeptService;
import com.service.DeptServiceImpl;

public class DeptMain {

	public static void main(String[] args) {
		//서비스 연동
		//가. 전체목록 findAll
		DeptService service = new DeptServiceImpl();
		List<DeptDTO> list = service.findAll();
		for(DeptDTO dto : list) {
			System.out.println(dto);
		}
		System.out.println("###########################");
		
		//나. findByDeptno
		DeptDTO xxx = service.findByDeptno(20);
		System.out.println(xxx);
		System.out.println("###########################");
		
		//다. 삽입 addDept
//		int n = service.addDept(new DeptDTO(50, "MANAGEMENT", "LA"));
//		System.out.println(n+"개가 저장됨");
		
		//라. 수정 updateDept
		HashMap<String, Object> map = new HashMap<>();
		map.put("deptno", 60);
		map.put("dname", "DEVELOPMENT");
		map.put("loc", "SEATTLE");
		
		int n2 = service.updateDept(map);
		System.out.println(n2+"개가 수정됨");
		
		//마. 삭제 deleteDept
		
		int n3 = service.deleteDept(80);
		System.out.println(n3+"개가 삭제됨");
		
	}

}
