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
import happy.jaj.prj.dtos.UserCourse_DTO;
import happy.jaj.prj.model.Attended_Interface;

@Controller
public class AttendedController {

	private Logger logger = LoggerFactory.getLogger(AttendedController.class);
	

	@Autowired
	private Attended_Interface attended_Interface;
	
//	@Autowired
//	private User_Interface user_Interface;
	
	
	
//  cal_stucos : 학생 과정 조회
	@RequestMapping(value="/attended_Student_Course.do", method=RequestMethod.GET)
	public String cal_stucos(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController cal_stucos 실행");
		String id = req.getParameter("id");
		String coursename = req.getParameter("coursename");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("coursename", coursename);
		return "attended_index";
		
	}
	
	
	//	cal_stuatt : 학생 출결 조회
	//	cal_cosview : 강사 과정 조회
	//	cal_monlist : 강사 캘린더 출결 조회
	//	cal_daylist : 강사 출석부 조회
	//	cal_monatt : 강사 학생 출석 상세 조회
	// 결석 문자 발송 ( 생각중 )
	//	cal_attended : 출석 및 퇴실,결석
	
	
	
}
