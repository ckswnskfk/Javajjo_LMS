package happy.jaj.prj.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happy.jaj.prj.dtos.Course_DTO;

@Service
public class Course_Service implements Course_IService {

	private Logger logger = LoggerFactory.getLogger(Course_Service.class);
	
	@Autowired
	private Course_Interface course_Interface;

	// (관리자)전체과정조회
	@Override
	public List<Course_DTO> course_select() {
		logger.info("Course_Service course_select 실행");
		return course_Interface.course_select();
	}

	// (관리자)과정 등록(회차 증가)
	@Override
	public int course_cnt(Course_DTO dto) {
		logger.info("Course_Service course_cnt 실행");
		return course_Interface.course_cnt(dto);
	}

	// (관리자)과정 등록(새로운 과정)
	@Override
	public int course_add(Course_DTO dto) {
		logger.info("Course_Service course_add 실행");
		return course_Interface.course_add(dto);
	}
	
	
}
