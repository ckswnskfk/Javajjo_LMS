package happy.jaj.prj.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Course_Subject_DTO;
import happy.jaj.prj.dtos.Subject_DTO;

@Service
public class Subject_Service implements Subject_IService {

	private Logger logger = LoggerFactory.getLogger(Subject_Service.class);
	
	@Autowired
	private Subject_Interface subject_Interface;

	// (강사)자신의 과정 조회
	@Override
	public List<Course_DTO> select_course_list(String id) {
		logger.info("Subject_Service select_course_list 실행");
		return subject_Interface.select_course_list(id);
	}

	// 전체 과목 조회
	@Override
	public List<Subject_DTO> subject_select_all() {
		logger.info("Subject_Service subject_select_all 실행");
		return subject_Interface.subject_select_all();
	}

	// 해당 과정명의 전 회차 조회
	@Override
	public List<Subject_DTO> subject_pre_course(Course_DTO dto) {
		logger.info("Subject_Service subject_pre_course 실행");
		return subject_Interface.subject_pre_course(dto);
	}
	
	// 과정에 해당하는 과목 조회
	@Override
	public List<Subject_DTO> subject_choice(String coursecode) {
		logger.info("Subject_Service subject_choice 실행");
		return subject_Interface.subject_choice(coursecode);
	}
	
	// 과정에 과목 추가
	@Override
	public int subject_add_course(List<Course_Subject_DTO> list) {
		logger.info("Subject_Service subject_add_course 실행");
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			cnt += subject_Interface.subject_add_course(list.get(i));
		}
		return cnt;
	}
	@Override
	public int course_sub_Del(List<Course_Subject_DTO> list) {
		logger.info("Subject_Service course_sub_Del 실행");
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			cnt += subject_Interface.course_sub_Del(list.get(i));
		}
		return cnt;
	}
	
	// 새로운 과목 생성
	@Override
	public int subject_add(Subject_DTO dto) {
		logger.info("Subject_Service subject_add 실행");
		return subject_Interface.subject_add(dto);
	}

	@Override
	public Subject_DTO subjectname(String re) {
		logger.info("Subject_Service subjectname 실행");
		return subject_Interface.subjectname(re);
	}

	@Override
	public int subDel(List<Subject_DTO> dto) {
		logger.info("Course_Service codeDel 실행");
		int cnt=0;
		for (int i = 0; i < dto.size(); i++) {
			cnt += subject_Interface.subDel(dto.get(i));
		}
		return cnt;
	}
	
}
