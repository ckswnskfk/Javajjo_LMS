package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.dtos.Teacher_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;

public interface User_IService {
	
	/* --------------------   학생    ------------------------*/
	//로그인
	public Student_DTO student_login(Map<String, String> map);
	
	//비밀번호 초기화 정보 찾기
	public String student_pwre(Map<String, String> map);
	
	//비밀번호 초기화
	public boolean resetPw(Map<String, String> map);
	
	//초기화 후 암호화 처리
	public boolean resetPwLock(Map<String, String> map);
	
	//회원가입
	public boolean student_join(Student_DTO dto);
	
	//아이디 중복 체크
	public String student_duplicate(String id);
	
	//과정 조회
	public List<Course_DTO> student_course(String id);
	
	//정보 조회
	public Student_DTO student_info(String id);
	
	//정보 수정
	public boolean student_modify(Student_DTO dto);
	
	/* --------------------   강사   ------------------------*/
	//로그인
	public Teacher_DTO teacher_login(Map<String, String> map);
	
	//정보 조회
	public Teacher_DTO teacher_info(String id);
	
	//정보 수정
	public boolean teacher_modify(Map<String, String> map);
	
	//담당 과정 수강 학생 조회
	public List<Student_DTO> teacher_student_list(RowNum_DTO dto);
	
	//담당 과정 수강 학생 수 조회
	public int teacher_student_list_count(RowNum_DTO dto);
	
	/* --------------------   관리자   ------------------------*/
	//로그인
	public Admin_DTO admin_login(Map<String, String> map);
	
	//정보 조회
	public Admin_DTO admin_info(String id);
	
	//정보 수정
	public boolean admin_modify(Map<String, String> map);
	
	//회원가입 신청 조회
	public List<Student_DTO> admin_accept_list(RowNum_DTO dto);
	
	//회원가입 신청 수 조회
	public int admin_accept_list_count();
	
	//회원가입 신청 승인
	public boolean admin_accept(Map<String, String[]> map);
	
	//회원가입 신청 거절
	public boolean admin_accept_refuse(Map<String, String[]> map);
	
	//강사 조회
	public List<Teacher_DTO> admin_teacher_list(RowNum_DTO dto);
	
	//강사 수 조회
	public int admin_teacher_list_count();
	
	//강사 탈퇴
	public boolean admin_teacher_delete(String id);
	
	//강사 등록
	public boolean admin_teacher_add(Teacher_DTO dto);
	
	//강사 정보 수정
	public boolean admin_teacher_modify(Map<String, String> map);
	
	//전체 학생 조회
	public List<Student_DTO> admin_student_list(RowNum_DTO dto);
	
	//전체 학생 수 조회
	public int admin_student_list_count();
	
	//학생 탈퇴
	public boolean admin_student_delete(Map<String, String[]> map);
	
	//학생 상세 조회
	public Student_DTO admin_student_detail(String id);

	//학생 정보 수정
	public boolean admin_student_modify(Map<String, String> map);
	
	//학생 과정 연결 조회 - 본인 과정
	public List<Course_DTO> admin_student_clist_sel(String id);
	
	//학생 과정 연결 조회 - 모든 과정
	public List<Course_DTO> admin_student_clist_all();
	
	//학생 과정 연결
	public boolean admin_student_cconnect(UserCourse_DTO dto);
	
	//학생 과정 삭제
	public boolean admin_student_cdelete(UserCourse_DTO dto);
	
}
