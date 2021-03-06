package happy.jaj.prj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.set.SynchronizedSortedSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import happy.jaj.prj.dtos.Answer_Des_DTO;
import happy.jaj.prj.dtos.Answer_Sel_DTO;
import happy.jaj.prj.dtos.ContentSelect_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Course_Subject_DTO;
import happy.jaj.prj.dtos.Exam_Des_DTO;
import happy.jaj.prj.dtos.Exam_Sel_DTO;
import happy.jaj.prj.dtos.Score_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.dtos.Subject_DTO;
import happy.jaj.prj.dtos.Subject_Test_DTO;
import happy.jaj.prj.dtos.TestSession_DTO;
import happy.jaj.prj.dtos.Test_DTO;
import happy.jaj.prj.dtos.Test_Exam_DTO;
import happy.jaj.prj.model.Member_IService;
import happy.jaj.prj.model.Test_IService;
import happy.jaj.prj.model.Test_Service;

@Controller
public class TestController {

	private Logger logger = LoggerFactory.getLogger(TestController.class);

//	@Autowired
//	private Member_IService iMember;
	
	@Autowired
	private Test_IService iService;
	
//	@RequestMapping(value="/login.do", method=RequestMethod.GET)
//	public String login(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("Controller login {} ");
//		String id = req.getParameter("id");
//		String pw = req.getParameter("pw");
//
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", id);
//		map.put("pw", pw);
//		Student_DTO dto = iMember.loginMember(map);
//		if(dto != null) {
//			return "main";
//		}
//		return "dd";
//	}
	
	// 과제의 문제 삭제 
	@RequestMapping(value="/test_deleteexam.do", method=RequestMethod.POST)
	public String test_delete(String[] examcode, HttpSession session) {
		logger.info("TESTController test_delete ");
		System.out.println(Arrays.toString(examcode));
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		String testcode = testsession.getTestcode();
		for (int i = 0; i < examcode.length; i++) {
			Test_Exam_DTO dto = new Test_Exam_DTO(testcode, examcode[i], "", "", "");
			iService.test_deltestexam(dto);
		}
		return "redirect:/division.do";
	}
	
	// 과정명이 동일한 회차 조회
	@RequestMapping(value="/test_Course_Cnt.do", method=RequestMethod.GET)
	public String testCourseCnt(HttpSession session, Model model) {
		logger.info("TESTcontroller testCourseCnt");
		TestSession_DTO dto = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println(dto.getCoursename());
		List<Course_DTO> list = iService.test_coursecnt(dto.getCoursename());
		model.addAttribute("list", list);
		
		// ajax 처리시 여기서 반환을 무엇을 함?
		return "test_CourseCnt";
	}
	
	@RequestMapping(value="/test_CouresSel.do", method=RequestMethod.POST,
			produces="application/text; charset=UTF-8")
	@ResponseBody
	public void testCourseSel1(String coursecode, HttpSession session) {
		logger.info("TESTController testCourseSel1");
		System.out.println(coursecode);
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		boolean isc = iService.test_deltestall(testsession.getTestcode());
		
		System.out.println("문제 삭제 성공 ? "+isc);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("subjectcode", testsession.getSubjectcode());
		map.put("coursecode", coursecode);
		List<Test_Exam_DTO> list = iService.test_coursecopy(map); //examcode, testcode
		for(Test_Exam_DTO dto : list) {
			Test_Exam_DTO TEdto = new Test_Exam_DTO(testsession.getTestcode(), dto.getExamcode(), dto.getAllot(), dto.getExamnum(), "");
			System.out.println("◆◆◆◆◆◆◆◆ "+TEdto);
			boolean isc1 = iService.te_insert(TEdto);
			System.out.println("복사한 문제 과제에 문제 연결 성공 ?"+isc1);
		}
		

	}
	// 선택한 과정의 회차 과목의 문제 들고옴 
	@RequestMapping(value="/test_CouresSel1.do", method=RequestMethod.GET,
			produces="application/text; charset=UTF-8")
	@ResponseBody
	public String testCourseSel(HttpSession session, String coursecode, Model model, String examcode, String allot) {
		logger.info("TESTController testCourseSel");
		JSONObject json = new JSONObject();
		JSONArray jLists = new JSONArray();
		JSONObject jList = null;
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println(coursecode);
		Map<String, String> map = new HashMap<String, String>();
		map.put("subjectcode", testsession.getSubjectcode());
		map.put("coursecode", coursecode);
		List<Test_Exam_DTO> list = iService.test_coursecopy(map);
		for(Test_Exam_DTO dto : list) {
			Test_Exam_DTO TEdto = new Test_Exam_DTO(testsession.getTestcode(), dto.getExamcode(), dto.getAllot(), dto.getExamnum(), dto.getExam());
//			TEdto.setExamcode(dto.getExamcode());
//			TEdto.setTestcode(testsession.getTestcode());
			System.out.println(TEdto);
			boolean isc = iService.te_insert(TEdto);
			System.out.println("과제에 문제등록 성공 ? "+isc);
//			System.out.println(dto);
//			jList = new JSONObject();
//			jList.put("examnum", dto.getExamnum());
//			jList.put("exam", dto.getExam());
//			jList.put("allot", dto.getAllot());
			
			jLists.add(jList);
		}
		
		String testcode = list.get(0).getTestcode();
//		model.addAttribute("list", list);
		if(testsession.getExamtype().equals("서술형")) {
			int total = iService.te_selectsum(testcode);
			 model.addAttribute("total", total);
			System.out.println("▼▼▼▼▼▼▼▼▼▼ total : "+total);
			json.put("lists",jLists);
			
			System.out.println(json);
			return json.toJSONString();
		}else {
			int total = iService.te_selectsum(testcode);
			model.addAttribute("total", total);
			System.out.println("▼▼▼▼▼▼▼▼▼▼ total : "+total);
			json.put("lists",jLists);
			return json.toJSONString();
		}
	}
	
