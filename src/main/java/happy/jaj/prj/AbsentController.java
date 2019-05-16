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

import happy.jaj.prj.dtos.App_Form_DTO;
import happy.jaj.prj.model.Absent_IService;

@Controller
public class AbsentController {

	private Logger logger = LoggerFactory.getLogger(AbsentController.class);
	
	@Autowired
	private Absent_IService absent_IService;
	
	// 자신의 신청내역 리스트 상태별로 조회(학생)
	@RequestMapping(value="/absentList.do", method=RequestMethod.GET)
	public String student_absent_list(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("AbsentController student_absent_list 실행");
		String id = req.getParameter("id");
		String stm = req.getParameter("stm");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("stm", stm);
		List<App_Form_DTO> list = absent_IService.student_absent_list(map);
		req.setAttribute("list", list);
		return "chanju_index";
	}
	
	// 클릭해서 각 신청을 상세조회(승인, 처리중)
	@RequestMapping(value="/absent_detail.do", method=RequestMethod.GET)
	public String absent_detail(HttpServletRequest req) {
		logger.info("AbsentController absent_detail 실행");
		String seq = req.getParameter("seq");
		App_Form_DTO dto = absent_IService.absent_detail(seq);
		req.setAttribute("dto", dto);
		return "chanju_index";
	}
	
	@RequestMapping(value="/select_signature.do", method=RequestMethod.GET)
	public String select_signature(HttpServletRequest req) {
		logger.info("AbsentController select_signature 실행");
		String id = req.getParameter("id");
		String seq = req.getParameter("seq");
		String stm = req.getParameter("stm");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("seq", seq);
		map.put("stm", stm);
		String newfilename = absent_IService.select_signature(map);
		req.setAttribute("newfilename", newfilename);
		return "chanju_index";
	}
	
	public String absent_detail_return(HttpServletRequest req) {
		logger.info("AbsentController absent_detail_return 실행");
		
		return "chanju_index";
	}
}
