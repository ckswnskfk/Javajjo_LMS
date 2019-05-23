package happy.jaj.prj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import happy.jaj.prj.dtos.Attended_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.model.Attended_IService;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;



@Controller
public class AttendedController {

	private Logger logger = LoggerFactory.getLogger(AttendedController.class);
	

	@Autowired
	private Attended_IService attended_Iservice;
	
	
	
	
////  cal_stucos : 학생 과정 조회 (완)
//	@RequestMapping(value="/attended_Student_Course.do", method=RequestMethod.GET)
//	public String cal_stucos(HttpSession session, String id) {
////		String id = req.getParameter("id");
//		logger.info("AttendedController cal_stucos 실행");
//		List<Course_DTO> clists = attended_Iservice.cal_stucos("id");
//		session.setAttribute("clists", clists);
////		logger.info("결과 값 : {}",lists);
////		req.setAttribute("lists", lists);
////		return "attended_Student_Course";
//		return "attended_Student_Course";
//		
//		
//	}

	
	//  cal_stucos : 학생 과정 조회 (완)
	@RequestMapping(value="/attended_Student_Course.do", method=RequestMethod.GET)
	public String cal_stucos(HttpSession session, String id) {
//		String id = req.getParameter("id");
		logger.info("AttendedController cal_stucos 실행");
		List<Course_DTO> clists = attended_Iservice.cal_stucos("id");
		session.setAttribute("clists", clists);
		
		logger.info("결과 값 : {}",clists);
//		req.setAttribute("lists", lists);
		return "attended_Student_Course";
		
		
	}
	
	
	//	cal_stuatt : 학생 출결 조회(완)
//	@RequestMapping(value="/attended_Student.do", method=RequestMethod.GET)
//	public String cal_stuatt(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("AttendedController cal_stuatt 실행");
//		String id = req.getParameter("id");
//		List<Attended_DTO> lists = attended_Iservice.cal_stuatt(id);
//		req.setAttribute("lists", lists);
//		return "attended_Student";
//	}
	//	cal_stuatt : 학생 출결 조회(완)
	@RequestMapping(value="/attended_Student.do", method=RequestMethod.GET)
	public String cal_stuatt(String id,Model model) {
		List<Attended_DTO> alists = attended_Iservice.cal_stuatt(id);
		System.out.println(alists + "------------------");
		logger.info("AttendedController cal_stuatt 실행");
		model.addAttribute("alists" , alists);
		return "attended_Student";
	}
	
//	attended_Student_Main : 학생메인페이지
	@RequestMapping(value="/attended_Student_Main.do", method=RequestMethod.GET)
	public String attended_Student_Main(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController attended_Student_Main 실행");
		return "attended_Student_Main";
	}
	
//	attended_Teacher_Main : 강사메인페이지
	@RequestMapping(value="/attended_Main.do", method=RequestMethod.GET)
	public String attended_Teacher_Main(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController attended_Teacher_Main 실행");
		return "./attended_Teacher_Main";
	}
	
	//	cal_cosview : 강사 과정 조회  (완)
//	@RequestMapping(value="/attended_Teacher_Course.do", method=RequestMethod.GET)
//	public String cal_cosview(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("AttendedController attended_Teacher.do 실행");
//		String id = req.getParameter("id");
//		Course_DTO dto = attended_Iservice.cal_cosview(id);
//		req.setAttribute("dto", dto);
//		return "attended_Teacher";
//	}
	
//	cal_cosview : 강사 과정 조회  (완)
	@RequestMapping(value="/attended_Teacher_Course.do", method=RequestMethod.GET)
	public String cal_cosview(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController attended_Teacher.do 실행");
		String id = req.getParameter("id");
		Course_DTO dto = attended_Iservice.cal_cosview(id);
		req.setAttribute("dto", dto);
		return "attended_Teacher";
	}
	
