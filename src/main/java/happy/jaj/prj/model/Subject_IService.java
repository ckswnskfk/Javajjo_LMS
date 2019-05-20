package happy.jaj.prj.model;

import java.util.List;

import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Course_Subject_DTO;
import happy.jaj.prj.dtos.Subject_DTO;

public interface Subject_IService {

	// (강사)자신의 과정 조회
	public List<Course_DTO> select_course_list(String id);
	
	// 전체 과목 조회
	public List<Subject_DTO> subject_select_all();
	
	// 해당 과정명의 전 회차 조회
	public List<Subject_DTO> subject_pre_course(Course_DTO dto);
	
	// 과정에 해당하는 과목 조회
	public List<Subject_DTO> subject_choice(String coursecode);
	
	// 과정에 과목 추가
	public int subject_add_course(Course_Subject_DTO dto);
		
	// 새로운 과목 생성
	public int subject_add(Subject_DTO dto);
}
