package happy.jaj.prj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.set.SynchronizedSortedSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtworks.qdox.model.Member;

import happy.jaj.prj.dtos.Answer_Des_DTO;
import happy.jaj.prj.dtos.Answer_Sel_DTO;
import happy.jaj.prj.dtos.ContentSelect_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Exam_Des_DTO;
import happy.jaj.prj.dtos.Exam_Sel_DTO;
import happy.jaj.prj.dtos.Subject_DTO;
import happy.jaj.prj.dtos.Subject_Test_DTO;
import happy.jaj.prj.dtos.TestSession_DTO;
import happy.jaj.prj.dtos.Test_Exam_DTO;
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
		System.out.println(dto);
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		testsession.setSubjectcode(dto.getSubjectcode());
		testsession.setSubjectname(dto.getSubjectname());
		testsession.setSubjecttype(dto.getSubjecttype());
		testsession.setExamtype(dto.getExamtype());
		
		Subject_Test_DTO STdto = iService.se_testselect(dto.getSubjectcode());
		model.addAttribute("dto", STdto);
		System.out.println("TSdto");
		

			testsession.setTestname(STdto.getTestname());
			testsession.setTestday(STdto.getTestday());
			testsession.setTestcode(STdto.getTestcode());
		
		System.out.println("■■■■■■■■■■ session : "+testsession);
		session.setAttribute("testsession", testsession);
		
		return "test_List_Stu";
	}
	
	@RequestMapping(value="/division_Stu.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String ExamDivision(HttpSession session, Model model) {
		logger.info("TestController ExamDivision");
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println(" 과제 유형 : "+testsession.getExamtype());
		if(testsession.getExamtype().equals("서술형")) {
			
			
			return "redirect:./desc_Detail.do";
		}else {
			return "redirect:./sel_Detail.do";
		}
	}
	
	// 서술형  문제 상세조회
	@SuppressWarnings("null")
	@RequestMapping(value="/desc_Detail.do", method={RequestMethod.POST, RequestMethod.GET})
	public String DescDetail(HttpSession session, Model model, String examnum, String examcode, String answer, String file, String page) {
		logger.info("TestController DescDetail");
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println("session : "+testsession);
		Map<String, String> map = new HashMap<String, String>();
		map.put("testcode", testsession.getTestcode());
		Map<String, String> member = (Map<String, String>)session.getAttribute("member");
		
		if(examnum==null) {
			examnum="1";
			Answer_Des_DTO ASdto = new Answer_Des_DTO();
			ASdto.setAnswer("");
			model.addAttribute("answer",ASdto);
		}else { // 문제 저장
			int pagenum = Integer.parseInt(page);// 0이면 이전, 1이면 다음 
			int examnumber=0;
			if(pagenum==0) {
				examnumber = Integer.parseInt(examnum)+1;
			}else {				
				examnumber = Integer.parseInt(examnum)-1; // 현재 문제 번호
			}
			Map<String, String> myanswer = new HashMap<>();
			myanswer.put("id", member.get("id"));
			myanswer.put("examnum", examnum);
			Answer_Des_DTO answerdto = iService.answerd_select(myanswer);
			System.out.println("답 : "+answerdto);
			if(answerdto!= null) { // 답등록된거 있음
				Answer_Des_DTO ADdto = new Answer_Des_DTO(member.get("id"), examcode, String.valueOf(examnumber), answer, file, "");
				boolean isc = iService.answerd_modify(ADdto);
				System.out.println("답안 수정 성공? "+isc);
			}else {
				Answer_Des_DTO ADdto = new Answer_Des_DTO(member.get("id"), examcode, String.valueOf(examnumber), answer, file, "");
				System.out.println("■■■■■■■■ dto :"+ADdto);
				boolean isc = iService.answerd_insert(ADdto);
				System.out.println("서술형 문제 답안 등록 성공 ? "+isc);
			}
			
			// 다음문제 답안 조회
			Map<String, String> answerMap = new HashMap<>();
			answerMap.put("id", member.get("id"));
			answerMap.put("examnum", examnum);
			Answer_Des_DTO ASdto = iService.answerd_select(answerMap);
//			if(ASdto==null) {
//				ASdto.setAnswer("");
//				System.out.println("결과 없다. ");
//			}
			System.out.println(ASdto);
			model.addAttribute("answer",ASdto);
		}
		System.out.println("examnum : "+examnum);
		map.put("examnum", examnum);
		Exam_Des_DTO dto = iService.te_select(map);
		model.addAttribute("dto", dto);
		
		
//		
		return "test_DetailDescription";
	}
	
	
	// 선택형 문제 상세조회
	@RequestMapping(value="/sel_Detail.do", method={RequestMethod.POST, RequestMethod.GET})
	public String SelDetail(HttpSession session, Model model, String examnum) {
		logger.info("TestController SelDetail");
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println(testsession);
		Map<String, String> map = new HashMap<String, String>();
		map.put("testcode", testsession.getTestcode());
		
		if(examnum==null) {
			examnum="1";
		}else {
			
		}
		System.out.println("examnum : "+examnum);
		map.put("examnum", examnum);
		Exam_Sel_DTO dto = iService.te_testselect(map);
		model.addAttribute("dto", dto);
		
		List<ContentSelect_DTO> list = iService.content_select(dto.getExamcode());
		
		return "test_DetailSelect";
	}
	
	
	
	

}
