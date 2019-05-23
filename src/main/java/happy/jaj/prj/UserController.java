package happy.jaj.prj;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.dtos.Teacher_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;
import happy.jaj.prj.model.User_IService;
import happy.jaj.prj.util.Random_Number;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
public class UserController {
	
private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private  User_IService user_IService;
	
	@RequestMapping(value="/main.do", method=RequestMethod.GET)
	//loginForm.do 처음 로그인 화면
	public String mainForm() {
		logger.info("Controller mainform");
		return "main";
	}	
	
	@RequestMapping(value="/loginForm.do", method=RequestMethod.GET)
	//loginForm.do 처음 로그인 화면
	public String loginForm() {
		logger.info("Controller loginForm");
		return "loginForm";
	}	
	
//	logout.do
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logOut(HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map = (Map<String, String>) session.getAttribute("mem");
		if(map!=null) {
			session.removeAttribute("member"); //invalidate하면 모든 session이 다 사라짐, remove는 하나의 객체의 session만 지움
		}
		
		return "redirect:/loginForm.do";
	}
	
	/* --------------------   학생    ------------------------*/
	//로그인
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String student_login(@RequestParam Map<String, String> map, HttpSession session) {
		logger.info("UserController student_login 실행");
		Student_DTO Sdto = user_IService.student_login(map);
		Map<String, String> mapSession = new HashMap<String, String>();
		mapSession.put("table", "Student");
		mapSession.put("id", Sdto.getId());
		mapSession.put("name", Sdto.getName());
		session.setAttribute("member", mapSession);
		return "redirect:/main.do";
	}
	
	//비밀번호 초기화 폼
	@RequestMapping(value="/student_pwReset.do", method=RequestMethod.GET)
	public String student_pwReset() {
		logger.info("UserController student_pwReset 실행");
		return "pwResetForm";
	}
	
	//비밀번호 초기화 정보 찾기
	@RequestMapping(value="/student_pwre.do", method=RequestMethod.POST, produces="application/text; charset=UTF-8")
	@ResponseBody
	public String student_pwre(@RequestParam Map<String, String> map) {
		logger.info("UserController student_pwre 실행");
		String chk = user_IService.student_pwre(map);
		logger.info("아이디 초기화 정보 찾기 {}",chk);
		return (chk != null)?"true":"false"; // 아이디가 있을 경우 true
	}
	
	//비밀번호 초기화
	@RequestMapping(value="/password_Reset.do", method=RequestMethod.POST)
	public String resetPw(Student_DTO dto) {
		logger.info("UserController resetPw 실행");
		Random_Number rn = new Random_Number();
		String pw = rn.Random_Pw();
		
		String api_key = "NCSBBEI5PLLEXGD2";
		String api_secret = "OFVHZ33HQYWVQSCCZRWTYRWZNE8ZT1LU";
		
		Message coolsms = new Message(api_key, api_secret);
		HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", dto.getId()); // 수신번호
	    params.put("from", "01065491058");
	    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
	    params.put("text", "[JAJ]본인확인 인증번호["+pw+"]입니다.\"타인 노출 금지\""); // 문자내용    
	    params.put("app_version", "JAVA SDK v1.2"); // application name and version
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", dto.getId());
		map.put("pw", pw);
		boolean isc = user_IService.resetPw(map);
		if(isc) {
			logger.info("초기화된 비밀 번호 ------------------------------: "+pw+isc);
			try {
				JSONObject result = coolsms.send(params);
				if ((long)result.get("success_count") > 0) {
			          // 메시지 보내기 성공 및 전송결과 출력
			          System.out.println("성공");            
			          System.out.println("group_id : "+result.get("group_id")); // 그룹아이디
			          System.out.println("result_code : "+result.get("result_code")); // 결과코드
			          System.out.println("result_message"+result.get("result_message"));  // 결과 메시지
			          System.out.println("success_count"+result.get("success_count")); // 메시지아이디
			          System.out.println("error_count"+result.get("error_count"));  // 여러개 보낼시 오류난 메시지 수
			      } else {
			          // 메시지 보내기 실패
			          System.out.println("실패");
			          System.out.println(result.get("code")); // REST API 에러코드
			          System.out.println(result.get("message")); // 에러메시지
			      } 
			} catch (CoolsmsException e) {
				e.printStackTrace();
			}
		}
		return "loginForm";
	}
	
