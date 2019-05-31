package happy.jaj.prj;

import java.io.InputStream;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

import com.thoughtworks.qdox.model.Member;

import happy.jaj.prj.dtos.Answer_Des_DTO;
import happy.jaj.prj.dtos.Answer_Sel_DTO;
import happy.jaj.prj.dtos.ContentSelect_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Exam_Des_DTO;
import happy.jaj.prj.dtos.Exam_Sel_DTO;
import happy.jaj.prj.dtos.Score_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.dtos.Subject_DTO;
import happy.jaj.prj.dtos.Subject_Test_DTO;
import happy.jaj.prj.dtos.TestSession_DTO;
import happy.jaj.prj.dtos.Test_Exam_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;
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
		//examnum == 현재 페이지
		// page == -1 : 이전페이지로 이동 
		// page == 0 : 다음 페이지로 이동
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println("session : "+testsession);
		Map<String, String> map = new HashMap<String, String>();
		map.put("testcode", testsession.getTestcode());
		Map<String, String> member = (Map<String, String>)session.getAttribute("member");
		
		// 문제갯수조회
		int maxexamnum = iService.test_examcount(testsession.getTestcode());
		model.addAttribute("maxexamnum", maxexamnum);
		
		if(examnum==null) {
			examnum="1";
			Answer_Des_DTO ASdto = new Answer_Des_DTO();
			ASdto.setAnswer("");
			model.addAttribute("answer",ASdto);
			
			// 다음 페이지 문제조회
			System.out.println("이동할 페이지 : "+examnum);
			map.put("examnum", examnum);
			Exam_Des_DTO dto = iService.te_select(map);
			model.addAttribute("dto", dto);
			
			
		}else { // 문제 저장
			int pagenum = Integer.parseInt(page);// 0이면 이전, 1이면 다음 
			int examnumber=0; // 이동할 페이지
			if(pagenum==(-1)) {
				examnumber = Integer.parseInt(examnum)-1;
			}else if(pagenum==0){				
				examnumber = Integer.parseInt(examnum)+1; // 이동할 문제 번호
			}else {
				examnumber = pagenum; // 이동할 문제 
			}
			Map<String, String> myanswer = new HashMap<>();
			myanswer.put("id", member.get("id"));
			myanswer.put("examnum", examnum);
			Answer_Des_DTO answerdto = iService.answerd_select(myanswer);
			System.out.println("답 : "+answerdto);
			if(answerdto!= null) { // 답등록된거 있음
				Answer_Des_DTO ADdto = new Answer_Des_DTO(member.get("id"), examcode, examnum, answer, file, "");
				boolean isc = iService.answerd_modify(ADdto);
				System.out.println("답안 수정 성공? "+isc);
			}else {
				Answer_Des_DTO ADdto = new Answer_Des_DTO(member.get("id"), examcode, examnum, answer, file, "");
				System.out.println("■■■■■■■■ dto :"+ADdto);
				boolean isc = iService.answerd_insert(ADdto);
				System.out.println("서술형 문제 답안 등록 성공 ? "+isc);
			}
			
			// 다음문제 답안 조회
			Map<String, String> answerMap = new HashMap<>();
			answerMap.put("id", member.get("id"));
			answerMap.put("examnum", String.valueOf(examnumber));
			Answer_Des_DTO ASdto = iService.answerd_select(answerMap);
//			if(ASdto==null) {
//				ASdto.setAnswer("");
//				System.out.println("결과 없다. ");
//			}
			System.out.println(ASdto);
			model.addAttribute("answer",ASdto);
			
			// 다음 페이지 문제조회
			System.out.println("이동할 페이지 : "+examnum);
			map.put("examnum", String.valueOf(examnumber));
			Exam_Des_DTO dto = iService.te_select(map);
			model.addAttribute("dto", dto);
		}
		
		