	// // 과목유형과 과제유형동일한 문제 조회(서술형)
	@RequestMapping(value="/test_examdesclist.do", method=RequestMethod.GET)
	public String testDescList(HttpSession session, Model model) {
		logger.info("TESTController testDescList");
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		
		List<Exam_Des_DTO> list = iService.test_typedesc(testsession.getSubjecttype());
		model.addAttribute("list", list);
		
		return "test_ExamList";
	}
	
	// 과목유형과 과제유형동일한 문제 조회(선택형) 
	@RequestMapping(value="/test_examsellist.do", method=RequestMethod.GET)
	public String testSelList(HttpSession session, Model model) {
		logger.info("TESTController testSelList");
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		
		List<Exam_Sel_DTO> list = iService.test_typesel(testsession.getSubjecttype());
		model.addAttribute("list", list);
		
		return "test_ExamListSel";
	}
	
	// 과목유형 과제유형 동일한 문제 조회
	@RequestMapping(value="/test_typecopy.do", method=RequestMethod.POST)
	public String testTypeCopy(String[] examcode, HttpSession session, Model model) {
		logger.info("TESTController testTypeCopy");
		
		System.out.println(Arrays.toString(examcode)); //나옴
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		model.addAttribute("examcode",examcode);
		int examnum = iService.test_maxexamnum(testsession.getTestcode());
		model.addAttribute("examnum", examnum);

		if(testsession.getExamtype().equals("서술형")) {
			model.addAttribute("examtype", testsession.getExamtype());
			List<Exam_Des_DTO> list = new ArrayList<>();
			for(int i=0; i<examcode.length; i++) {
				Exam_Des_DTO dto = iService.test_examdesc(examcode[i]);
				list.add(dto);
			}
			model.addAttribute("list", list);
			return "test_ExamCopyDesc";
		}else {
			model.addAttribute("examtype", testsession.getExamtype());
			List<Exam_Sel_DTO> list = new ArrayList<>();
			for(int i=0; i<examcode.length; i++) {
				Exam_Sel_DTO dto = iService.test_examsel(examcode[i]);
				list.add(dto);
			}
			model.addAttribute("list", list);
			return "test_ExamCopySel";
		}
		
	}
	
	// 과목유형 과제유형 동일한 문제 조회 -> 문제등록
	@RequestMapping(value="/test_typecopyexam.do", method=RequestMethod.POST,
			produces="application/text; charset=UTF-8")
	@ResponseBody
	public void testTypeCopyExam(String[] examcode, String[] examnum, String[] allot, HttpSession session) {
		logger.info("TESTController testTypeCopyExam");
		System.out.println("문제코드 : "+Arrays.toString(examcode));
		System.out.println("번호 : "+Arrays.toString(examnum));
		System.out.println("배점 : "+Arrays.toString(allot));
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println(testsession.getTestcode());
//		String testcode =  "T00052";
		String testcode = testsession.getTestcode();
		
		for (int i = 0; i < allot.length; i++) {
			Test_Exam_DTO dto = new Test_Exam_DTO(testcode, examcode[i], allot[i], examnum[i], "");
			iService.te_insert(dto);
		}
		
	}
	
	//담당 과정 조회
	@RequestMapping(value="/test_Course_Insert.do", method=RequestMethod.GET)
	public String testCourse(Model model, HttpSession session) {
		logger.info("TestController testCourse ");
		// 세션에서 id 받아옴 
//		Map<String, String> map = (Map<String, String>)session.getAttribute("member");
//		String id = map.get("id");
//		String id = "01012345678";
		Map<String, String> map = (Map<String, String>)session.getAttribute("member");
		String id = map.get("id");
		System.out.println(id);
		Course_DTO dto = iService.test_course(id);
		model.addAttribute("dto", dto);
		return "test_Courselist";
	}
	
	//담당 과정에 해당하는 과목 조회
	@RequestMapping(value="/test_Subject_Insert.do", method=RequestMethod.POST)
	public String testSubject(HttpSession session, Model model, String coursecode, String coursename,String coursecnt) {
		logger.info("TestController testSubject");
		
		System.out.println("받아온 값 : "+coursecode);
		List<Subject_DTO> list = iService.test_subject(coursecode);
		for(Subject_DTO dto:list) {			
			System.out.println(dto);
		}
		model.addAttribute("list", list);
		TestSession_DTO testsession = new TestSession_DTO();
		testsession.setCoursename(coursename);
		testsession.setCoursecnt(coursecnt);
		testsession.setCoursecode(coursecode);

		session.setAttribute("testsession", testsession);
		return "test_SubjectList";
	}
	