	//회원가입 폼
	@RequestMapping(value="/student_joinForm.do", method=RequestMethod.GET)
	public String student_joinForm() {
		logger.info("UserController student_joinForm 실행");
		return "student_joinForm";
	}
	//회원가입
	@RequestMapping(value="/student_join.do", method=RequestMethod.POST)
	public String student_join(Student_DTO dto) {
		logger.info("UserController student_join 실행");
		boolean isc = user_IService.student_join(dto);
		if(isc) {
			logger.info("회원가입 완료");
		}
		return "loginForm";
	}
	
	//과정 조회
	@RequestMapping(value="/student_course.do", method=RequestMethod.GET)
	public String student_course(Course_DTO dto, Model model) {
		logger.info("UserController student_course 실행");
		List<Course_DTO> lists = user_IService.student_course(dto.getId());
		model.addAttribute("lists", lists);
		return "student_Course";
	}
	
	//정보 조회
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/student_info.do", method=RequestMethod.GET)
	public String student_info(Model model, HttpSession session) {
		logger.info("UserController student_info 실행");
		Map<String, String> map = (Map<String, String>) session.getAttribute("member");
		String id = map.get("id");
		Student_DTO dto = user_IService.student_info(id);
		model.addAttribute("dto", dto);
		return "student_Mypage";
	}
	
	//정보 수정 폼 이동
	@RequestMapping(value="/student_Modify_Form.do", method=RequestMethod.GET)
	public String student_modify_form() {
		logger.info("UserController student_modify_form 실행");
		return "student_Modify";
	}
	
	//정보 수정
	@RequestMapping(value="/student_modify.do", method=RequestMethod.POST)
	public String student_modify(Student_DTO dto, HttpSession session) {
		logger.info("UserController student_modify 실행");
		boolean isc = user_IService.student_modify(dto);
		if(isc) {
			logger.info("------------------- 정보 수정 완료 ---------------- 학생");
			Map<String, String> mapSession = new HashMap<String, String>();
			mapSession.put("table", "Student");
			mapSession.put("id", dto.getId());
			mapSession.put("name", dto.getName());
			session.setAttribute("member", mapSession);
		}
		return "redirect:/main.do";
	}
	
	/* --------------------   강사   ------------------------*/
	//로그인
	@RequestMapping(value="/teacher_login.do", method=RequestMethod.POST)
	public String teacher_login(@RequestParam Map<String, String> map,HttpSession session) {
		logger.info("UserController teacher_login 실행");
		Teacher_DTO dto = user_IService.teacher_login(map);
		Map<String, String> mapSession = new HashMap<String, String>();
		mapSession.put("table", "Teacher");
		mapSession.put("id", dto.getId());
		mapSession.put("name",dto.getName());
		session.setAttribute("member", mapSession);
		return "redirect:/main.do";
	}
	
	//정보 조회
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/teacher_info.do", method=RequestMethod.GET)
	public String teacher_info(Model model,HttpSession session) {
		logger.info("UserController teacher_info 실행");
		Map<String, String> map = (Map<String, String>) session.getAttribute("member");
		String id = map.get("id");
		Teacher_DTO dto =  user_IService.teacher_info(id);
		model.addAttribute("dto", dto);
		return "teacher_Mypage";
	}
	
	//정보 수정 폼 이동
	@RequestMapping(value="/teacher_Modify_Form.do", method=RequestMethod.GET)
	public String teacher_Modify_Form() {
		logger.info("UserController teacher_Modify_Form 실행");
		return "teacher_Modify";
	}
	
	//정보 수정
	@RequestMapping(value="/teacher_modify.do", method=RequestMethod.POST)
	public String teacher_modify(@RequestParam Map<String, String> map, HttpSession session) {
		logger.info("UserController teacher_modify 실행");
		boolean isc = user_IService.teacher_modify(map);
		if(isc) {
			logger.info("--------------------------- 정보 수정 완료 ------------강사 ");
			Map<String, String> mapSession = new HashMap<String, String>();
			mapSession.put("table", "Teacher");
			mapSession.put("id", map.get("id"));
			mapSession.put("name", map.get("name"));
			session.setAttribute("member", mapSession);
		}
		return "redirect:/main.do";
	}
	