//		
		return "test_DetailDescription";
	}
	
	
	// 선택형 문제 상세조회
	@RequestMapping(value="/sel_Detail.do", method={RequestMethod.POST, RequestMethod.GET})
	public String SelDetail(HttpSession session, Model model, String examnum, String examcode, String answer, String page) {
		logger.info("TestController SelDetail");
		//examnum == 현재 페이지
		// page == -1 : 이전페이지로 이동 
		// page == 0 : 다음 페이지로 이동
		
		System.out.println("answer : "+answer);
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println("session : "+testsession);
		Map<String, String> map = new HashMap<String, String>();
		map.put("testcode", testsession.getTestcode());
		Map<String, String> member = (Map<String, String>)session.getAttribute("member");
		
		// 문제갯수조회
		int maxexamnum = iService.test_examcount(testsession.getTestcode());
		model.addAttribute("maxexamnum", maxexamnum);
		
		if(examnum==null) {
			examnum="1";
			String f_answer = "";
			model.addAttribute("answer", f_answer);
			
			// 다음 페이지 문제조회
			System.out.println("이동할 페이지 : "+examnum);
			map.put("examnum", examnum);
			Exam_Sel_DTO dto = iService.te_testselect(map);
			model.addAttribute("dto", dto);
			
			List<ContentSelect_DTO> contentlist = iService.content_select(dto.getExamcode());
			model.addAttribute("contentlist", contentlist);
			
			
		}else { // 문제 저장
			int pagenum = Integer.parseInt(page);// 0이면 이전, 1이면 다음 
			int examnumber=0; // 이동할 페이지
			if(pagenum==(-1)) {
				examnumber = Integer.parseInt(examnum)-1;
			}else if(pagenum==0){				
				examnumber = Integer.parseInt(examnum)+1; // 이동할 문제 번호
			}else {
				examnumber = pagenum; // 이동할 문제 
			}
			Map<String, String> myanswer = new HashMap<>();
			myanswer.put("id", member.get("id"));
			myanswer.put("examnum", examnum);
			String h_answer = iService.answers_select(myanswer);
			System.out.println("답 : "+h_answer);
			if(h_answer!= null) { // 답등록된거 있음
//				Answer_Des_DTO ADdto = new Answer_Des_DTO(member.get("id"), examcode, examnum, answer, file, "");
				Answer_Sel_DTO pageanswer = new Answer_Sel_DTO(member.get("id"), examcode, examnum, (answer==null)?"":answer);
				boolean isc = iService.answers_modify(pageanswer);
				System.out.println("답안 수정 성공? "+isc);
			}else {
				Answer_Sel_DTO pageanswer = new Answer_Sel_DTO(member.get("id"), examcode, examnum, (answer==null)?"":answer);
				System.out.println("■■■■■■■■ dto :"+pageanswer);
				boolean isc = iService.answers_insert(pageanswer);
				System.out.println("서술형 문제 답안 등록 성공 ? "+isc);
			}
			
			// 다음문제 답안 조회
			Map<String, String> answerMap = new HashMap<>();
			answerMap.put("id", member.get("id"));
			answerMap.put("examnum", String.valueOf(examnumber));
			String m_answer = iService.answers_select(answerMap);
//			if(ASdto==null) {
//				ASdto.setAnswer("");
//				System.out.println("결과 없다. ");
//			}
			System.out.println(m_answer);
			if(m_answer==null) {
				m_answer="";
			}
			model.addAttribute("answer",m_answer);
			
			// 다음 페이지 문제조회
			System.out.println("이동할 페이지 : "+examnum);
			map.put("examnum", String.valueOf(examnumber));
			Exam_Sel_DTO dto = iService.te_testselect(map);
			model.addAttribute("dto", dto);
			
			List<ContentSelect_DTO> contentlist = iService.content_select(dto.getExamcode());
			model.addAttribute("contentlist", contentlist);
		
		}
		return "test_DetailSelect";
	}
	
	@RequestMapping(value="test_Course_Mark.do", method=RequestMethod.GET)
	public String markCourseList(HttpSession session, Model model) {
		logger.info("TestController_Submit markCourseList");
		
		Map<String, String> member = (Map<String, String>)session.getAttribute("member");
		Course_DTO dto = iService.test_course(member.get("id"));
		model.addAttribute("dto",dto);
		
		return "test_Courselist_Mark";
	}
	
	@RequestMapping(value="/test_Subject_Mark.do", method=RequestMethod.GET)
	public String markSubjectList(String coursecode, String coursename, Model model, HttpSession session) {
		logger.info("TestController markSubjectList");
		System.out.println(coursecode+":"+coursename);
		List<Subject_DTO> list = iService.test_subject(coursecode);
		
		model.addAttribute("list", list);
		TestSession_DTO testsession = new TestSession_DTO();
		testsession.setCoursename(coursename);
		testsession.setCoursecode(coursecode);

		session.setAttribute("testsession", testsession);
		
		return "test_SubjectList_Mark";
	}
	
	@RequestMapping(value="/test_Student_List.do", method=RequestMethod.GET)
	public String markStudentList(TestSession_DTO dto, HttpSession session, Model model) {
		logger.info("TestController markStudentList");
		
		System.out.println(dto);
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		testsession.setSubjectcode(dto.getSubjectcode());
		testsession.setSubjectname(dto.getSubjectname());
		testsession.setSubjecttype(dto.getSubjecttype());
		testsession.setExamtype(dto.getExamtype());
		
		List<Student_DTO> list = iService.test_coursestu(testsession.getCoursecode());
		
		model.addAttribute("list", list);
		
		Subject_Test_DTO test = iService.se_testselect(dto.getSubjectcode());
		
		testsession.setTestname(test.getTestname());
		testsession.setTestday(test.getTestday());
		testsession.setTestcode(test.getTestcode());
		
		System.out.println("■■■■■■■■■■ session : "+testsession);
		session.setAttribute("testsession", testsession);
		
		return "test_StudentList";
	}
	
	// 서술형 문제 채점
	@RequestMapping(value="/desc_Detail_Exam.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String DescDetailExam(String id, String examcode, String score, String examnum, String page ,HttpSession session, Model model) {
		logger.info("TestController DescDetailExam");
		// examnum : 현재 자신의 페이지 
		System.out.println("★ ID : "+id);
		model.addAttribute("id",id);
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println(testsession);
		int examnumber=0; // 이동할 페이지
		
		if(examnum==null) { // 현재 페이지가 없으면 이동활 페이지 1
			examnumber = 1;
		}else { // 현재페이지가 있으면 
			int pagenum = Integer.parseInt(page);// 0이면 이전, 1이면 다음 
			if(pagenum==(-1)) {
				examnumber = Integer.parseInt(examnum)-1;
			}else if(pagenum==0){				
				examnumber = Integer.parseInt(examnum)+1; // 이동할 문제 번호
			}else {
				examnumber = pagenum; // 이동할 문제 
			}			
			// 점수 등록 
			if(score==null) {
				score = "0";
			}
			
			// 현재페이지 점수조회 
			System.out.println("◆◆◆◆◆◆◆◆◆◆현재페이지 점수 조회");
					
			Map<String, String> pagemap = new HashMap<>();
			pagemap.put("id", id);
			pagemap.put("testcode", testsession.getTestcode());
			pagemap.put("examcode", examcode);
			Score_DTO pagedto = iService.score_select(pagemap);
			if(pagedto==null) {
				Score_DTO myscore = new Score_DTO(id, "", testsession.getTestcode(), examcode, Integer.parseInt(score));
				boolean isc = iService.score_insertd(myscore);		
				System.out.println("현재 페이지 점수 등록 성공?"+isc);
			}else {
				//수정
				Score_DTO myscore = new Score_DTO(id, "", testsession.getTestcode(), examcode, Integer.parseInt(score));
				boolean isc = iService.score_modify(myscore);
				System.out.println("현재 페이지 점수 수정 성공?"+isc);
			}
			
		}
		
		
		// 학생 답안 조회
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("examnum", String.valueOf(examnumber));
		Answer_Des_DTO answer = iService.answerd_select(map); // 이동할 페이지의 학생 답안 조회
	
		model.addAttribute("answer",answer); 
		
		// 다음 페이지 문제조회
		System.out.println("이동할 페이지 : "+examnumber);
		Map<String, String> exam = new HashMap<String, String>();
		exam.put("testcode", testsession.getTestcode());
		exam.put("examnum", String.valueOf(examnumber));
		Exam_Des_DTO dto = iService.te_select(exam);
		System.out.println("문제 : "+dto);
		model.addAttribute("dto", dto);
		
		int maxexamnum = iService.test_examcount(testsession.getTestcode());
		model.addAttribute("maxexamnum", maxexamnum);
		
		// 다음 페이지 점수 조회 
		System.out.println("◆◆◆◆◆◆◆◆◆◆다음페이지 점수 조회");
		Map<String, String> scoremap = new HashMap<String, String>();
		scoremap.put("id" ,id);
		scoremap.put("testcode", testsession.getTestcode());
		Map<String, String> nextexam = new HashMap<String, String>();
		nextexam.put("testcode", testsession.getTestcode());
		nextexam.put("examnum", String.valueOf(examnumber));
		String nextexamcode = iService.test_examcodeselect(nextexam);
		scoremap.put("examcode", nextexamcode);
		Score_DTO scoredto = iService.score_select(scoremap);
		int score1;
		if(scoredto==null) {
			score1 = 0;
		}else {
			score1 = scoredto.getScore();		
		}
		System.out.println("◆◆◆◆◆◆◆◆◆◆다음페이지 점수 : "+scoredto);
		model.addAttribute("scoredto",score1);
		
		
		
		return "test_StuDescAnswer";
	}
	
	// 선택형 문제 자동 채점
	@RequestMapping(value="/test_Sel_Score.do", method=RequestMethod.GET)
	public String SelDetailExam(HttpSession session, Model model) {
		logger.info("TestController SelDetailExam");
		//INSERT INTO SCORE(ID, EXAMCHECK, TESTCODE, EXAMCODE, SCORE)
//		VALUES(#{id}, 'N', #{testcode}, #{examcode}, (SELECT 
//		(CASE WHEN C_ANSWER=ANSWER THEN ALLOT ELSE 0 END)
//		FROM ANSWERSELECT JOIN EXAMSELECT ON
//		ANSWERSELECT.EXAMCODE=EXAMSELECT.EXAMCODE
//		JOIN TEST_EXAM ON EXAMSELECT.EXAMCODE=TEST_EXAM.EXAMCODE
//		WHERE ID= #{id} AND EXAMSELECT.EXAMCODE = #{examcode}))
		// id, testcode (session), examcode
		// 시험제출 눌렀을때 testcode를 가져와 해당 문제들(examcode)조회 -> for문돌려서 insert
		
		Map<String, String> member = (Map<String, String>)session.getAttribute("member");
		String id = member.get("id");
		System.out.println("▶ id : "+id);
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println("▶ session : "+testsession);
		
		List<Test_Exam_DTO> list = iService.te_testselectlist(testsession.getTestcode());
		for(int i=0; i< list.size();i++) {
			Score_DTO dto = new Score_DTO(id, "", testsession.getTestcode(), list.get(i).getExamcode(), 0);
			boolean isc = iService.score_inserts(dto);
			System.out.println("선택형문제 점수등록 성공 ?"+isc);	
		}
//		System.out.println("▶ ㄴㄷ");
//		TestSession_DTO dto = new TestSession_DTO("", "", testsession.getSubjectcode(), testsession.getSubjectname(), testsession.getSubjecttype(), testsession.getExamtype(), "", "", "");
//		model.addAttribute("dto", dto);
//		return "redirect:/test_List_Submit.do?subjectcode="+testsession.getSubjectcode()+"&subjectname="+testsession.getSubjectname()+"&";	
//		return "redirect:/test_Subject_Submit.do?coursecode="+testsession.getCoursecode();
		return "redirect:/test_Course_Submit.do";
	}
	
	//-----------------------------------------------------------------------------------------
	
	// 담당과정 조회(성적)(강사)
	@RequestMapping(value="/test_Course_Result.do", method=RequestMethod.GET)
	public String testCourseResult(HttpSession session, Model model) {
		logger.info("TestController testCourseResult");
		Map<String, String> map = (Map<String, String>)session.getAttribute("member");
		String id = map.get("id");
		System.out.println(id);
		Course_DTO dto = iService.test_course(id);
		model.addAttribute("dto", dto);
		
		return "test_Courselist_Result";
	}
	
	// 수강과정 조회(성적)(학생)
	@RequestMapping(value="/test_Course_ResultStu.do", method=RequestMethod.GET)
	public String testCourseResultStu(HttpSession session, Model model) {
		logger.info("TestController testCourseResultStu");
		logger.info("TestController Courselist");
		Map<String, String> map = (Map<String, String>)session.getAttribute("member");
		String id = map.get("id");
		System.out.println(id);
		List<Course_DTO> dto = iService.test_courselist(id);
		model.addAttribute("dto", dto);
		
		return "test_Courselist_Result_Stu";
	}
	
	// 강사 과목 조회 
	@RequestMapping(value="/test_Subject_Result.do", method=RequestMethod.GET)
	public String testSubjectResult(String coursecode, String coursename, HttpSession session, Model model) {
		logger.info("TestController testSubjectResult");
		
		System.out.println("받아온 값 : "+coursecode);
		List<Subject_DTO> list = iService.test_subject(coursecode);
		for(Subject_DTO dto:list) {			
			System.out.println(dto);
		}
		model.addAttribute("list", list);
		TestSession_DTO testsession = new TestSession_DTO();
		testsession.setCoursename(coursename);

		session.setAttribute("testsession", testsession);
		
		return  "test_SubjectList_Result";
	}
	
	// 학생 과목 조회 
	@RequestMapping(value="/test_Subject_ResultStu.do",method=RequestMethod.GET)
	public String testSubjectResultStu(String coursecode, String coursename, HttpSession session, Model model) {
		logger.info("TestController testSubjectResultStu");
		
		System.out.println("받아온 값 : "+coursecode);
		List<Subject_DTO> list = iService.test_subject(coursecode);
		for(Subject_DTO dto:list) {			
			System.out.println(dto);
		}
		
		model.addAttribute("list", list);
		TestSession_DTO testsession = new TestSession_DTO();
		testsession.setCoursename(coursename);

		session.setAttribute("testsession", testsession);
		
		return "test_SubjectList_ResultStu";
	}
	
