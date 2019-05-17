package happy.jaj.prj;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.dtos.Teacher_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;
import happy.jaj.prj.model.User_IService;
import happy.jaj.prj.util.Random_Number;

@Controller
public class UserController {
	
private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private  User_IService user_IService;
	
	/* --------------------   학생    ------------------------*/
	//로그인
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String student_login(HttpServletRequest req) {
		logger.info("UserController student_login 실행");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		Student_DTO dto = user_IService.student_login(map);
		req.setAttribute("dto", dto);
		return "jemin_index";
	}
	
	//비밀번호 초기화
	@RequestMapping(value="/password_Reset.do", method=RequestMethod.GET)
	public String resetPw(HttpServletRequest req) {
		logger.info("UserController resetPw 실행");
		Random_Number rn = new Random_Number();
		String id = req.getParameter("id");
		String pw = rn.Random_Pw();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		boolean isc = user_IService.resetPw(map);
		if(isc) {
			logger.info("초기화된 비밀 번호 ------------------------------: "+pw+isc);
		}
		return "jemin_index";
	}

	//회원가입
	@RequestMapping(value="/student_join.do", method=RequestMethod.GET)
	public String student_join(HttpServletRequest req) {
		logger.info("UserController student_join 실행");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pw = req.getParameter("pw");
		String gender = req.getParameter("gender");
		String birth = req.getParameter("birth");
		String addr = req.getParameter("addr");
		Student_DTO dto = new Student_DTO(id, name, pw, gender, birth, addr, "");
		boolean isc = user_IService.student_join(dto);
		if(isc) {
			logger.info("회원가입 완료");
		}
		return "jemin_index";
	}
	
