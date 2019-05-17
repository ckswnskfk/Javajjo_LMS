package happy.jaj.prj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.model.Course_IService;

@Controller
public class CourseController {

	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	private Course_IService course_IService;
	
	@RequestMapping(value="/course_select.do", method=RequestMethod.GET)
	public String course_select(HttpServletRequest req) {
		logger.info("CourseController course_select 실행");
		List<Course_DTO> list = course_IService.course_select();
		req.setAttribute("list", list);
		return "chanju_index";
	}
	
	@RequestMapping(value="/course_cnt.do", method=RequestMethod.GET)
	public String course_cnt(HttpServletRequest req) {
		logger.info("CourseController course_cnt 실행");
		String coursename = req.getParameter("coursename");
		String startdate = req.getParameter("startdate");
		Course_DTO dto = new Course_DTO();
		dto.setCoursename(coursename);
		dto.setStartdate(startdate);
		int n = course_IService.course_cnt(dto);
		req.setAttribute("n", n);
		return "chanju_index";
	}
	
	@RequestMapping(value="/course_add.do", method=RequestMethod.GET)
	public String course_add(HttpServletRequest req) {
		logger.info("CourseController course_add 실행");
		String coursename = req.getParameter("coursename");
		String startdate = req.getParameter("startdate");
		Course_DTO dto = new Course_DTO();
		dto.setCoursename(coursename);
		dto.setStartdate(startdate);
		int n = course_IService.course_add(dto);
		req.setAttribute("n", n);
		return "chanju_index";
	}
}
