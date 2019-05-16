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

import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Student_DTO;
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
}