	//과정 조회
	@RequestMapping(value="/student_course.do", method=RequestMethod.GET)
	public String student_course(HttpServletRequest req) {
		logger.info("UserController student_course 실행");
		String id = req.getParameter("id");
		List<Course_DTO> lists = user_IService.student_course(id);
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	
	//정보 조회
	@RequestMapping(value="/student_info.do", method=RequestMethod.GET)
	public String student_info(HttpServletRequest req) {
		logger.info("UserController student_info 실행");
		String id = req.getParameter("id");
		Student_DTO dto = user_IService.student_info(id);
		req.setAttribute("dto",dto);
		return "jemin_index";
	}
	
	//정보 수정
	@RequestMapping(value="/student_modify.do", method=RequestMethod.GET)
	public String student_modify(HttpServletRequest req) {
		logger.info("UserController student_modify 실행");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String addr = req.getParameter("addr");
		Student_DTO dto = new Student_DTO(id, name, pw, gender, "", addr,"");
		boolean isc = user_IService.student_modify(dto);
		if(isc) {
			logger.info("------------------- 정보 수정 완료 ---------------- 학생");
		}
		return "jemin_index";
	}
	
	/* --------------------   강사   ------------------------*/
	//로그인
	@RequestMapping(value="/teacher_login.do", method=RequestMethod.GET)
	public String teacher_login(HttpServletRequest req) {
		logger.info("UserController teacher_login 실행");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		Teacher_DTO dto = user_IService.teacher_login(map);
		req.setAttribute("dto", dto);
		return "jemin_index";
	}
	
	//정보 조회
	@RequestMapping(value="/teacher_info.do", method=RequestMethod.GET)
	public String teacher_info(HttpServletRequest req) {
		logger.info("UserController teacher_info 실행");
		String id = req.getParameter("id");
		Teacher_DTO dto =  user_IService.teacher_info(id);
		req.setAttribute("dto", dto);
		return "jemin_index";
	}
	
	//정보 수정
	@RequestMapping(value="/teacher_modify.do", method=RequestMethod.GET)
	public String teacher_modify(HttpServletRequest req) {
		logger.info("UserController teacher_modify 실행");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		map.put("name", name);
		boolean isc = user_IService.teacher_modify(map);
		if(isc) {
			logger.info("--------------------------- 정보 수정 완료 ------------강사 ");
		}
		return "jemin_index";
	}
	
	//담당 과정 수강 학생 조회
	@RequestMapping(value="/teacher_student_list.do", method=RequestMethod.GET)
	public String teacher_student_list(HttpServletRequest req) {
		logger.info("UserController teacher_student_list 실행");
		String coursecode = req.getParameter("coursecode");
		RowNum_DTO dto = new RowNum_DTO();
		dto.setCoursecode(coursecode);
		List<Student_DTO> lists = user_IService.teacher_student_list(dto);
		req.setAttribute("lists",lists);
		return "jemin_index";
	}
	
	/* --------------------   관리자   ------------------------*/
	//로그인
	@RequestMapping(value="/admin_login.do", method=RequestMethod.GET)
	public String admin_login(HttpServletRequest req) {
		logger.info("UserController admin_login 실행");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		Admin_DTO dto = user_IService.admin_login(map);
		req.setAttribute("dto", dto);
		return "jemin_index";
	}
	
	//정보 조회
	@RequestMapping(value="/admin_info.do", method=RequestMethod.GET)
	public String admin_info(HttpServletRequest req) {
		logger.info("UserController admin_info 실행");
		String id = req.getParameter("id");
		Admin_DTO dto = user_IService.admin_info(id);
		req.setAttribute("dto", dto);
		return "jemin_index";
	}
	
	//정보 수정
	@RequestMapping(value="/admin_modify.do", method=RequestMethod.GET)
	public String admin_modify(HttpServletRequest req) {
		logger.info("UserController admin_modify 실행");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		map.put("name", name);
		boolean isc = user_IService.admin_modify(map);
		if(isc) {
			logger.info("--------------------------- 정보 수정 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//회원가입 신청 조회
	@RequestMapping(value="/admin_accept_list.do", method=RequestMethod.GET)
	public String admin_accept_list(HttpServletRequest req) {
		logger.info("UserController admin_accept_list 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<Student_DTO> lists = user_IService.admin_accept_list(dto);
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	
	//회원가입 신청 승인
	@RequestMapping(value="/admin_accept.do", method=RequestMethod.GET)
	public String admin_accept(HttpServletRequest req) {
		logger.info("UserController admin_accept 실행");
		String [] id = req.getParameterValues("id");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("id", id);
		boolean isc = user_IService.admin_accept(map);
		if(isc) {
			logger.info("--------------------------- 가입 신청 승인 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//회원가입 신청 거절
	@RequestMapping(value="/admin_accept_refuse.do", method=RequestMethod.GET)
	public String admin_accept_refuse(HttpServletRequest req) {
		logger.info("UserController admin_accept_refuse 실행");
		String [] id = req.getParameterValues("id");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("id", id);
		boolean isc = user_IService.admin_accept_refuse(map);
		if(isc) {
			logger.info("--------------------------- 가입 신청 승인 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//강사 조회
	@RequestMapping(value="/admin_teacher_list.do", method=RequestMethod.GET)
	public String admin_teacher_list(HttpServletRequest req) {
		logger.info("UserController admin_teacher_list 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<Teacher_DTO> lists = user_IService.admin_teacher_list(dto);
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	
	//강사 탈퇴
	@RequestMapping(value="/admin_teacher_delete.do", method=RequestMethod.GET)
	public String admin_teacher_delete(HttpServletRequest req) {
		logger.info("UserController admin_teacher_delete 실행");
		String id = req.getParameter("id");
		boolean isc = user_IService.admin_teacher_delete(id);
		if(isc) {
			logger.info("--------------------------- 강사 탈퇴 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//강사 등록
	@RequestMapping(value="/admin_teacher_add.do", method=RequestMethod.GET)
	public String admin_teacher_add(HttpServletRequest req) {
		logger.info("UserController admin_teacher_add 실행");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String coursecode = req.getParameter("coursecode");
		Teacher_DTO dto = new Teacher_DTO(id, name, pw, coursecode);
		boolean isc = user_IService.admin_teacher_add(dto);
		if(isc) {
			logger.info("--------------------------- 강사 등록 완료------------관리자 ");
		}
		return "jemin_index";
	}
	
	//강사 정보 수정
	@RequestMapping(value="/admin_teacher_modify.do", method=RequestMethod.GET)
	public String admin_teacher_modify(HttpServletRequest req) {
		logger.info("UserController admin_teacher_modify 실행");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String coursecode = req.getParameter("coursecode");
		String find_id = req.getParameter("find_id");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		map.put("name", name);
		map.put("coursecode", coursecode);
		map.put("find_id", find_id);
		boolean isc = user_IService.admin_teacher_modify(map);
		if(isc) {
			logger.info("--------------------------- 강사 정보 수정 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//전체 학생 조회
	@RequestMapping(value="/admin_student_list.do", method=RequestMethod.GET)
	public String admin_student_list(HttpServletRequest req) {
		logger.info("UserController admin_student_list 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<Student_DTO> lists = user_IService.admin_student_list(dto);
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	
	//학생 탈퇴
	@RequestMapping(value="/admin_student_delete.do", method=RequestMethod.GET)
	public String admin_student_delete(HttpServletRequest req) {
		logger.info("UserController admin_student_delete 실행");
		String [] id = req.getParameterValues("id");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("id", id);
		boolean isc = user_IService.admin_student_delete(map);
		if(isc) {
			logger.info("--------------------------- 학생 탈퇴 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//학생 상세 조회
	@RequestMapping(value="/admin_student_detail.do", method=RequestMethod.GET)
	public String admin_student_detail(HttpServletRequest req) {
		logger.info("UserController admin_student_detail 실행");
		String id = req.getParameter("id");
		Student_DTO dto = user_IService.admin_student_detail(id);
		req.setAttribute("dto", dto);
		return "jemin_index";
	}
	
	//학생 정보 수정
	@RequestMapping(value="/admin_student_modify.do", method=RequestMethod.GET)
	public String admin_student_modify(HttpServletRequest req) {
		logger.info("UserController admin_student_modify 실행");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pw = req.getParameter("pw");
		String gender = req.getParameter("gender");
		String birth = req.getParameter("birth");
		String addr = req.getParameter("addr");
		String find_id = req.getParameter("find_id");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		map.put("pw", pw);
		map.put("gender", gender);
		map.put("birth", birth);
		map.put("addr", addr);
		map.put("find_id", find_id);
		boolean isc = user_IService.admin_student_modify(map);
		if(isc) {
			logger.info("--------------------------- 학생 정보 수정 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//학생 과정 연결 조회 - 본인 과정
	@RequestMapping(value="/admin_student_clist_sel.do", method=RequestMethod.GET)
	public String admin_student_clist_sel(HttpServletRequest req) {
		logger.info("UserController admin_student_clist_sel 실행");
		String id = req.getParameter("id");
	    List<Course_DTO> lists = user_IService.admin_student_clist_sel(id);
	    req.setAttribute("lists", lists);
		return "jemin_index";
	}
	
	//학생 과정 연결 조회 - 모든 과정
	@RequestMapping(value="/admin_student_clist_all.do", method=RequestMethod.GET)
	public String admin_student_clist_all(HttpServletRequest req) {
		logger.info("UserController admin_student_clist_all 실행");
		List<String> lists = user_IService.admin_student_clist_all();
	    req.setAttribute("lists", lists);
		return "jemin_index";
	}
	
	//학생 과정 연결
	@RequestMapping(value="/admin_student_cconnect.do", method=RequestMethod.GET)
	public String admin_student_cconnect(HttpServletRequest req) {
		logger.info("UserController admin_student_cconnect 실행");
		String id = req.getParameter("id");
		String coursecode = req.getParameter("coursecode");
		UserCourse_DTO dto = new UserCourse_DTO(id, coursecode);
		boolean isc = user_IService.admin_student_cconnect(dto);
		if(isc) {
			logger.info("--------------------------- 학생 과정 연결 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//학생 과정 삭제
	@RequestMapping(value="/admin_student_cdelete.do", method=RequestMethod.GET)
	public String admin_student_cdelete(HttpServletRequest req) {
		logger.info("UserController admin_student_cdelete 실행");
		String id = req.getParameter("id");
		String coursecode = req.getParameter("coursecode");
		UserCourse_DTO dto = new UserCourse_DTO(id, coursecode);
		boolean isc = user_IService.admin_student_cdelete(dto);
		if(isc) {
			logger.info("--------------------------- 학생 과정 삭제 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
		
}
