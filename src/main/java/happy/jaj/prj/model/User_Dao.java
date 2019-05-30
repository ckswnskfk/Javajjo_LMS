package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.dtos.Teacher_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;
@Repository
public class User_Dao implements User_Interface {

	private Logger logger = LoggerFactory.getLogger(User_Dao.class);
	
	private final String NS_Stu = "happy.jaj.prj.Student_Mapper.";
	private final String NS_Tea = "happy.jaj.prj.Teacher_Mapper.";
	private final String NS_Adm = "happy.jaj.prj.Admin_Mapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder; // crypto로 해야한다.
	
	/* --------------------   학생    ------------------------*/
	//로그인
	@Override
	public Student_DTO student_login(Map<String, String> map) {
		logger.info("student_login Dao 실행 {}", map);
		//좋은 방법
		//입력한 id에 해당하는 pw(DB)
		String securityPw = sqlSession.selectOne(NS_Stu+"student_login_find", map.get("id")); //$2a$10$GgCM.MtWE2uQlI6OP/0oSe9cgBhFUeEBqNHj3gP4/n4mI56KnE9ZW
		
		//dto.getPw() : 내가 로그인시 입력한 비밀번호(1111)
		//$2a$10$GgCM.MtWE2uQlI6OP/0oSe9cgBhFUeEBqNHj3gP4/n4mI56KnE9ZW -> 1111로 디코딩해서 두개가 일치하는지 확인 
		//이 순서로 넣어주어야 한다.
		if(passwordEncoder.matches(map.get("pw"), securityPw)) { // 역으로 디코딩해줘서
			map.put("pw", securityPw);
			return sqlSession.selectOne(NS_Stu+"student_login", map);
		}
				
		return null;
	}
	
	//비밀번호 초기화 정보 찾기
	@Override
	public String student_pwre(Map<String, String> map) {
		logger.info("student_pwre Dao 실행 {}", map);
		return sqlSession.selectOne(NS_Stu+"student_pwre", map);
	}

	//비밀번호 초기화
	@Override
	public boolean resetPw(Map<String, String> map) {
		logger.info("resetPw Dao 실행 {}", map);
		return ((sqlSession.update(NS_Stu+"resetPw", map)) > 0);
	}

	//회원가입
	@Override
	public boolean student_join(Student_DTO dto) {
		logger.info("student_join Dao 실행 {}", dto);
		String encodePw = passwordEncoder.encode(dto.getPw());
		dto.setPw(encodePw);
		
		return ((sqlSession.insert(NS_Stu+"student_join", dto)) > 0);
	}
	
	//아이디 중복 체크
	@Override
	public String student_duplicate(String id) {
		logger.info("student_duplicate Dao 실행 {}", id);
		return sqlSession.selectOne(NS_Stu+"student_duplicate", id);
	}
	

	//과정 조회
	@Override
	public List<Course_DTO> student_course(String id) {
		logger.info("student_course Dao 실행 {}", id);
		return sqlSession.selectList(NS_Stu+"student_course", id);
	}

	//정보 조회
	@Override
	public Student_DTO student_info(String id) {
		logger.info("student_info Dao 실행 {}", id);
		return sqlSession.selectOne(NS_Stu+"student_info", id);
	}

	//정보 수정
	@Override
	public boolean student_modify(Student_DTO dto) {
		logger.info("student_modify Dao 실행 {}", dto);
		return ((sqlSession.update(NS_Stu+"student_modify", dto)) > 0 );
	}

	/* --------------------   강사   ------------------------*/
	//로그인
	@Override
	public Teacher_DTO teacher_login(Map<String, String> map) {
		logger.info("teacher_login Dao 실행 {}", map);
		//좋은 방법
		//입력한 id에 해당하는 pw(DB)
		String securityPw = sqlSession.selectOne(NS_Stu+"teacher_login_find", map.get("id")); //$2a$10$GgCM.MtWE2uQlI6OP/0oSe9cgBhFUeEBqNHj3gP4/n4mI56KnE9ZW
		
		//dto.getPw() : 내가 로그인시 입력한 비밀번호(1111)
		//$2a$10$GgCM.MtWE2uQlI6OP/0oSe9cgBhFUeEBqNHj3gP4/n4mI56KnE9ZW -> 1111로 디코딩해서 두개가 일치하는지 확인 
		//이 순서로 넣어주어야 한다.
		if(passwordEncoder.matches(map.get("pw"), securityPw)) { // 역으로 디코딩해줘서
			map.put("pw", securityPw);
			return sqlSession.selectOne(NS_Tea+"teacher_login", map);
		}
				
		return null;
		
	}

	//정보 조회
	@Override
	public Teacher_DTO teacher_info(String id) {
		logger.info("teacher_info Dao 실행 {}", id);
		return sqlSession.selectOne(NS_Tea+"teacher_info", id);
	}

	//정보 수정
	@Override
	public boolean teacher_modify(Map<String, String> map) {
		logger.info("teacher_modify Dao 실행 {}", map);
		return ((sqlSession.update(NS_Tea+"teacher_modify", map)) > 0);
	}

