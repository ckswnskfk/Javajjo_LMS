package happy.jaj.prj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import happy.jaj.prj.dtos.Attended_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;
import happy.jaj.prj.model.Attended_IService;

@Controller
public class AttendedController {

	private Logger logger = LoggerFactory.getLogger(AttendedController.class);
	

	@Autowired
	private Attended_IService attended_Iservice;
	
//	@Autowired
//	private User_Interface user_Interface;
	
	
	
//  cal_stucos : 학생 과정 조회 (미완)
	@RequestMapping(value="/attended_Student_Course.do", method=RequestMethod.GET)
	public String cal_stucos(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController cal_stucos 실행");
		String id = req.getParameter("id");
		List<Course_DTO> lists = attended_Iservice.cal_stucos(id);
		logger.info("결과 갑 : {}",lists);
		req.setAttribute("lists", lists);
		return "attended_index";
		
	}
	
	
	//	cal_stuatt : 학생 출결 조회(완)
	@RequestMapping(value="/attended_Student.do", method=RequestMethod.GET)
	public String cal_stuatt(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController attended_Student 실행");
		String id = req.getParameter("id");
		Attended_DTO dto = attended_Iservice.cal_stuatt(id);
		req.setAttribute("dto", dto);
		return "attended_index";
	}
	
	//	cal_cosview : 강사 과정 조회  (완)
	@RequestMapping(value="/attended_Teacher_Course.do", method=RequestMethod.GET)
	public String cal_cosview(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController attended_Teacher.do 실행");
		String id = req.getParameter("id");
		Course_DTO dto = attended_Iservice.cal_cosview(id);
		req.setAttribute("dto", dto);
		return "attended_index";
	}
	
	//	cal_monlist : 강사 캘린더 출결 조회 C201900001 
	@RequestMapping(value="/attended_Teacher.do", method=RequestMethod.GET)
	public String cal_monlist(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController attended_Teacher.do 실행");
		String coursecode = req.getParameter("coursecode");
		String regdate = req.getParameter("regdate");
		Map<String, String> map = new HashMap<String, String>();
		map.put("coursecode", coursecode);
		map.put("regdate", regdate);
		Attended_DTO dto = attended_Iservice.cal_monlist(map);
		req.setAttribute("map", map);
//		req.setAttribute("dto", dto);
		return "attended_index";
	}
	//	cal_daylist : 강사 출석부 조회
	@RequestMapping(value="/attended_Rollbook.do", method=RequestMethod.GET)
	public String cal_daylist(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController attended_Rollbook.do 실행");
		String regdate = req.getParameter("regdate");
		List<Attended_DTO> lists = attended_Iservice.cal_daylist(regdate);
		req.setAttribute("lists", lists);
		return "attended_index";
	}
	
	
	//	cal_detail : 강사 학생 출석 상세 조회 (완)
	@RequestMapping(value="/attended_Detail.do", method=RequestMethod.GET)
	public String cal_detail(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController attended_Detail.do 실행");
		String id = req.getParameter("id");
		List<Student_DTO> lists = attended_Iservice.cal_detail(id);
		req.setAttribute("lists", lists);
		return "attended_index";
	}
	// 결석 문자 발송 ( 생각중 )
	//	cal_attended : 출석 및 퇴실,결석
	@RequestMapping(value="/beacon_Attended.do", method=RequestMethod.GET)
	public String cal_attended(HttpServletRequest req, HttpServletResponse resp, String seq, String id, String name, String a_check, String regdate) {
		logger.info("AttendedController beacon_Attended.do 실행");
		Attended_DTO dto = new Attended_DTO(seq, id, name, a_check, regdate);
		boolean isc = attended_Iservice.cal_attended(dto);
		return "attended_index";
	}
	
}