	//담당 과정 수강 학생 조회
	@RequestMapping(value="/teacher_student_list.do", method=RequestMethod.GET)
	public String teacher_student_list(RowNum_DTO dto, Model model) {
		logger.info("UserController teacher_student_list 실행");
		List<Student_DTO> lists = user_IService.teacher_student_list(dto);
		model.addAttribute("lists", lists);
		return "teacher_Course";
	}
	
	/* --------------------   관리자   ------------------------*/
	//로그인
	@RequestMapping(value="/admin_login.do", method=RequestMethod.POST)
	public String admin_login(@RequestParam Map<String, String> map,HttpSession session) {
		logger.info("UserController admin_login 실행");
		Admin_DTO Adto = user_IService.admin_login(map);
		Map<String, String> mapSession = new HashMap<String, String>();
		mapSession.put("table", "Admin");
		mapSession.put("id", Adto.getId());
		mapSession.put("name", Adto.getName());
		session.setAttribute("member", mapSession);
		return "redirect:/main.do";
	}
	
	//정보 조회
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/admin_info.do", method=RequestMethod.GET)
	public String admin_info(Model model, HttpSession session) {
		logger.info("UserController admin_info 실행");
		Map<String, String> map = (Map<String, String>) session.getAttribute("member");
		String id = map.get("id");
		Admin_DTO dto = user_IService.admin_info(id);
		model.addAttribute("dto", dto);
		return "admin_Mypage";
	}
	
	//정보 수정 폼 이동
	@RequestMapping(value="/admin_Modify_Form.do", method=RequestMethod.GET)
	public String admin_Modify_Form() {
		logger.info("UserController admin_Modify_Form 실행");
		return "admin_Modify";
	}
	
	//정보 수정
	@RequestMapping(value="/admin_modify.do", method=RequestMethod.GET)
	public String admin_modify(@RequestParam Map<String, String> map, HttpSession session) {
		logger.info("UserController admin_modify 실행");
		boolean isc = user_IService.admin_modify(map);
		if(isc) {
			logger.info("--------------------------- 정보 수정 완료 ------------관리자 ");
			Map<String, String> mapSession = new HashMap<String, String>();
			mapSession.put("table", "Admin");
			mapSession.put("id", map.get("id"));
			mapSession.put("name", map.get("name"));
			session.setAttribute("member", mapSession);
		}
		return "redirect:/main.do";
	}
	
