package happy.jaj.prj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.model.Course_IService;

@Controller
public class CourseController {

	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	private Course_IService course_IService;
	
	@RequestMapping(value="/course_List.do", method=RequestMethod.GET)
	public String course_select(Model model) {
		logger.info("CourseController course_select 실행");
		List<Course_DTO> list = course_IService.course_select();
		model.addAttribute("list", list);
		return "course_List";
	}
	
	@RequestMapping(value="/course_cnt.do", method=RequestMethod.GET)
	public String course_cnt(Course_DTO dto) {
		logger.info("CourseController course_cnt 실행");
		int n = course_IService.course_cnt(dto);
		return "redirect:/course_List.do";
		
	}
	
	
	
	@RequestMapping(value="/course_add.do", method=RequestMethod.POST)
	public String course_add(Course_DTO dto) {
		logger.info("CourseController course_add 실행");
		int n = course_IService.course_add(dto);
		return "redirect:/course_List.do";
	}
	
	@RequestMapping(value="/move.do", method=RequestMethod.GET)
	@ResponseBody
	public String move() {
//		JSONObject json=new JSONObject();
//		return json.toString();
		return "course_List";
	}
}
