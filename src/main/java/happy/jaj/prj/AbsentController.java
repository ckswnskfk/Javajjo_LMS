package happy.jaj.prj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.App_Form_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.model.Absent_IService;

@Controller
public class AbsentController {

	private Logger logger = LoggerFactory.getLogger(AbsentController.class);
	
	@Autowired
	private Absent_IService absent_IService;
	
	// 완성
	// 세션 비교(사용자별 신청 내역)
	@RequestMapping(value="/absent.do", method=RequestMethod.GET)
	public String absent_main(HttpSession session) {
		logger.info("AbsentController absent_main 실행");
		Map<String, String> map = (Map<String, String>) session.getAttribute("member");
		if(map.get("table").trim().equalsIgnoreCase("Student")) {
			return "redirect:/absentListForm.do";
		}else {
			return "redirect:/absentListForm.do";
		}
	}
	
	// 완성
	// 리스트 폼으로 이동
	@RequestMapping(value="/absentListForm.do", method=RequestMethod.GET)
	public String absent_List_Form() {
		logger.info("AbsentController absent_List_Form 실행");
		return "absent";
	}
	
	// 완성
	// 자신의 신청내역 리스트 상태별로 조회(학생) ajax
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/absentList.do", method=RequestMethod.POST, produces="application/text; charset=UTF-8")
	public @ResponseBody String student_absent_list(@RequestBody String stm, HttpSession session) {
		logger.info("AbsentController student_absent_list 실행 {}", stm);
		Map<String, String> lmap = (Map<String, String>) session.getAttribute("member");
		System.out.println(stm);
		String inStm = stm.substring(0, 1);
		System.out.println(inStm);
		lmap.put("stm", inStm);
		List<App_Form_DTO> list = absent_IService.student_absent_list(lmap);
		System.out.println(list.toString());
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONObject data = null;
		for (int i = 0; i < list.size(); i++) {
			data = new JSONObject();
			data.put("form_seq", list.get(i).getForm_seq());
			data.put("app_date", list.get(i).getApp_date());
			data.put("coursecode", list.get(i).getCoursecode());
			data.put("coursename", list.get(i).getCoursename());
			data.put("stm", list.get(i).getStm());
			data.put("start_date", list.get(i).getStart_date());
			data.put("recipient_id", list.get(i).getRecipient_id());
			data.put("absent_days", list.get(i).getAbsent_days());
			
			jArray.add(data);
		}
		System.out.println("===="+jArray);
		
		json.put("lists", jArray);
		System.out.println(json);
		System.out.println(json.toJSONString());
		return json.toJSONString();
	}
	
	// 완성
	// 결석신청 폼으로
	@RequestMapping(value="/to_app_form.do", method=RequestMethod.GET)
	public String to_App_Form() {
		logger.info("AbsentController to_App_Form 실행");
		return "app_Form";
	}
	
	// 승인으로 통합해서 사용
//	// 클릭해서 각 신청을 상세조회(처리중)
//	@RequestMapping(value="/absent_detail_no.do", method=RequestMethod.GET)
//	public String absent_detail_no(HttpServletRequest req) {
//		logger.info("AbsentController absent_detail_no 실행");
//		String seq = req.getParameter("seq");
//		App_Form_DTO dto = absent_IService.absent_detail_no(seq);
//		req.setAttribute("dto", dto);
//		return "absent_detail";
//	}
	
	// 완성
	// 클릭해서 각 신청을 상세조회(승인)
	@RequestMapping(value="/absent_detail_yes.do", method=RequestMethod.GET)
	public String absent_detail_yes(HttpServletRequest req) {
		logger.info("AbsentController absent_detail_yes 실행");
		String id = req.getParameter("id");
		String stm = req.getParameter("stm");
		String seq = req.getParameter("seq");
//		App_Form_DTO dto = absent_IService.absent_detail_yes(seq);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("stm", stm);
		map.put("seq", seq);
		Map<String, Object> yesMap = absent_IService.absent_detail_yes(map);
		System.out.println(yesMap);
		req.setAttribute("yesMap", yesMap);
		return "absent_detail";
	}

	// 승인으로 통합해서 사용
//	// 클릭해서 각 신청을 상세조회(반려시)
//	@RequestMapping(value="/absent_detail_return.do", method=RequestMethod.GET)
//	public String absent_detail_return(HttpServletRequest req) {
//		logger.info("AbsentController absent_detail_return 실행");
//		String seq = req.getParameter("seq");
//		App_Form_DTO dto = absent_IService.absent_detail_return(seq);
//		req.setAttribute("dto", dto);
//		return "absent_detail";
//	}
	