	//회원가입 신청 조회
	@RequestMapping(value="/admin_accept_list.do", method=RequestMethod.GET)
	public String admin_accept_list(Model model) {
		logger.info("UserController admin_accept_list 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<Student_DTO> lists = user_IService.admin_accept_list(dto);
		model.addAttribute("lists", lists);
		return "admin_Accept";
	}
	
	//회원가입 신청 승인
	@RequestMapping(value="/admin_accept.do", method=RequestMethod.GET)
	public String admin_accept(String[] id) {
		logger.info("UserController admin_accept 실행");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("list", id);
		boolean isc = user_IService.admin_accept(map);
		if(isc) {
			logger.info("--------------------------- 가입 신청 승인 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//회원가입 신청 거절
	@RequestMapping(value="/admin_accept_refuse.do", method=RequestMethod.GET)
	public String admin_accept_refuse(String[] id) {
		logger.info("UserController admin_accept_refuse 실행");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("list", id);
		boolean isc = user_IService.admin_accept_refuse(map);
		if(isc) {
			logger.info("--------------------------- 가입 신청 거절 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//강사 조회
	@RequestMapping(value="/admin_teacher_list.do", method=RequestMethod.GET)
	public String admin_teacher_list(Model model) {
		logger.info("UserController admin_teacher_list 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<Teacher_DTO> lists = user_IService.admin_teacher_list(dto);
		model.addAttribute("lists", lists);
		return "admin_Teacher_List";
	}
	
	//강사 탈퇴
	@RequestMapping(value="/admin_teacher_delete.do", method=RequestMethod.GET)
	public String admin_teacher_delete(String id) {
		logger.info("UserController admin_teacher_delete 실행");
		boolean isc = user_IService.admin_teacher_delete(id);
		if(isc) {
			logger.info("--------------------------- 강사 탈퇴 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//강사 등록
	@RequestMapping(value="/admin_teacher_add.do", method=RequestMethod.GET)
	public String admin_teacher_add(Teacher_DTO dto) {
		logger.info("UserController admin_teacher_add 실행");
		boolean isc = user_IService.admin_teacher_add(dto);
		if(isc) {
			logger.info("--------------------------- 강사 등록 완료------------관리자 ");
		}
		return "jemin_index";
	}
	
	//강사 정보 수정
	@RequestMapping(value="/admin_teacher_modify.do", method=RequestMethod.GET)
	public String admin_teacher_modify(@RequestParam Map<String, String> map) {
		logger.info("UserController admin_teacher_modify 실행");
		boolean isc = user_IService.admin_teacher_modify(map);
		if(isc) {
			logger.info("--------------------------- 강사 정보 수정 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//전체 학생 조회
	@RequestMapping(value="/admin_student_list.do", method=RequestMethod.GET)
	public String admin_student_list(Model model) {
		logger.info("UserController admin_student_list 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<Student_DTO> lists = user_IService.admin_student_list(dto);
		model.addAttribute("lists", lists);
		return "admin_Student_List";
	}
	
	//학생 탈퇴
	@RequestMapping(value="/admin_student_delete.do", method=RequestMethod.GET)
	public String admin_student_delete(String[] id) {
		logger.info("UserController admin_student_delete 실행");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("list", id);
		boolean isc = user_IService.admin_student_delete(map);
		if(isc) {
			logger.info("--------------------------- 학생 탈퇴 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//학생 상세 조회
	@RequestMapping(value="/admin_student_detail.do", method=RequestMethod.GET)
	public String admin_student_detail(String id, Model model, HttpSession session) {
		logger.info("UserController admin_student_detail 실행");
		Student_DTO dto = user_IService.admin_student_detail(id);
		session.setAttribute("id", id);
		model.addAttribute("dto", dto);
		return "admin_Student_Detail";
	}
	
	//학생 정보 수정 폼 이동
	@RequestMapping(value="/admin_student_modify_Form.do", method=RequestMethod.GET)
	public String admin_student_modify_form(HttpSession session) {
		logger.info("UserController admin_student_modify_form 실행");
		return "admin_Student_Modify";
	}
	
	//학생 정보 수정
	@RequestMapping(value="/admin_student_modify.do", method=RequestMethod.POST)
	public String admin_student_modify(@RequestParam Map<String, String> map) {
		logger.info("UserController admin_student_modify 실행");
		boolean isc = user_IService.admin_student_modify(map);
		if(isc) {
			logger.info("--------------------------- 학생 정보 수정 완료 ------------관리자 ");
		}
		return "redirect:/admin_student_list.do";
	}
	
	//학생 과정 연결 조회 - 본인 과정
	@RequestMapping(value="/admin_student_course.do", method=RequestMethod.GET)
	public String admin_student_clist_sel(Model model, HttpSession session) {
		logger.info("UserController admin_student_clist_sel 실행");
		String id = (String) session.getAttribute("id");
	    List<Course_DTO> Slists = user_IService.admin_student_clist_sel(id);
	    List<Course_DTO> Alists = user_IService.admin_student_clist_all();
	    model.addAttribute("Slists", Slists);
	    model.addAttribute("Alists", Alists);
		return "admin_Student_Course";
	}
	
	//학생 과정 연결
	@RequestMapping(value="/admin_student_cconnect.do", method=RequestMethod.GET)
	public String admin_student_cconnect(UserCourse_DTO dto) {
		logger.info("UserController admin_student_cconnect 실행");
		boolean isc = user_IService.admin_student_cconnect(dto);
		if(isc) {
			logger.info("--------------------------- 학생 과정 연결 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
	
	//학생 과정 삭제
	@RequestMapping(value="/admin_student_cdelete.do", method=RequestMethod.GET)
	public String admin_student_cdelete(UserCourse_DTO dto) {
		logger.info("UserController admin_student_cdelete 실행");
		boolean isc = user_IService.admin_student_cdelete(dto);
		if(isc) {
			logger.info("--------------------------- 학생 과정 삭제 완료 ------------관리자 ");
		}
		return "jemin_index";
	}
		
}