//	//과제 조회(강사)
//	@RequestMapping(value="/test_Test_ResultStu.do", method=RequestMethod.GET)
//	public String testTestResultStu() {
//		logger.info("TestController testTestResultStu");
//		
//		
//		return "test_List_Result";
//	}
	
	//과제 조회(학생)
	@RequestMapping(value="/test_Test_ResultStu.do" , method=RequestMethod.GET)
	public String testSubjectResultStu(TestSession_DTO dto, HttpSession session, Model model) {
		logger.info("TestController testSubjectResultStu");
		
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
			
		return "test_List_ResultStu";
	}
	
	// 성적 조회
	@RequestMapping(value="/test_Total_ResultStu.do", method=RequestMethod.GET)
	public String testTotalResultStu(HttpSession session, Model model) {
		logger.info("TestController testTotalResultStu");
		// id, testcode
		
		Map<String, String> member = (Map<String, String>)session.getAttribute("member");
		String id = member.get("id");
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		String testcode = testsession.getTestcode();
		Map<String, String> test = new HashMap<>();
		test.put("id", id);
		test.put("testcode", testcode);
		Score_DTO dto = iService.score_selectsum(test);
		int total = dto.getScore();
		model.addAttribute("total", total);
		
		return "test_DetailScoreStu";
	}
	
	
	
	
	
	

}