	//	cal_monlist : 강사 캘린더 출결 조회 C201900001 (완)
	@RequestMapping(value="/attended_Teacher.do", method=RequestMethod.GET)
	public String cal_monlist(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController cal_monlist 실행");
		Map<String, String> map = new HashMap<String, String>();
		
		String coursecode = req.getParameter("coursecode");
		String regdate = req.getParameter("regdate");
		
		
		map.put("coursecode", coursecode);
		map.put("regdate", regdate);
		
		List<Attended_DTO> lists = attended_Iservice.cal_monlist(map);
		req.setAttribute("map", map);
		req.setAttribute("lists", lists);
		return "attended_Teacher";
	}
	//	cal_daylist : 강사 출석부 조회(완)
//	@RequestMapping(value="/attended_Rollbook.do", method=RequestMethod.GET)
//	public String cal_daylist(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("AttendedController cal_daylist 실행");
//		String regdate = req.getParameter("regdate");
//		List<Attended_DTO> lists = attended_Iservice.cal_daylist(regdate);
//		req.setAttribute("lists", lists);
//		return "attended_Rollbook";
//	}
	// cal_daylist :  출석부
	@RequestMapping(value="/attended_Rollbook.do", method=RequestMethod.GET)
	public String cal_daylist(Model model, String regdate ) {
		logger.info("AttendedController cal_daylist 실행");
		List<Attended_DTO> dlists = attended_Iservice.cal_daylist(regdate);
		System.out.println(dlists+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		model.addAttribute("dlists", dlists);
		return "attended_Rollbook";
	}
	
	//	cal_detail : 강사 학생 출석 상세 조회 (완)
//	@RequestMapping(value="/attended_Detail.do", method=RequestMethod.GET)
//	public String cal_detail(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("AttendedController cal_detail 실행");
//		String id = req.getParameter("id");
//		List<Student_DTO> lists = attended_Iservice.cal_detail(id);
//		req.setAttribute("lists", lists);
//		return "attended_Detail";
//	}
	@RequestMapping(value="/attended_Detail.do", method=RequestMethod.GET)
	public String cal_detail(Model model, String id) {
		logger.info("AttendedController cal_detail 실행");
		List<Student_DTO> slists = attended_Iservice.cal_detail(id);
		model.addAttribute("slists", slists);
		System.out.println(slists+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		return "attended_Detail";
	}
	//	 cal_sms결석 문자 발송 ( 생각중 )
	@RequestMapping(value="/attended_SMS.do", method=RequestMethod.GET)
	public String cal_sms(HttpServletRequest req) {
		logger.info("AttendedController cal_sms 실행");
		String id = req.getParameter("id");
		String a_check = req.getParameter("a_check");
		List<Attended_DTO> lists = attended_Iservice.cal_sms(id); 
		
		String api_key = "NCSBBEI5PLLEXGD2";
		String api_secret = "OFVHZ33HQYWVQSCCZRWTYRWZNE8ZT1LU";
		
		Message coolsms = new Message(api_key, api_secret);
		HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", id); // 수신번호
	    params.put("from", "01039102218");
	    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
	    params.put("text", "오늘 결석 하셨네요. 내일은 제발 출석해주세요."); // 문자내용    
	    params.put("app_version", "JAVA SDK v1.2"); // application name and version

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("a_check", a_check);
		if(a_check==null) {
			logger.info(" ------------------------------: "+id+a_check);
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
		return "attended_Rollbook";
	
		
	}
	
	
	
	
//		cal_attended : 출석 및 퇴실,결석
	@RequestMapping(value="/beacon_Attended.do", method=RequestMethod.GET)
	public String cal_attended(HttpServletRequest req, HttpServletResponse resp, String seq, String id, String name, String a_check, String regdate) {
		logger.info("AttendedController cal_attended 실행");
		Attended_DTO dto = new Attended_DTO(seq, id, name, a_check, regdate);
	
//		boolean isc = attended_Iservice.cal_attended(dto);
		
		return "attended_index";
	}
	
	
	
	
	
}
