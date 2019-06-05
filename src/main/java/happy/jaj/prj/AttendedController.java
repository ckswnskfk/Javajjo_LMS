package happy.jaj.prj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	
	
	
//	attended_Student_Main : 학생메인페이지
	@RequestMapping(value="/attended_Student_Main.do", method=RequestMethod.GET)
	public String attended_Student_Main(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AttendedController attended_Student_Main 실행");
		return "attended_Student_Main";
	}
	
//	attended_Teacher_Main : 강사메인페이지
	@RequestMapping(value="/attended_Teacher_Main.do", method=RequestMethod.GET)
	public String attended_Teacher_Main() {
		logger.info("AttendedController attended_Teacher_Main 실행");
		
		
		
		return "attended_Teacher_Main";
	}
	
	
//	  cal_stucos : 학생 과정 조회 (완)	
	@RequestMapping(value="/attended_Student_Course.do", method=RequestMethod.GET)	
	public String cal_stucos(Model model, String id) {	
		logger.info("AttendedController cal_stucos 실행");	
		List<Course_DTO> clists = attended_Iservice.cal_stucos(id);	
		model.addAttribute("clists", clists);	
		logger.info("결과 값 : {}",clists);	
		return "attended_Student_Course";	

	}
	
	
//	cal_stuatt : 학생 출결 조회(완)	
	@RequestMapping(value="/attended_Student.do", method=RequestMethod.GET)	
	public String cal_stuatt(Model model, String id) {	
		logger.info("AttendedController cal_stuatt 실행");	
		List<Attended_DTO> alists = attended_Iservice.cal_stuatt(id);	
		System.out.println(alists + "------------------");	
		model.addAttribute("alists" , alists);	
		return "attended_Student";	
	}
	
	
	
//	cal_cosview : 강사 과정 조회  (완)
	@RequestMapping(value="/attended_Teacher_Course.do", method=RequestMethod.GET)
	public String cal_cosview(Model model , String id) {
		logger.info("AttendedController attended_Teacher.do 실행");
		Course_DTO cdto = attended_Iservice.cal_cosview(id);
		model.addAttribute("cdto", cdto);
		return "attended_Teacher";
	}
	

	// cal_monlist : 강사 캘린더 조회 (완)
	@RequestMapping(value="/attended_Teacher.do", method=RequestMethod.POST)
	public String cal_monlist(@RequestParam Map<String, String> map, Model model) {
		String amap = attended_Iservice.cal_monlist(map);
		System.out.println(amap);
		logger.info("AttendedController cal_monlist 실행");
		
//		String a_check = attended_Iservice.cal_monlist(map);
//		
		return "attended_Teacher";
	}
	
	// cal_daylist :  출석부
	@RequestMapping(value="/attended_Rollbook.do", method=RequestMethod.GET)
	public String cal_daylist(Model model, String regdate) {
		logger.info("AttendedController cal_daylist 실행");
		List<Attended_DTO> dlists = attended_Iservice.cal_daylist(regdate);
		System.out.println(dlists+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		model.addAttribute("dlists", dlists);
		model.addAttribute("regdate", regdate);
		
		
		
		return "attended_Rollbook";
	}
	
//	cal_detail : 강사 학생 출석 상세 조회 (완)
	@RequestMapping(value="/attended_Detail.do", method=RequestMethod.GET)
	public String cal_detail(Model model, String id) {
		logger.info("AttendedController cal_detail 실행");
		List<Student_DTO> slists = attended_Iservice.cal_detail(id);
		model.addAttribute("slists", slists);
		System.out.println(slists+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		return "attended_Detail";
	}
	
	
	//	 cal_sms결석 문자 발송 ( 완 )
	@Transactional
	@RequestMapping(value="/attended_SMS.do", method=RequestMethod.GET)
	public String cal_sms(Model model,String id, String regdate) {
		logger.info("AttendedController cal_sms 실행");
		model.addAttribute("regdate", regdate);
		
		
		String api_key = "NCSBBEI5PLLEXGD2";
		String api_secret = "OFVHZ33HQYWVQSCCZRWTYRWZNE8ZT1LU";
		
		
		Message coolsms = new Message(api_key, api_secret);
		HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", id); // 수신번호
	    params.put("from", "01065491058");
	    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
	    params.put("text", "오늘 결석 하셨네요. 내일은 제발 출석해주세요."); // 문자내용    
	    params.put("app_version", "JAVA SDK v1.2"); // application name and version

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
			
			return "redirect:/attended_Rollbook.do";
	}
	
	
	
	
//		cal_attended : 출석 및 퇴실,결석
//	@RequestMapping(value="/beacon_Attended.do", method=RequestMethod.GET)
//	public String cal_attended(HttpServletRequest req, HttpServletResponse resp, String seq, String id, String name, String a_check, String regdate) {
//		logger.info("AttendedController cal_attended 실행");
//		Attended_DTO dto = new Attended_DTO(seq, id, name, a_check, regdate);
//	
////		boolean isc = attended_Iservice.cal_attended(dto);
//		
//		return "attended_index";
//	}
	
	
	
	
	
}