	//과제 추가
	@RequestMapping(value="/test_Input.do", method=RequestMethod.GET)
	public String testInsert(TestSession_DTO dto, HttpSession session, Model model) {
//		subjectcode=S00001&subjecttype=HTML&examtype=서술형&subjectname=화면%20설계
		logger.info("TestController testInsert {}");
		// 받는 값 : testname(test), testday(subject_test)
		// subjectcode(subject-test), subjectname(subject), subjecttype(test), examtype(test)
		
//		String subjectcode= (String)session.getAttribute("subjectcode");
//		String subjectcode = dto.getSubjectcode();
		TestSession_DTO TSdto = (TestSession_DTO)session.getAttribute("testsession");
		TSdto.setSubjectcode(dto.getSubjectcode());
		TSdto.setSubjectname(dto.getSubjectname());
		TSdto.setSubjecttype(dto.getSubjecttype());
		TSdto.setExamtype(dto.getExamtype());
	
		Map<String, String> map = new HashMap<>();
		map.put("subjectcode", dto.getSubjectcode());
		map.put("coursecode", TSdto.getCoursecode());
		Subject_Test_DTO STdto = iService.se_testselect(map);
//		System.out.println("■■■■■■■■■■■■■ 출력 dto : "+dto);
		model.addAttribute("dto", STdto);
		System.out.println("TSdto");
		
		if(STdto!=null) { // 과목에 등록된 과제가 있으면 
//			session.setAttribute("testname", (dto.getTestname()));
			TSdto.setTestname(STdto.getTestname());
//			session.setAttribute("testday", (dto.getTestday()));
			TSdto.setTestday(STdto.getTestday());
			TSdto.setTestcode(STdto.getTestcode());
		}

		System.out.println("■■■■■■■■■■■■■"+STdto);
//		session.setAttribute("testcode", dto.getTestcode());
		session.setAttribute("testsession", TSdto);
		System.out.println("♠♠♠♠♠♠♠♠♠♠♠♠♠ sessin dto : "+TSdto);
		
//		Map<String, String> testmap = (Map<String, String>)session.getAttribute("tsetmap");
//		session.setAttribute(name, value);
//				testmap.put("subjecttype", subjecttype);
//		testmap.put("examtype", examtype);
//		session.setAttribute("testmap", testmap);
//		
////		map.put("coursename", coursename);
////		map.put("subjectname", subjectname);
//		req.setAttribute("map", map);
		
		
//		String testcode = td.getTestcode();
//		String testday = req.getParameter("testday");
//		
//		Subject_Test_DTO st = new Subject_Test_DTO(subjectcode, "", testday);
//		boolean isc = iService.test_Transaction(td, st);
//		System.out.println("과제 추가 성공 ?"+isc);
		return "test_Form";
	}
	
	//과목에 과제 등록 
	@RequestMapping(value="/test_Regi.do", method=RequestMethod.GET)
	public String courseSubject(Subject_Test_DTO dto, HttpSession session) {
		logger.info("TestController courseSubject");
		// 받아오는 값 : testname, testday, subjectcode
		// 세션에서 받아오는 값 : subjecttype, examtype
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		String subjecttype = testsession.getSubjecttype();
		String examtype = testsession.getExamtype();
//		String subjecttype = (String)session.getAttribute("subjecttype");
//		String examtype = (String)session.getAttribute("examtype");
		
//		String testname = req.getParameter("testname");
//		String testday = req.getParameter("testday");
//		String subjectcode = req.getParameter("subjectcode");
//		System.out.println("testname :"+testname+", testday : "+testday);
		Test_DTO tdto = new Test_DTO("", dto.getTestname(), subjecttype, examtype);
		
//		Subject_Test_DTO sdto = new Subject_Test_DTO(testsession.getSubjectcode(), "", dto.getTestday());
		Subject_Test_DTO sdto = new Subject_Test_DTO(testsession.getSubjectcode(), "", dto.getTestcode(), "", dto.getTestday(), testsession.getCoursecode());
		boolean isc = iService.test_Transaction(tdto, sdto);
		System.out.println("과제 추가 성공 ?"+isc);
		
//		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
	
//		session.setAttribute("testname", dto.getTestname());
//		session.setAttribute("testday", dto.getTestday());	
		testsession.setTestname(dto.getTestname());
		testsession.setTestcode(sdto.getTestcode());
		testsession.setTestday(dto.getTestday());
		session.setAttribute("testsession", testsession);
		
		String testcode = sdto.getTestcode();
		System.out.println("■■■■■■■■■ testcode : "+testcode);
		
//		session.setAttribute("testcode", testcode);
		
		return "redirect:./division.do";
	}
	
