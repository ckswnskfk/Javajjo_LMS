package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.dtos.Teacher_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;
@Service
public class User_Service implements User_IService {

	private Logger logger = LoggerFactory.getLogger(User_Service.class);
	
	@Autowired
	private User_Interface user_interface;
	
	/* --------------------   학생    ------------------------*/
	//로그인
	@Override
	public Student_DTO student_login(Map<String, String> map) {
		logger.info("student_login Service 실행 {}", map);
		return user_interface.student_login(map);
	}
	
	//비밀전호 초기화 정보 찾기
	@Override
	public String student_pwre(Map<String, String> map) {
		logger.info("student_pwre Service 실행 {}", map);
		return user_interface.student_pwre(map);
	}

	//비밀번호 초기화
	@Override
	public boolean resetPw(Map<String, String> map) {
		logger.info("resetPw Service 실행 {}", map);
		return user_interface.resetPw(map);
	}

	//회원가입
	@Override
	public boolean student_join(Student_DTO dto) {
		logger.info("student_join Service 실행 {}", dto);
		return user_interface.student_join(dto);
	}

	//과정 조회
	@Override
	public List<Course_DTO> student_course(String id) {
		logger.info("student_course Service 실행 {}", id);
		return user_interface.student_course(id);
	}

	//정보 조회
	@Override
	public Student_DTO student_info(String id) {
		logger.info("student_info Service 실행 {}", id);
		return user_interface.student_info(id);
	}

	//정보 수정
	@Override
	public boolean student_modify(Student_DTO dto) {
		logger.info("student_modify Service 실행 {}", dto);
		return user_interface.student_modify(dto);
	}

	/* --------------------   강사   ------------------------*/
	//로그인
	@Override
	public Teacher_DTO teacher_login(Map<String, String> map) {
		logger.info("teacher_login Service 실행 {}", map);
		return user_interface.teacher_login(map);
	}

	//정보 조회
	@Override
	public Teacher_DTO teacher_info(String id) {
		logger.info("teacher_info Service 실행 {}", id);
		return user_interface.teacher_info(id);
	}

	//정보 수정
	@Override
	public boolean teacher_modify(Map<String, String> map) {
		logger.info("teacher_modify Service 실행 {}", map);
		return user_interface.teacher_modify(map);
	}

	//담당 과정 수강 학생 조회
	@Override
	public List<Student_DTO> teacher_student_list(RowNum_DTO dto) {
		logger.info("teacher_student_list Service 실행 {}", dto);
		return user_interface.teacher_student_list(dto);
	}

	/* --------------------   관리자   ------------------------*/
	//로그인
	@Override
	public Admin_DTO admin_login(Map<String, String> map) {
		logger.info("admin_login Service 실행 {}", map);
		return user_interface.admin_login(map);
	}

	//정보 조회
	@Override
	public Admin_DTO admin_info(String id) {
		logger.info("admin_info Service 실행 {}", id);
		return user_interface.admin_info(id);
	}

	//정보 수정
	@Override
	public boolean admin_modify(Map<String, String> map) {
		logger.info("admin_modify Service 실행 {}", map);
		return user_interface.admin_modify(map);
	}

	//회원가입 신청 조회
	@Override
	public List<Student_DTO> admin_accept_list(RowNum_DTO dto) {
		logger.info("admin_accept_list Service 실행 {}", dto);
		return user_interface.admin_accept_list(dto);
	}

	//회원가입 신청 승인
	@Override
	public boolean admin_accept(Map<String, String[]> map) {
		logger.info("admin_accept Service 실행 {}", map);
		return user_interface.admin_accept(map);
	}

	//회원가입 신청 거절
	@Override
	public boolean admin_accept_refuse(Map<String, String[]> map) {
		logger.info("admin_accept_refuse Service 실행 {}", map);
		return user_interface.admin_accept_refuse(map);
	}

	//강사 조회
	@Override
	public List<Teacher_DTO> admin_teacher_list(RowNum_DTO dto) {
		logger.info("admin_teacher_list Service 실행 {}", dto);
		return user_interface.admin_teacher_list(dto);
	}

	//강사 탈퇴
	@Override
	public boolean admin_teacher_delete(String id) {
		logger.info("admin_teacher_delete Service 실행 {}", id);
		return user_interface.admin_teacher_delete(id);
	}

	//강사 등록
	@Override
	public boolean admin_teacher_add(Teacher_DTO dto) {
		logger.info("admin_teacher_add Service 실행 {}", dto);
		return user_interface.admin_teacher_add(dto);
	}

	//강사 정보 수정
	@Override
	public boolean admin_teacher_modify(Map<String, String> map) {
		logger.info("admin_teacher_modify Service 실행 {}", map);
		return user_interface.admin_teacher_modify(map);
	}

	//전체 학생 조회
	@Override
	public List<Student_DTO> admin_student_list(RowNum_DTO dto) {
		logger.info("admin_student_list Service 실행 {}", dto);
		return user_interface.admin_student_list(dto);
		
	}

	//학생 탈퇴
	@Override
	public boolean admin_student_delete(Map<String, String[]> map) {
		logger.info("admin_student_delete Service 실행 {}", map);
		return user_interface.admin_student_delete(map);
	}

	//학생 상세 조회
	@Override
	public Student_DTO admin_student_detail(String id) {
		logger.info("admin_student_detail Service 실행 {}", id);
		return user_interface.admin_student_detail(id);
	}

	//학생 정보 수정
	@Override
	public boolean admin_student_modify(Map<String, String> map) {
		logger.info("admin_student_modify Service 실행 {}", map);
		return user_interface.admin_student_modify(map);
	}

	//학생 과정 연결 조회 - 본인 과정
	@Override
	public List<Course_DTO> admin_student_clist_sel(String id) {
		logger.info("admin_student_clist_sel Service 실행 {}", id);
		return user_interface.admin_student_clist_sel(id);
	}

	//학생 과정 연결 조회 - 모든 과정
	@Override
	public List<String> admin_student_clist_all() {
		logger.info("admin_student_clist_all Service 실행");
		return user_interface.admin_student_clist_all();
	}

	//학생 과정 연결
	@Override
	public boolean admin_student_cconnect(UserCourse_DTO dto) {
		logger.info("admin_student_cconnect Service 실행 {}", dto);
		return user_interface.admin_student_cconnect(dto);
	}
	
	//학생 과정 삭제
	@Override
	public boolean admin_student_cdelete(UserCourse_DTO dto) {
		logger.info("admin_student_cdelete Service 실행 {}", dto);
		return user_interface.admin_student_cdelete(dto);
	}

}
