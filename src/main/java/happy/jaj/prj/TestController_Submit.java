package happy.jaj.prj;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Subject_DTO;
import happy.jaj.prj.dtos.Subject_Test_DTO;
import happy.jaj.prj.dtos.TestSession_DTO;
import happy.jaj.prj.model.Test_IService;

@Controller
public class TestController_Submit {
	
	private Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private Test_IService iService;
	
	@RequestMapping(value="/test_Course_Submit.do", method=RequestMethod.GET)
	public String Courselist(HttpSession session, Model model) {
		logger.info("TestController Courselist");
		Map<String, String> map = (Map<String, String>)session.getAttribute("member");
		String id = map.get("id");
		System.out.println(id);
		List<Course_DTO> dto = iService.test_courselist(id);
		model.addAttribute("dto", dto);
		return "test_Courselist_Stu";
	}
	
	@RequestMapping(value="/test_Subject_Submit.do", method=RequestMethod.GET)
	public String Subjectlist(String coursecode, String coursename, HttpSession session, Model model) {
		logger.info("Testcontroller Subjectlist");
		
		System.out.println("받아온 값 : "+coursecode);
		List<Subject_DTO> list = iService.test_subject(coursecode);
		for(Subject_DTO dto:list) {			
			System.out.println(dto);
		}
		
		model.addAttribute("list", list);
		TestSession_DTO testsession = new TestSession_DTO();
		testsession.setCoursename(coursename);

		session.setAttribute("testsession", testsession);
		
		return "test_SubjectList_Stu";
	}
	
	@RequestMapping(value="/test_List_Submit.do", method=RequestMethod.GET)
	public String Testlist(HttpSession session, TestSession_DTO dto, Model model) {
		logger.info("TestController Testlist");
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		testsession.setSubjectcode(dto.getSubjectcode());
		testsession.setSubjectname(dto.getSubjectname());
		testsession.setSubjecttype(dto.getSubjecttype());
		testsession.setExamtype(dto.getExamtype());
		
		Subject_Test_DTO STdto = iService.se_testselect(dto.getSubjectcode());
		model.addAttribute("dto", STdto);
		System.out.println("TSdto");
		
		if(STdto != null) {
			testsession.setTestname(STdto.getTestname());
			testsession.setTestday(STdto.getTestday());
			testsession.setTestcode(STdto.getTestcode());
		}
		session.setAttribute("testsession", testsession);
		
		return "test_List_Stu";
	}
	
	

}