	//서술형 선택형 구분 
	@RequestMapping(value="/division.do", method=RequestMethod.GET)
	public String division(String testcode, HttpSession session, Model model) {
		logger.info("TESTController division");
		// 받은 값 :  testcode
		
		String testcode1 = "";
		
//		String st1 = (String)session.getAttribute("testcode");
//		System.out.println("■■■■■■■■■■ session의 testcode : "+dto.getTestcode());
		TestSession_DTO dto = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println("session : "+dto);
		
		if(dto.getTestcode()==null) {
//			testcode1 = (String)req.getAttribute("testcode");
			testcode1 = testcode;
			System.out.println("■■■■■■■■■■■ 화면에서 받은 testcode : "+testcode);
		}else {
//			TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
			testcode1 = dto.getTestcode();
		}
		
//		String examtype = (String)session.getAttribute("examtype");
		
		
		System.out.println("■■■■■■■■■■■■ testcode : "+testcode1+", examtype : "+dto.getExamtype());
		
//		String testname = (String)session.getAttribute("testname");
//		String testday = (String)session.getAttribute("testday");
		
		System.out.println("★★★★★★ testname : "+dto.getTestname()+", testday : "+dto.getTestday());
		
		if(dto.getExamtype().equals("서술형")) {
			Map<String, String> map = new HashMap<>();
			map.put("coursecode", dto.getCoursecode());
			map.put("subjectcode", dto.getSubjectcode());
			int seq = iService.test_seqselect(map);
			
			Map<String, String> map1 = new HashMap<>();
			map1.put("seq", String.valueOf(seq));
			map1.put("testcode", testcode1);
			map1.put("coursecode", dto.getCoursecode());
			List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)iService.test_testdesclist(map1);
			model.addAttribute("dto", list);
			
			int total = iService.te_selectsum(testcode1);
			model.addAttribute("total", total);
			System.out.println("▼▼▼▼▼▼▼▼▼▼ total : "+total);
			
			return "test_DescriptionListForm";
		}else {
			Map<String, String> map = new HashMap<>();
			map.put("testcode", testcode1);
			map.put("coursecode", dto.getCoursecode());
			List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)iService.te_testselectlist(map);
			
			model.addAttribute("dto", list);
			
			int total = iService.te_selectsum(testcode1);
	
			model.addAttribute("total", total);
			System.out.println("▼▼▼▼▼▼▼▼▼▼ total : "+total);
			
			return "test_SelectListForm";
		}
	}
	
	// 서술형 문제 등록 폼 이동
	@RequestMapping(value="/desc_ExamForm.do", method=RequestMethod.GET)
	public String moveDescExamInsertForm(HttpSession session, Model model) {
		logger.info("TEST Controller moveExamInsertForm");
		// 받는 값 : testcode
		TestSession_DTO dto = (TestSession_DTO)session.getAttribute("testsession");
		int examnum = iService.test_maxexamnum(dto.getTestcode());
		System.out.println("■■■■■■■■■ examnum : "+examnum);
		model.addAttribute("examnum", examnum);
		
		return "test_DescriptionExam";
	}
	
	//  서술형 문제 등록
	@RequestMapping(value="/desc_ExamInput.do", method=RequestMethod.POST)
	public String desc_ExamInput(Exam_Des_DTO dto, HttpSession session) {
		logger.info("TESTController desc_ExamInput{}");
		// 받은 값 : exam, explanation, standard, c_answer, examnum, allot
		
//		String exam = req.getParameter("exam");
//		String explanation = req.getParameter("explanation");
//		String standard = req.getParameter("standard");
//		String c_answer = req.getParameter("c_answer");
//		System.out.println("■■■■■■■■■exam : "+exam+", explanation : "+explanation+", c_answer : "+c_answer);
		Exam_Des_DTO des = new Exam_Des_DTO(dto.getExam(), "", dto.getExplanation(), dto.getStandard(), dto.getC_answer());
		
//		String testcode = (String)session.getAttribute("testcode");
		TestSession_DTO testsession =  (TestSession_DTO)session.getAttribute("testsession");
		
//		String examnum = req.getParameter("examnum");
//		String allot = req.getParameter("allot");
		
		Test_Exam_DTO TEdto = new Test_Exam_DTO(testsession.getTestcode(), "", dto.getAllot(), dto.getExamnum(), "");
		boolean isc = iService.examdes_insert(des,TEdto);	
		System.out.println("서술문제등록 성공 ? "+isc);
		return "redirect:/moveInsertDesc.do";	
	}
	
	//  서술형문제등록 후 이동
	@RequestMapping(value="/moveInsertDesc.do", method=RequestMethod.GET)
	public String moveInserDesc(HttpSession session, Model model) {
		logger.info("TESTController moveInserDesc");
//		String testcode = (String)session.getAttribute("testcode");
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		List<Test_Exam_DTO> dto = (List<Test_Exam_DTO>)iService.te_selectlist(testsession.getTestcode());
		System.out.println("testcode : "+testsession.getTestcode());
		model.addAttribute("dto", dto);
		
		if(testsession.getExamtype().equals("서술형")) {
			List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)iService.te_selectlist(testsession.getTestcode());
			model.addAttribute("dto", list);
			
			int total = iService.te_selectsum(testsession.getTestcode());
			model.addAttribute("total", total);
			System.out.println("▼▼▼▼▼▼▼▼▼▼ total : "+total);
//			
			return "test_DescriptionListForm";
		}else {
			Map<String, String> map = new HashMap<>();
			map.put("testcode", testsession.getTestcode());
			map.put("coursecode", testsession.getCoursecode());
			List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)iService.te_testselectlist(map);
//			System.out.println("▼▼▼▼▼▼▼▼▼▼ dto : "+list);
			model.addAttribute("dto", list);
			
			int total = iService.te_selectsum(testsession.getTestcode());
			model.addAttribute("total", total);
			
			return "test_SelectListForm";
		}
