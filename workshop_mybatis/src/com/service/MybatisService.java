package com.service;

import java.util.List;
import com.dto.StudentDTO;

public interface MybatisService {

	public List<StudentDTO> selectAllStudent();
}
