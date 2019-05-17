package happy.jaj.prj.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Subject_DTO;

@Repository
public class Subject_Dao implements Subject_Interface {

	private Logger logger = LoggerFactory.getLogger(Subject_Dao.class);
	private final String NS = "happy.jaj.prj.Subject_Mapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// (강사)자신의 과정 조회
	@Override
	public List<Course_DTO> select_course_list(String id) {
		logger.info("Subject_Dao select_course_list 실행, id = {}", id);
		return sqlSessionTemplate.selectList(NS+"select_course_list", id);
	}

	// 전체 과목 조회
	@Override
	public List<Subject_DTO> subject_select_all() {
		logger.info("Subject_Dao subject_select_all 실행");
		return sqlSessionTemplate.selectList(NS+"subject_select_all");
	}

	// 해당 과정명의 전 회차 조회
	@Override
	public List<Subject_DTO> subject_pre_course(Course_DTO dto) {
		logger.info("Subject_Dao subject_pre_course 실행 {}", dto);
		return sqlSessionTemplate.selectList(NS+"subject_pre_course", dto);
	}
	
	// 과정에 해당하는 과목 조회
	@Override
	public List<Subject_DTO> subject_choice(String coursecode) {
		logger.info("Subject_Dao subject_choice 실행 {}", coursecode);
		return sqlSessionTemplate.selectList(NS+"subject_choice", coursecode);
	}
	
	// 새로운 과목 생성
	@Override
	public int subject_add(Subject_DTO dto) {
		logger.info("Subject_Dao subject_add 실행 {}", dto);
		return sqlSessionTemplate.insert(NS+"subject_add", dto);
	}



}