//		return "";
	}
	
	// 선택형 문제 등록 폼 이동
	@RequestMapping(value="/sel_ExamForm.do", method=RequestMethod.GET)
	public String moveSelExamInsertForm(Model model, HttpSession session) {
		logger.info("TESTController moveSelExamInsertForm");
		
//		String testcode = (String)session.getAttribute("testcode");
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		int examnum = iService.test_maxexamnum(testsession.getTestcode());
		System.out.println("■■■■■■■■■ examnum : "+examnum);
		model.addAttribute("examnum", examnum);
				
		return "test_SelectExam";
	}
	
	// 선택형 문제 등록 
	@RequestMapping(value="/sel_ExamInput.do", method=RequestMethod.POST)
	public String examsel_Transaction(HttpSession session, String examnum, String exam, String[] contentnum, String[] examcontent, String allot, String c_answer) {
		System.out.println(examnum+":"+exam+":"+allot+":"+c_answer);
		System.out.println(Arrays.toString(contentnum));
		System.out.println(Arrays.toString(examcontent));
		
		
		logger.info("TESTController examsel_Transaction{}");
//		String exam = req.getParameter("exam");
//		String c_answer = req.getParameter("c_answer");
		Exam_Sel_DTO ESdto = new Exam_Sel_DTO(exam, "", c_answer, "", "");
//		String[] contentnum = req.getParameterValues("contentnum");	
//		String[] examcontent = req.getParameterValues("examcontent");
		
//		String testcode = (String)session.getAttribute("testcode");
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		
		List<ContentSelect_DTO> list = new ArrayList<ContentSelect_DTO>();
		for(int i=0; i<contentnum.length;i++) {
			ContentSelect_DTO dto = new ContentSelect_DTO("", contentnum[i], examcontent[i]);
			System.out.println(dto);
			list.add(dto);
			System.out.println("▶▶▶▶▶▶▶ddddddddddddd"+dto);
		}
		System.out.println("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶"+list);
		
		Test_Exam_DTO TEdto = new Test_Exam_DTO(testsession.getTestcode(), "", allot, examnum, "");
		System.out.println(TEdto);
//		ContentSelect_DTO CSdto = new ContentSelect_DTO("", examnum, examcontent);
		boolean isc = iService.examsel_Transaction(ESdto, list, TEdto);	
		return "redirect:./moveInsertSel.do";
	}
	
	// 선택형 문제등록 후 폼이동
		@RequestMapping(value="/moveInsertSel.do", method=RequestMethod.GET)
		public String moveInserSel(HttpSession session, Model model) {
			logger.info("TESTController moveInserSel");
			
			TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
			Map<String, String> map = new HashMap<>();
			map.put("testcode", testsession.getTestcode());
			map.put("coursecode", testsession.getCoursecode());
			List<Test_Exam_DTO> dto = (List<Test_Exam_DTO>)iService.te_testselectlist(map);
			model.addAttribute("dto", dto);
			
			int total = iService.te_selectsum(testsession.getTestcode());
			model.addAttribute("total", total);
			System.out.println("▼▼▼▼▼▼▼▼▼▼ total : "+total);
			
			return "test_SelectListForm";
		}
	
	
	
	//과제에 문제 등록 
	@RequestMapping(value="/test_Exam.do", method=RequestMethod.GET)
	public String te_Insert(Test_Exam_DTO dto) {
		logger.info("TESTController te_Insert{}");
		// 받는 값 : testcode, examcode, allot, examnum
		
		
//		String testcode = req.getParameter("testcode");
//		String examcode = req.getParameter("examcode");
//		String allot = req.getParameter("allot");
//		String examnum = req.getParameter("examnum");
//		System.out.println("testcode : "+testcode+", examcode : "+examcode+", allot : "+allot+", examnum : "+examnum);
//		Test_Exam_DTO TEdto = new Test_Exam_DTO(dto.getTestcode(), dto.getExamcode(), dto.getAllot(), dto.getExamnum());
		Test_Exam_DTO TEdto = new Test_Exam_DTO(dto.getTestcode(),dto.getExamcode(), dto.getAllot(), dto.getExamnum(), "");
		boolean isc = iService.te_insert(TEdto);
		
		return "te_Insert";
	}
	
	// 서술형문제 수정 폼이동
	@RequestMapping(value="/desc_Exam_ModifyForm.do",method=RequestMethod.GET)
	public String moveDescExamModify(String examnum, HttpSession session, Model model) {
		logger.info("TESTController moveDescExamModify");
		// 받은 값 : examcode
		
//		String examcode = req.getParameter("examcode");
//		String testcode = (String)session.getAttribute("testcode");
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		Map<String, String> map = new HashMap<String, String>();
		map.put("examnum", examnum);
		map.put("testcode", testsession.getTestcode());
//		System.out.println("■■■■■■■■■■■■examcode : "+dto.getExamcode()+", testcode : "+testcode);
		
		Exam_Des_DTO dto = iService.te_select(map);
		System.out.println(dto);
		model.addAttribute("dto", dto);
		
		return "test_DescExamModify";
	}
	
	// 선택형문제 수정 폼이동
	@RequestMapping(value="/sel_Exam_ModifyForm.do", method=RequestMethod.GET)
	public String moveSelExamModify(String examcode, HttpSession session, Model model, String examnum) {
		logger.info("TESTController moveSelExamModify");
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		Map<String, String> map = new HashMap<String, String>();
		map.put("examnum", examnum);
		map.put("testcode", testsession.getTestcode());
		Exam_Sel_DTO dto = iService.te_testselect(map);
		System.out.println(dto);
		model.addAttribute("dto", dto);
		
		List<ContentSelect_DTO> Clist = iService.content_select(examcode);
//		System.out.println(Clist);
		for(ContentSelect_DTO dto1 : Clist) {
			System.out.println("●●●●●●●●●●●●●●●●●● dto : "+dto1);
		}
		model.addAttribute("Clist", Clist);
		
		return "test_SelExamModify";
	}
	
	// 과제에 등록된 문제수정
	@RequestMapping(value="/test_ExamModify.do", method=RequestMethod.GET)
	public String te_modify(Test_Exam_DTO dto, HttpSession session, Model model) {
		logger.info("TESTController te_modify{}");
		// 받아오는 값 : allot, examnum, examcode
		
//		String allot = req.getParameter("allot");
//		String examnum = req.getParameter("examnum");
//		String examcode = req.getParameter("examcode");
//		System.out.println("allot : "+allot+", examnum : "+examnum+", examcode : "+examcode);
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		Test_Exam_DTO TEdto = new Test_Exam_DTO("", dto.getExamcode(), dto.getAllot(), dto.getExamnum(), "");
		System.out.println(TEdto);
		boolean isc = iService.te_modify(TEdto);
		System.out.println("결과 ? "+isc);
		
//		String testcode =  (String)session.getAttribute("testcode");
		model.addAttribute("testcode", testsession.getTestcode());
		
		if(testsession.getExamtype().equals("서술형")) {
			
			List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)iService.te_selectlist(testsession.getTestcode());
			model.addAttribute("dto", list);
			
			int total = iService.te_selectsum(testsession.getTestcode());
			model.addAttribute("total", total);
			System.out.println("▼▼▼▼▼▼▼▼▼▼ total : "+total);
			
			return "test_DescriptionListForm";
		}else {
			Map<String, String> map = new HashMap<>();
			map.put("testcode", testsession.getTestcode());
			map.put("coursecode", testsession.getCoursecode());
			List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)iService.te_testselectlist(map);
			model.addAttribute("dto", list);
			
			int total = iService.te_selectsum(testsession.getTestcode());
			model.addAttribute("total", total);
			System.out.println("▼▼▼▼▼▼▼▼▼▼ total : "+total);
			
			return "test_SelectListForm";
		}