	//담당 과정 수강 학생 조회
	@Override
	public List<Student_DTO> teacher_student_list(RowNum_DTO dto) {
		logger.info("teacher_student_list Dao 실행 {}", dto);
		return sqlSession.selectList(NS_Tea+"teacher_student_list", dto);
	}

	/* --------------------   관리자   ------------------------*/
	//로그인
	@Override
	public Admin_DTO admin_login(Map<String, String> map) {
		logger.info("admin_login Dao 실행 {}", map);
		return sqlSession.selectOne(NS_Adm+"admin_login", map);
	}

	//정보 조회
	@Override
	public Admin_DTO admin_info(String id) {
		logger.info("admin_info Dao 실행 {}", id);
		return sqlSession.selectOne(NS_Adm+"admin_info", id);
	}

	//정보 수정
	@Override
	public boolean admin_modify(Map<String, String> map) {
		logger.info("admin_modify Dao 실행 {}", map);
		return ((sqlSession.update(NS_Adm+"admin_modify", map)) > 0 );
	}

	//회원가입 신청 조회
	@Override
	public List<Student_DTO> admin_accept_list(RowNum_DTO dto) {
		logger.info("admin_accept_list Dao 실행 {}", dto);
		return sqlSession.selectList(NS_Adm+"admin_accept_list", dto);
	}

	//회원가입 신청 승인
	@Override
	public boolean admin_accept(Map<String, String[]> map) {
		logger.info("admin_accept Dao 실행 {}", map);
		return ((sqlSession.update(NS_Adm+"admin_accept", map)) > 0);
	}

	//회원가입 신청 거절
	@Override
	public boolean admin_accept_refuse(Map<String, String[]> map) {
		logger.info("admin_accept_refuse Dao 실행 {}", map);
		return ((sqlSession.delete(NS_Adm+"admin_accept_refuse", map)) > 0);
	}

	//강사 조회
	@Override
	public List<Teacher_DTO> admin_teacher_list(RowNum_DTO dto) {
		logger.info("admin_teacher_list Dao 실행 {}", dto);
		return sqlSession.selectList(NS_Adm+"admin_teacher_list", dto);
	}

	//강사 탈퇴
	@Override
	public boolean admin_teacher_delete(String id) {
		logger.info("admin_teacher_delete Dao 실행 {}", id);
		return ((sqlSession.delete(NS_Adm+"admin_teacher_delete", id)) > 0 );
	}

	//강사 등록
	@Override
	public boolean admin_teacher_add(Teacher_DTO dto) {
		logger.info("admin_teacher_add Dao 실행 {}", dto);
		return ((sqlSession.insert(NS_Adm+"admin_teacher_add", dto)) > 0 );
	}

	//강사 정보 수정
	@Override
	public boolean admin_teacher_modify(Map<String, String> map) {
		logger.info("admin_teacher_modify Dao 실행 {}", map);
		return ((sqlSession.update(NS_Adm+"admin_teacher_modify", map)) > 0 );
	}

	//전체 학생 조회
	@Override
	public List<Student_DTO> admin_student_list(RowNum_DTO dto) {
		logger.info("admin_student_list Dao 실행 {}", dto);
		return sqlSession.selectList(NS_Adm+"admin_student_list", dto);
	}

	//학생 탈퇴
	@Override
	public boolean admin_student_delete(Map<String, String[]> map) {
		logger.info("admin_student_delete Dao 실행 {}", map);
		return ((sqlSession.update(NS_Adm+"admin_student_delete", map)) > 0 );
	}

	//학생 상세 조회
	@Override
	public Student_DTO admin_student_detail(String id) {
		logger.info("admin_student_detail Dao 실행 {}", id);
		return sqlSession.selectOne(NS_Adm+"admin_student_detail", id);
	}

	//학생 정보 수정
	@Override
	public boolean admin_student_modify(Map<String, String> map) {
		logger.info("admin_student_modify Dao 실행 {}", map);
		return ((sqlSession.update(NS_Adm+"admin_student_modify", map)) > 0 );
	}

	//학생 과정 연결 조회 - 본인 과정
	@Override
	public List<Course_DTO> admin_student_clist_sel(String id) {
		logger.info("admin_student_clist_sel Dao 실행 {}", id);
		return sqlSession.selectList(NS_Adm+"admin_student_clist_sel", id);
	}

	//학생 과정 연결 조회 - 모든 과정
	@Override
	public List<Course_DTO> admin_student_clist_all() {
		logger.info("admin_student_clist_all Dao 실행 ");
		return sqlSession.selectList(NS_Adm+"admin_student_clist_all");
	}

	//학생 과정 연결
	@Override
	public boolean admin_student_cconnect(UserCourse_DTO dto) {
		logger.info("admin_student_cconnect Dao 실행 {}", dto);
		return ((sqlSession.insert(NS_Adm+"admin_student_cconnect", dto)) > 0 );
	}

	//학생 과정 삭제
	@Override
	public boolean admin_student_cdelete(UserCourse_DTO dto) {
		logger.info("admin_student_cdelete Dao 실행 {}", dto);
		return ((sqlSession.delete(NS_Adm+"admin_student_cdelete", dto)) > 0 );
	}

}
