package com.service;

import java.util.List;

import com.dto.DeptDTO;
import com.exception.DuplicatedDeptnoException;

//dept 테이블의 데이터를 가공하는 역할 ==> 비즈니스 로직 처리 및 트랜잭션 처리 담당.
public interface DeptService {
	
	//Select
	public List<DeptDTO> findAll(); //먼저 추상메서드 만든 다음 얘를 상속받는 애에서 메서드 구현
	
	//DML
	public int insert(DeptDTO dto) throws DuplicatedDeptnoException;
	public int update(DeptDTO dto);
	public int delete(DeptDTO dto);
	
	//수정 및 삭제 처리하는 메서드
	public int updateAndDelete(DeptDTO dto, int deptno);

}