//		
//		int total = iService.te_selectsum(testsession.getTestcode());
//		model.addAttribute("total", total);
//		System.out.println("▼▼▼▼▼▼▼▼▼▼ total : "+total);
//		System.out.println("왜안되지?");
//		return "test_Courselist";
//		return "redirect:./desc_ListForm.do";
	}
	
	// 배점 총점 계산 
	@RequestMapping(value="/score_total.do", method=RequestMethod.GET)
	public String te_selectsum(String testcode) {
		logger.info("TESTController te_selectsum{}");

		int total =  iService.te_selectsum(testcode);
		
		System.out.println("작성한 배점의 총 합은 ? "+total);
		
		return "te_selectsum";
	}
	
	// 선택형 문제 수정 (문제, 문항)
	@RequestMapping(value="/sel_Content_Modify.do", method=RequestMethod.POST)
	public String examsel_Modify(String examcode, String examnum, String exam, String[] contentnum, String[] examcontent, String allot, String c_answer,HttpSession session, Model model) {
		logger.info("TESTController examsel_Modify{}");
		// 받은 값 : examnum, exam, examcode?, contentnum[], examcontent[], c_answer, allot
		
//		String exam = req.getParameter("exam");
//		String c_answer = req.getParameter("c_answer");
//		String examcode = req.getParameter("examcode");
//		System.out.println("■■■■■■■exam : "+exam+", c_answer : "+c_answer+", examcode : "+examcode);
//		Exam_Sel_DTO ESdto = new Exam_Sel_DTO(exam, examcode, c_answer);
		Exam_Sel_DTO ESdto = new Exam_Sel_DTO(exam, examcode, c_answer, examnum, allot);
		System.out.println(ESdto);
		
//		String examcontent = req.getParameter("examcontent");
//		String examnum = req.getParameter("examnum");
		
		List<ContentSelect_DTO> list = new ArrayList<ContentSelect_DTO>();
		for (int i = 0; i < examcontent.length; i++) {			
			ContentSelect_DTO dto = new ContentSelect_DTO(examcode, contentnum[i], examcontent[i]);
			System.out.println(dto);
			list.add(dto);
		}
//		System.out.println("■■■■■■■examcontent : "+examcontent+", examnum : "+examnum+", examcode : "+examcode);
		boolean isc = iService.examsel_Modify(ESdto, list);
		
		//allot, examnum, examcode
		model.addAttribute("allot", allot);
		model.addAttribute("examnum", examnum);
		model.addAttribute("examcode", examcode);
		
		return "redirect:./test_ExamModify.do";
	}
	
	// 서술형 문제 수정
	@RequestMapping(value="/desc_Exam_Modify.do", method=RequestMethod.GET)
	public String examdes_modify(Exam_Des_DTO dto, HttpSession session, Model model) {
		logger.info("TESTController desc_Exam_Modify {}");
		// examcode, exam, explanation, standard, c_answr, allot, examnum
		
//		String examcode = req.getParameter("examcode");
//		String exam = req.getParameter("exam");
//		String explanation = req.getParameter("explanation");
//		String standard = req.getParameter("standard");
//		String c_answer = req.getParameter("c_answer");
//		System.out.println("■■■■■■■■■■■■■■■■■■■■■ examcode : "+examcode+", exam : "+exam+", explanation : "+explanation+", standard : "+standard+", c_answer : "+c_answer);
		Exam_Des_DTO EDdto = new Exam_Des_DTO(dto.getExam(), dto.getExamcode(), dto.getExplanation(), dto.getStandard(), dto.getC_answer());
		System.out.println(EDdto);
		boolean isc = iService.examdes_modify(EDdto);
		System.out.println("서술형 문제 수정 성공?"+isc);

//		String testcode = (String)session.getAttribute("testcode");
//		TestSession_DTO testsession =(TestSession_DTO)session.getAttribute("testsession");
//		List<Test_Exam_DTO> dto = (List<Test_Exam_DTO>)iService.te_selectlist(testcode);
//		System.out.println("■■■■■■■■■■■■■"+dto);
//		req.setAttribute("dto", dto);
		
		model.addAttribute("examcode", dto.getExamcode());
//		String allot = req.getParameter("allot");
		model.addAttribute("allot", dto.getAllot());
//		String examnum= req.getParameter("examnum");
		model.addAttribute("examnum", dto.getExamnum());
//		System.out.println("★★★★★★★★★ allot : "+allot+", examnum : "+examnum);
		
//		return "redirect:/test_ExamModify.do?examnum="+dto.getExamnum()+"&examcode="+dto.getExamcode()+"&allot="+dto.getAllot();
		return "redirect:./test_ExamModify.do";
	}
	
	
	//등록된 서술형문제 조회