	// 완성
	// 강사, 관리자가 자신의 과정의 학생들것만의 내역서 리스트 보기
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/recipient_absent_list.do", method=RequestMethod.POST, produces="application/text; charset=UTF-8")
	public @ResponseBody String recipient_absent_list(@RequestBody String stm, HttpSession session, HttpServletRequest req) {
		logger.info("AbsentController recipient_absent_list 실행");
		Map<String, String> lmap = (Map<String, String>) session.getAttribute("member");
		System.out.println(stm);
		String inStm = stm.substring(0, 1);
		System.out.println(inStm);
		lmap.put("stm", inStm);
		List<App_Form_DTO> list = absent_IService.recipient_absent_list(lmap);
		System.out.println(list);
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONObject data = null;
		for (int i = 0; i < list.size(); i++) {
			data = new JSONObject();
			data.put("form_seq", list.get(i).getForm_seq());
			data.put("app_date", list.get(i).getApp_date());
			data.put("coursecode", list.get(i).getCoursecode());
			data.put("coursename", list.get(i).getCoursename());
			data.put("stm", list.get(i).getStm());
			data.put("start_date", list.get(i).getStart_date());
			data.put("recipient_id", list.get(i).getRecipient_id());
			data.put("absent_days", list.get(i).getAbsent_days());
			
			jArray.add(data);
		}
		
		
		System.out.println(jArray);
		json.put("lists", jArray);
		System.out.println(json);
		return json.toJSONString();
	}
	
	// 완성
	// 결석 신청
	// 결석 신청하려는 과정을 선택
	@RequestMapping(value="/absent_course.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Course_DTO> absent_course(@RequestBody String id, HttpServletRequest req) {
		logger.info("AbsentController absent_course 실행");
		System.out.println(id);
		String inid = id.substring(0, 11);
		System.out.println(inid);
		List<Course_DTO> list = absent_IService.absent_course(inid);
		System.out.println("list = " + list);
		Course_DTO dto = null;
		Map<String, Course_DTO> mapp = new HashMap<String, Course_DTO>();
		for (int i = 0; i < list.size(); i++) {
			dto = new Course_DTO();
			dto = list.get(i);
			mapp.put("dto"+i, dto);
		}
		System.out.println("mapp = " + mapp);
		return mapp;
	}
	
//	// 관리자를 선택 (관리자 한 명이라 안쓸것 같음)
//	@RequestMapping(value="/absent_admin.do", method=RequestMethod.GET)
//	public String absent_admin(HttpServletRequest req) {
//		logger.info("AbsentController absent_admin 실행");
//		Admin_DTO dto = absent_IService.absent_admin();
//		req.setAttribute("dto", dto);
//		return "chanju_index";
//	}
	
	// 완성
	// 결석 신청
	@RequestMapping(value="/insert_absent_form.do", method=RequestMethod.POST)
	public String insert_absent_form(HttpServletRequest req) {
		logger.info("AbsentController insert_absent_form 실행");
		App_Form_DTO dto = new App_Form_DTO();
		dto.setStudent_id(req.getParameter("id"));
		dto.setRecipient_id(req.getParameter("recipient_id"));
		dto.setCoursecode(req.getParameter("coursecode"));
		dto.setReason(req.getParameter("reason"));
		dto.setStart_date(req.getParameter("start_date"));
		dto.setAbsent_days(req.getParameter("absent_days"));
		dto.setFilename(req.getParameter("filename"));
		dto.setNewfilename(req.getParameter("newfilename"));
		int n = absent_IService.insert_absent_form(dto);
		System.out.println(n);
		return "absent";
	}

	
	// 강사 및 관리자가 미승인 사유를 작성+동시에 승인 여부 수정
	@RequestMapping(value="/insert_unapprove_reason.do", method=RequestMethod.GET)
	public String insert_unapprove_reason(HttpServletRequest req) {
		logger.info("AbsentController insert_unapprove_reason 실행");
		String seq = req.getParameter("seq");
		String unapproved_reason = req.getParameter("unapproved_reason");
		String stm = req.getParameter("stm");
		Map<String, String> map = new HashMap<String, String>();
		map.put("seq", seq);
		map.put("unapproved_reason", unapproved_reason);
		map.put("stm", stm);
		int n = absent_IService.insert_unapprove_reason(map);
		req.setAttribute("n", n);
		return "chanju_index";
	}
	
	// 강사 및 관리자가 승인을 함
	@RequestMapping(value="/update_is_approve_Yes.do", method=RequestMethod.GET)
	public String update_is_approve_Yes(HttpServletRequest req) {
		logger.info("AbsentController update_is_approve_Yes 실행");
		Map<String, String> map = new HashMap<String, String>();
		map.put("seq", req.getParameter("seq"));
		map.put("stm", req.getParameter("stm"));
		int n = absent_IService.update_is_approve_Yes(map);
		req.setAttribute("n", n);
		return "absent";
	}
}
