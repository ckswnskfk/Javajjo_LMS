package happy.jaj.prj.model;

import java.util.List;

import happy.jaj.prj.dtos.Course_DTO;

public interface Course_IService {

	// (관리자)전체과정조회
	public List<Course_DTO> course_select();
	
	// (관리자)과정 등록(회차 증가)
	public int course_cnt(Course_DTO dto);
	
	// (관리자)과정 등록(새로운 과정)
	public int course_add(Course_DTO dto);
}