//	@RequestMapping(value="/desc_Detail.do", method=RequestMethod.GET)
//	public String te_select(Test_Exam_DTO dto) {
//		logger.info("TESTController te_select{}");
//		// testcode, examcode
//		
////		String testcode = req.getParameter("testcode");
////		String examcode = req.getParameter("examcode");
//		System.out.println("■■■■■■■■testcode= : "+dto.getTestcode()+", examcode : "+dto.getExamcode());
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("testcode", dto.getTestcode());
//		map.put("examcode", dto.getExamcode());
//		Exam_Des_DTO EDdto = iService.te_select(map);
//		System.out.println(EDdto);
//		return "te_select";
//	}
	
	//등록된 선택형문제 조회
//	@RequestMapping(value="/sel_Detail.do", method=RequestMethod.GET)
//	public String te_testselect(Test_Exam_DTO dto) {
//		logger.info("TESTController te_testselect{}");
//		// testcode, examcode
//		
////		String testcode = req.getParameter("testcode");
////		String examcode = req.getParameter("examcode");
//		System.out.println("testcode : "+dto.getTestcode()+", examcode : "+dto.getExamcode());
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("testcode", dto.getTestcode());
//		map.put("examcode", dto.getExamcode());
//		Exam_Sel_DTO ESdto = iService.te_testselect(map);
//		System.out.println(dto);
//		return "te_testselect";
//	}

	//과제에 해당하는 문제리스트 조회(서술형)
	@RequestMapping(value="/desc_ListForm.do",method=RequestMethod.GET)
	public String te_selectlist(String testcode) {
		logger.info("TESTController te_selectlist{}");
//		String testcode = req.getParameter("testcode");
		System.out.println("testcode : "+testcode);
		List<Test_Exam_DTO> list =  iService.te_selectlist(testcode);
		for(Test_Exam_DTO dto:list) {
			System.out.println(dto);
		}
		return "test_DescriptionListForm";
	}

	//과제에 해당하는 문제리스트 조회(선택형)
//	@RequestMapping(value="/sel_ListForm.do", method=RequestMethod.GET)
//	public String te_testselectlist(String testcode) {
//		logger.info("TESTController te_testselectlist{}");
////		String testcode = req.getParameter("testcode");
//		System.out.println("testcode : "+testcode);
//		List<Test_Exam_DTO> list = iService.te_testselectlist(testcode);
//		for(Test_Exam_DTO dto : list) {
//			System.out.println(dto);
//		}
//		return "te_testselectlist";
//	}
	
	//선택형 문제에 해당하는 문항 조회
	@RequestMapping(value="/detail_Content.do", method=RequestMethod.GET)
	public String content_select(String examcode) {
		logger.info("TESTController content_select{}");
//		String examcode = req.getParameter("examcode");
		System.out.println("examcode : "+examcode);
		List<ContentSelect_DTO> list = iService.content_select(examcode);
		for(ContentSelect_DTO dto : list) {
			System.out.println(dto);
		}
		return "content_select";
	}
	
	//---------------------------------------------------------------------------------
	//서술형 문제의 답안 등록
	@RequestMapping(value="/sel_Answer_Submit.do", method=RequestMethod.GET)
	public String answerd_insert(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTCOntroller answerd_insert{}");
		String id = req.getParameter("id");
		String examcode = req.getParameter("examcode");
//		int examnum = Integer.parseInt(req.getParameter("examnum"));
		String examnum = req.getParameter("examnum");
		String answer = req.getParameter("answer");
		String originfile = req.getParameter("originfile");
		String newfilename = req.getParameter("newfilename");
		System.out.println("id : "+id+", examcode : "+examcode+", examnum : "+examnum+", answer : "+answer+", originfile :"+originfile+", newfilename : "+newfilename );
		Answer_Des_DTO ADdto = new Answer_Des_DTO(id, examcode, examnum, answer, originfile, newfilename);
		boolean isc = iService.answerd_insert(ADdto);
		System.out.println("서술형 문제 답안 등록 성공 ?"+isc);
		return "answerd_insert";
	}
	
	//선택형 문제의 답안 등록 
	@RequestMapping(value="/desc_Answer_Submit.do", method=RequestMethod.GET)
	public String answers_insert(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController answers_insert{}");
		String id = req.getParameter("id");
		String examcode = req.getParameter("examcode");
		String examnum = req.getParameter("examnum");
		String answer = req. getParameter("answer");
		Answer_Sel_DTO ASdto = new Answer_Sel_DTO(id, examcode, examnum, answer);
		boolean isc = iService.answers_insert(ASdto);
		System.out.println("선택형 문제 답안 등록 성공 ?"+isc);
		return "answers_insert";
	}
