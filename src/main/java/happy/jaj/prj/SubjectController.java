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
	@RequestMapping(value="/subject_select_all.do", method={RequestMethod.GET,RequestMethod.POST})
	public String subject_select_all(Model model,Course_Subject_DTO dto, String coursecode, HttpSession session,String coursename) {
		logger.info("SubjectController subject_select_all 실행");
//		System.out.println(coursecode);
//		System.out.println(coursename);
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
		model.addAttribute("coursename",coursename);
		session.setAttribute("coursecode", coursecode);
		System.out.println("******************************************"+dto);
		return "addSubject";
	}
	
	// 과정에 과목 추가
	@RequestMapping(value="/subject_add_course.do", method={RequestMethod.GET, RequestMethod.POST})
	public String subject_add_course(HttpSession session, String[] code, String[] time, String[] content, String[] startday) {
		logger.info("SubjectController subject_add_course 실행");
		System.out.println(Arrays.toString(code));
		System.out.println(Arrays.toString(time));
		System.out.println(Arrays.toString(content));
		System.out.println(Arrays.toString(startday));
		List<Course_Subject_DTO> lists = new ArrayList<Course_Subject_DTO>();
		
		for (int i = 0; i < time.length; i++) {
			Course_Subject_DTO dto 
			= new Course_Subject_DTO((String)session.getAttribute("coursecode"), code[i], time[i], content[i], startday[i]);
			lists.add(dto);
		}
		int n = subject_IService.subject_add_course(lists);
		return (n>0)?"redirect:/subject_select_all.do?coursecode="+(String)session.getAttribute("coursecode"):"chanju_index";
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
	
	
	@RequestMapping(value="/submit.do", method=RequestMethod.GET)
	public String submit(String subjectcode ,Model model) {
		String[] rere=subjectcode.split(",");
		
		List<Subject_DTO> add = new ArrayList<Subject_DTO>();
		for(int i=0; i<rere.length; i++) {
			add.add(subject_IService.subjectname(rere[i]));
			System.out.println(rere[i]);
		}
		model.addAttribute("add",add);
		return "timecon_set";
	}
	@RequestMapping(value="/cosubDel.do", method=RequestMethod.POST)
	public String course_sub_Del(String[] seq,HttpSession session) {
		logger.info("SubjectController course_sub_Del 실행");
		System.out.println(Arrays.toString(seq));
		List<Course_Subject_DTO> lists = new ArrayList<Course_Subject_DTO>();
		for (int i = 0; i < seq.length; i++) {
			Course_Subject_DTO dto 
			= new Course_Subject_DTO(seq[i]);
			lists.add(dto);
		}
		int n = subject_IService.course_sub_Del(lists);
		return (n>0)?"redirect:/subject_select_all.do?coursecode="+(String)session.getAttribute("coursecode"):"chanju_index";
	}
	@RequestMapping(value="/subDel.do", method=RequestMethod.POST)
	public String subDel(String[] subjectcode,HttpSession session) {
		logger.info("SubjectController subDel 실행");
		System.out.println(Arrays.toString(subjectcode));
		List<Subject_DTO> lists = new ArrayList<Subject_DTO>();
		for (int i = 0; i < subjectcode.length; i++) {
			Subject_DTO dto 
			= new Subject_DTO(subjectcode[i]);
			lists.add(dto);
		}
		int n = subject_IService.subDel(lists);
		return (n>0)?"redirect:/subject_select_all.do?coursecode="+(String)session.getAttribute("coursecode"):"chanju_index";
	}
	
	@RequestMapping(value="/copySelect.do", method=RequestMethod.GET)
	public String copySelect(HttpSession session,Model model,String coursename) {
		logger.info("SubjectController copySelect 실행");
		List<Subject_DTO> slist=(List<Subject_DTO>) session.getAttribute("ctt");
//		System.out.println(session);
//		System.out.println("!@#!@##%#$$#%&#%^&$%^&%$^&"+coursename);
		List<Course_DTO> list=subject_IService.copySelectCnt(coursename);
		model.addAttribute("cnt",list);
		model.addAttribute("slist",slist);
		model.addAttribute("coursecode",session);
		
		return "copySelect";
	}	
	
	@RequestMapping(value="/cntsel.do", method=RequestMethod.GET)
	public String cntsel(Course_DTO dto,HttpSession session,Model model) {
		logger.info("SubjectController cntsel 실행");
		List<Subject_DTO> list=subject_IService.cntsel(dto);
		session.setAttribute("ctt",list);
		model.addAttribute("coursename",dto.getCoursename());
		return "redirect:/copySelect.do";
	}
	@RequestMapping(value="/timeset.do", method=RequestMethod.GET)
	public String timeset(String seq,Model model) {
		logger.info("SubjectController timeset 실행");
		System.out.println("!@#!@##!@!#@!#@@#!@#@!#!@#!@!@##!@!#@"+seq);
		String[] rere=seq.split(",");
		System.out.println("!@#!@##!@!#@!#@@#!@#@!#!@#!@!@##!@!#@"+rere);
		List<Subject_DTO> tlist= new ArrayList<Subject_DTO>();
		for(int i=0; i<rere.length; i++) {
			tlist.add(subject_IService.timeset(rere[i]));
			System.out.println(rere[i]);
		}
		model.addAttribute("add",tlist);
		return "time_set";
	}
	
	@RequestMapping(value="/endset.do", method=RequestMethod.POST)
	public String endset(String[] seq,String[] startdate,HttpSession session ) {
		logger.info("SubjectController endset 실행");
			List<Subject_DTO> lists = new ArrayList<Subject_DTO>();
			for (int i = 0; i < seq.length; i++) {
				Subject_DTO dto 
				= new Subject_DTO((String)session.getAttribute("coursecode"),seq[i],startdate[i]);
				lists.add(dto);
			}
			int n = subject_IService.endset(lists);
			return (n>0)?"redirect:/subject_select_all.do?coursecode="+(String)session.getAttribute("coursecode"):"chanju_index";
		}
}
