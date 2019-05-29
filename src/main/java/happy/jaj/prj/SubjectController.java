package happy.jaj.prj;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Course_Subject_DTO;
import happy.jaj.prj.dtos.Subject_DTO;
import happy.jaj.prj.dtos.Teacher_DTO;
import happy.jaj.prj.model.Subject_IService;

@Controller
public class SubjectController {

	private Logger logger = LoggerFactory.getLogger(SubjectController.class);
	
	@Autowired
	private Subject_IService subject_IService;
	
	// (강사)자신의 과정 조회
	@RequestMapping(value="/subject_Course1.do", method=RequestMethod.GET)
	public String select_course_list(Course_DTO dto,HttpSession session, Model model) {
		Map<String, String> map = (Map<String, String>) session.getAttribute("member");
		System.out.println(map.get("id")+"************************");
		logger.info("SubjectController select_course_list 실행");
		List<Course_DTO> lists = subject_IService.select_course_list(map.get("id"));
		model.addAttribute("list",lists);
		return "subject_CourseList";
	}
	
	// 전체 과목 조회
	@RequestMapping(value="/subject_select_all.do", method=RequestMethod.GET)
	public String subject_select_all(Model model,Course_Subject_DTO dto, String coursecode) {
		logger.info("SubjectController subject_select_all 실행");
		System.out.println(coursecode);
		List<Subject_DTO> list = subject_IService.subject_select_all();
		if (dto == null) {
			List<Subject_DTO> lists = subject_IService.subject_choice(coursecode);
			model.addAttribute("lists", lists);
		}else {
			List<Subject_DTO> lists = subject_IService.subject_choice(dto.getCoursecode());			
			model.addAttribute("lists", lists);
		}
		model.addAttribute("listss", list);
		model.addAttribute("dto",dto);
		System.out.println("******************************************"+dto);
		return "addSubject";
	}
	
	// 해당 과정명의 전 회차 조회
	@RequestMapping(value="/subject_pre_course.do", method=RequestMethod.GET)
	public String subject_pre_course(HttpServletRequest req) {
		logger.info("SubjectController subject_pre_course 실행");
		String coursename = req.getParameter("coursename");
		String coursecnt = req.getParameter("coursecnt");
		Course_DTO dto = new Course_DTO();
		dto.setCoursename(coursename);
		dto.setCoursecnt(coursecnt);
		List<Subject_DTO> list = subject_IService.subject_pre_course(dto);
		req.setAttribute("list", list);
		return "chanju_index";
	}
	
//	// 과정에 해당하는 과목 조회
//	@RequestMapping(value="/subject_choice.do", method=RequestMethod.GET)
//	public String subject_choice(HttpServletRequest req) {
//		logger.info("SubjectController subject_choice 실행");
//		String coursecode = req.getParameter("coursecode");
//		List<Subject_DTO> list = subject_IService.subject_choice(coursecode);
//		req.setAttribute("list", list);
//		return "chanju_index";
//	}
	
	// 과정에 과목 추가
	@RequestMapping(value="/subject_add_course.do", method=RequestMethod.GET)
	public String subject_add_course(HttpServletRequest req) {
		logger.info("SubjectController subject_add_course 실행");
		String coursecode = req.getParameter("coursecode");
		String subjectcode = req.getParameter("subjectcode");
		String subjecttime = req.getParameter("subjecttime");
		String content = req.getParameter("content");
		String startdate = req.getParameter("startdate");
		Course_Subject_DTO dto = new Course_Subject_DTO(coursecode, subjectcode, subjecttime, content, startdate);
		int n = subject_IService.subject_add_course(dto);
		req.setAttribute("n", n);
		return "chanju_index";
	}
	
	// 새로운 과목 생성
	@RequestMapping(value="/subject_add.do", method={RequestMethod.GET, RequestMethod.POST})
	public String subject_add(Subject_DTO dto, String coursecode) {
		logger.info("SubjectController subject_add 실행");
		int n = subject_IService.subject_add(dto);
		System.out.println(coursecode);
		return "redirect:/subject_select_all.do?coursecode="+coursecode;
	}
	
	@RequestMapping(value="/add.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String add() {
		return "addSubject";
	}
	
	
	@SuppressWarnings("null")
	@RequestMapping(value="/submit.do", method=RequestMethod.GET)
	public String submit(HttpServletRequest req,Model model) {
		String re=req.getParameter("subjectcode");
		System.out.println(re);
		String[] rere=re.split(",");
		System.out.println(rere);
		
		List<Subject_DTO> add = new ArrayList<Subject_DTO>();
		for(int i=0; i<rere.length; i++) {
			System.out.println("@#$@#$@#$@#$@#$@#$@#$@##$@#$@#$");
			add.add(subject_IService.subjectname(rere[i]));
			System.out.println(rere[i]);
		}
		model.addAttribute("add",add);
		
//		logger.info("Controller multidel{}",Arrays.toString(req));
//		Map<String, String[]> map=new HashMap<String,String[]>();
//		map.put("seq_", chkVal);
//		String str=subject_IService.subjectname(map);
//		model.addAttribute("add",str);
		
//		String[] arrayParam = request.getParameterValues("subjectcode");
//		logger.info("Controller submit{}");
//		for (int i = 0; i < arrayParam.length; i++) {
//			System.out.println(arrayParam[i]);
//			List<Subject_DTO> list= subject_IService.subjectname(arrayParam[i]);
//		}
////		model.addAttribute("add",arrayParam);
//
//		return "ddd";
		return "ddd";
	}
	
	
}