//	
//	//과제에 해당하는 문제,답 조회(서술형)
//	@RequestMapping(value="/desc_Detail_Exam.do", method=RequestMethod.GET)
//	public String answerd_select(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("TESTController answerd_select{}");
//		String id = req.getParameter("id");
//		String examcode = req.getParameter("examcode");
//		String examnum = req.getParameter("examnum");
//		System.out.println("id : "+id+", examcode : "+examcode+", examnum : "+examnum);
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", id);
//		map.put("examcode", examcode);
//		map.put("examnum", examnum);
//		Answer_Des_DTO dto = iService.answerd_select(map);
//		System.out.println(dto);
//		return "answerd_select";
//	}
	
//	//과제에 해당하는 문제,답 조회(선택형)
//	@RequestMapping(value="/sel_Detail_Exam.do", method=RequestMethod.GET)
//	public String answers_select(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("TESTController answers_select{}");
//		String id = req.getParameter("id");
//		String examcode = req.getParameter("examcode");
//		System.out.println("id : "+id+", examcode : "+examcode);
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", id);
//		map.put("examcode", examcode);
//		
//
//		return "answers_select";
//	}
	
//	//선택형 문제 자동 점수 등록 
//	@RequestMapping(value="/test_Sel_Score.do", method=RequestMethod.GET)
//	public String score_inserts(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("TESTController score_inserts {}");
//		String id = req.getParameter("id");
//		String testcode = req.getParameter("testcode");
//		String examcode = req.getParameter("examcode");
//		System.out.println("id : "+id+", testcode : "+testcode+", examcodee : "+examcode);
//		Score_DTO sdto = new Score_DTO(id, "", testcode, examcode, 0);
//		boolean isc = iService.score_inserts(sdto);
//		System.out.println("선택형 문제 자동 점수 등록 성공?"+isc);
//		return "score_inserts";
//	}
	
//	//서술형 문제 점수 등록 
//	@RequestMapping(value="test_Desc_Score.do", method=RequestMethod.GET)
//	public String score_insertd(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("TESTController score_insertd {}");
//		String id = req.getParameter("id");
//		String testcode = req.getParameter("testcode");
//		String examcode = req.getParameter("examcode");
//		int score = Integer.parseInt(req.getParameter("score"));
//		System.out.println("id : "+id+", testcode : "+testcode+", examcode : "+examcode+", score : "+score);
//		Score_DTO sdto = new Score_DTO(id, "", testcode, examcode, score);
//		boolean isc = iService.score_insertd(sdto);
//		System.out.println("서술형 문제 점수 등록 성공 ? "+isc);
//		return "score_insertd";
//	}
//	
//	//총점 점수 조회
//	@RequestMapping(value="/test_Total_Result.do", method=RequestMethod.GET)
//	public String score_selectsum(HttpServletRequest req, HttpServletResponse resp) {
//		logger.info("TESTController score_selectsum {}");
//		String id = req.getParameter("id");
//		String testcode = req.getParameter("testcode");
//		System.out.println("id : "+id+", testcode : "+testcode);
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", id);
//		map.put("testcode", testcode);
//		Score_DTO dto = iService.score_selectsum(map);
//		System.out.println(dto);
//		return "score_selectsum";
//	}
	
	// 문제번호 수정
	@RequestMapping(value="/updateExam.do", method=RequestMethod.POST)
	public String testExamUpdate(String[] examnum, String[] exam, String[] allot, HttpSession session, Model model) {
		logger.info("TESTController testExamUpdate");
		System.out.println(Arrays.toString(examnum));
		System.out.println(Arrays.toString(exam));
		System.out.println(Arrays.toString(allot));
		for (int i = 0; i < allot.length; i++) {
			Test_Exam_DTO dto = new Test_Exam_DTO("", exam[i], allot[i], examnum[i], "");
			iService.te_modify(dto);
		}
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		if(testsession.getExamtype().equals("서술형")) {
			List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)iService.te_selectlist(testsession.getTestcode());
			model.addAttribute("dto", list);
			
			return "test_DescriptionList";
		}else {
			Map<String, String> map = new HashMap<>();
			map.put("testcode", testsession.getTestcode());
			map.put("coursecode", testsession.getCoursecode());
			List<Test_Exam_DTO> list = (List<Test_Exam_DTO>)iService.te_testselectlist(map);
			model.addAttribute("dto", list);
			return "test_SelectList";
		}
	}
	
	
	
	
}
