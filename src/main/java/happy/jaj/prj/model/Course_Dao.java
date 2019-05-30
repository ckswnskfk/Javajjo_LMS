package happy.jaj.prj.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Course_DTO;

@Repository
public class Course_Dao implements Course_Interface {

	private Logger logger = LoggerFactory.getLogger(Course_Dao.class);
	private final String NS = "happy.jaj.prj.Course_Mapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	
	// (관리자)전체과정조회
	@Override
	public List<Course_DTO> course_select() {
		logger.info("Course_Dao course_select 실행");
		return sqlSessionTemplate.selectList(NS+"course_select");
	}

	// (관리자)과정 등록(회차 증가)
	@Override
	public int course_cnt(Course_DTO dto) {
		logger.info("Course_Dao course_cnt 실행");
		return sqlSessionTemplate.insert(NS+"course_cnt", dto);
	}

	// (관리자)과정 등록(새로운 과정)
	@Override
	public int course_add(Course_DTO dto) {
		logger.info("Course_Dao course_add 실행");
		return sqlSessionTemplate.insert(NS+"course_add", dto);
	}
	
	public int codeDel(Course_DTO dto) {
		logger.info("Course_Dao codeDel 실행");
		return sqlSessionTemplate.delete(NS+"codeDel", dto);
	}
	
	
}
