package happy.jaj.prj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.App_Form_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.model.Absent_IService;

@Controller
public class AbsentController {

	private Logger logger = LoggerFactory.getLogger(AbsentController.class);
	
	@Autowired
	private Absent_IService absent_IService;
	
	// 결석 신청 세션 비교(사용자별 신청 내역)
	@RequestMapping(value="/absent.do", method=RequestMethod.GET)
	public String absent_main(HttpSession session) {
		logger.info("AbsentController absent_main 실행");
		Map<String, String> map = (Map<String, String>) session.getAttribute("member");
		if(map.get("table").trim().equalsIgnoreCase("Student")) {
			return "redirect:/absentList.do";
		}else {
			return "redirect:/recipient_absent_list.do";
		}
	}
	
	 // 자신의 신청내역 리스트 상태별로 조회(학생)
	@RequestMapping(value="/absentList.do", method=RequestMethod.GET)
	public String student_absent_list(HttpSession session, HttpServletRequest req) {
		logger.info("AbsentController student_absent_list 실행");
		Map<String, String> lmap = (Map<String, String>) session.getAttribute("member");
		lmap.put("stm", "N");
		List<App_Form_DTO> list = absent_IService.student_absent_list(lmap);
		req.setAttribute("list", list);
		return "absent";
	}
	
	// 클릭해서 각 신청을 상세조회(처리중)
	@RequestMapping(value="/absent_detail_no.do", method=RequestMethod.GET)
	public String absent_detail_no(HttpServletRequest req) {
		logger.info("AbsentController absent_detail_no 실행");
		String seq = req.getParameter("seq");
		App_Form_DTO dto = absent_IService.absent_detail_no(seq);
		req.setAttribute("dto", dto);
		return "absent_detail";
	}
	
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
		req.setAttribute("yesMap", yesMap);
		return "chanju_index";
	}

	
	// 클릭해서 각 신청을 상세조회(반려시)
	@RequestMapping(value="/absent_detail_return.do", method=RequestMethod.GET)
	public String absent_detail_return(HttpServletRequest req) {
		logger.info("AbsentController absent_detail_return 실행");
		String seq = req.getParameter("seq");
		App_Form_DTO dto = absent_IService.absent_detail_return(seq);
		req.setAttribute("dto", dto);
		return "absent_detail";
	}
	
	// 강사, 관리자가 자신의 과정의 학생들것만의 내역서 리스트 보기
	@RequestMapping(value="/recipient_absent_list.do", method=RequestMethod.GET)
	public String recipient_absent_list(HttpSession session, HttpServletRequest req) {
		logger.info("AbsentController recipient_absent_list 실행");
		Map<String, String> lmap = (Map<String, String>) session.getAttribute("member");
		lmap.put("stm", "N");
		List<App_Form_DTO> list = absent_IService.recipient_absent_list(lmap);
		req.setAttribute("list", list);
		return "absent";
	}
	
	
	// 결석 신청
	// 결석 신청하려는 과정을 선택
	@RequestMapping(value="/absent_course.do", method=RequestMethod.GET)
	public String absent_course(HttpServletRequest req) {
		logger.info("AbsentController absent_course 실행");
		String id = req.getParameter("id");
		Course_DTO dto = absent_IService.absent_course(id);
		req.setAttribute("dto", dto);
		return "chanju_index";
	}
	
	// 관리자를 선택
	@RequestMapping(value="/absent_admin.do", method=RequestMethod.GET)
	public String absent_admin(HttpServletRequest req) {
		logger.info("AbsentController absent_admin 실행");
		Admin_DTO dto = absent_IService.absent_admin();
		req.setAttribute("dto", dto);
		return "chanju_index";
	}
	
	// 결석 신청
	@RequestMapping(value="/insert_absent_form.do", method=RequestMethod.GET)
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
		req.setAttribute("n", n);
		return "chanju_index";
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
		return "chanju_index";
	}
}
