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

import happy.jaj.prj.dtos.Answer_Des_DTO;
import happy.jaj.prj.dtos.Answer_Sel_DTO;
import happy.jaj.prj.dtos.ContentSelect_DTO;
import happy.jaj.prj.dtos.Exam_Des_DTO;
import happy.jaj.prj.dtos.Exam_Sel_DTO;
import happy.jaj.prj.dtos.Score_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.dtos.Subject_Test_DTO;
import happy.jaj.prj.dtos.Test_DTO;
import happy.jaj.prj.dtos.Test_Exam_DTO;
import happy.jaj.prj.model.Member_IService;
import happy.jaj.prj.model.Test_IService;

@Controller
public class TestController {

	private Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private Member_IService iMember;
	
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
	
	//담당 과정 조회
	@RequestMapping(value="/test_Course_Insert.do", method=RequestMethod.GET)
	public String testCourse(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		logger.info("TestController testCourse ");
//		Map<String, String> map = (Map<String, String>)session.getAttribute("member");
//		String id = map.get("id");
//		System.out.println(id);
		return "test_Courselist";
	}
	
	//과제 등록 
	@RequestMapping(value="/test_Input.do", method=RequestMethod.GET)
	public String testInsert(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TestController testInsert {}");
		String testname = req.getParameter("testname");
		String subjecttype = req.getParameter("subjecttype");
		String examtype = req.getParameter("examtype");
//		System.out.println("testname :"+testname+", testtype : "+testtype+", examtype : "+examtype);
		Test_DTO td = new Test_DTO("", testname, subjecttype, examtype);
//		td.setTestname(testname);
//		td.setSubjecttype(subjecttype);
//		td.setExamtype(examtype);
		
		String subjectcode= req.getParameter("subjectcode");
//		String testcode = td.getTestcode();
		String testday = req.getParameter("testday");
		
		Subject_Test_DTO st = new Subject_Test_DTO(subjectcode, "", testday);
		boolean isc = iService.test_Transaction(td, st);
		System.out.println("과제 추가 성공 ?"+isc);
		return "success";
	}
	
//	@RequestMapping(value="/")
//	public String testSelect(HttpServletRequest req, HttpServletResponse resp) {
//		
//	}
	
	//  서술형 문제 등록
	@RequestMapping(value="/desc_ExamInput.do", method=RequestMethod.GET)
	public String desc_ExamInput(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController desc_ExamInput{}");
		String exam = req.getParameter("exam");
		String explanation = req.getParameter("explanation");
		String standard = req.getParameter("standard");
		String c_answer = req.getParameter("c_answer");
		System.out.println("■■■■■■■■■exam : "+exam+", explanation : "+explanation+", c_answer : "+c_answer);
		Exam_Des_DTO des = new Exam_Des_DTO(exam, "", explanation, standard, c_answer);
		boolean isc = iService.examdes_insert(des);	
		return "success";
	}
	
	// 선택형 문제 등록 
	@RequestMapping(value="/sel_ExamInput.do", method=RequestMethod.GET)
	public String examsel_Transaction(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController examsel_Transaction{}");
		String exam = req.getParameter("exam");
		String c_answer = req.getParameter("c_answer");
		Exam_Sel_DTO ESdto = new Exam_Sel_DTO(exam, "", c_answer);
		
		int examnum = Integer.parseInt(req.getParameter("examnum"));	
		String examcontent = req.getParameter("examcontent");
		
		ContentSelect_DTO CSdto = new ContentSelect_DTO("", examnum, examcontent);
		boolean isc = iService.examsel_Transaction(ESdto, CSdto);	
		return "examsel_Transaction";
	}
	
	//과제에 문제 등록 
	@RequestMapping(value="/test_Exam.do", method=RequestMethod.GET)
	public String te_Insert(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController te_Insert{}");
		String testcode = req.getParameter("testcode");
		String examcode = req.getParameter("examcode");
		String allot = req.getParameter("allot");
		String examnum = req.getParameter("examnum");
		System.out.println("testcode : "+testcode+", examcode : "+examcode+", allot : "+allot+", examnum : "+examnum);
		Test_Exam_DTO TEdto = new Test_Exam_DTO(testcode, examcode, allot, examnum);
		boolean isc = iService.te_insert(TEdto);
		
		return "te_Insert";
	}
	
	// 과제에 등록된 문제수정
	@RequestMapping(value="/test_ExamModify.do", method=RequestMethod.GET)
	public String te_modify(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController te_modify{}");
		String allot = req.getParameter("allot");
		String examnum = req.getParameter("examnum");
		String examcode = req.getParameter("examcode");
		System.out.println("allot : "+allot+", examnum : "+examnum+", examcode : "+examcode);
		Test_Exam_DTO TEdto = new Test_Exam_DTO("", examcode, allot, examnum);
		boolean isc = iService.te_modify(TEdto);
		System.out.println("결과 ? "+isc);
		return "te_modify";
	}
	
	// 배점 총점 계산 
	@RequestMapping(value="/score_total.do", method=RequestMethod.GET)
	public String te_selectsum(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController te_selectsum{}");
		String testcode = req.getParameter("testcode");
		int total =  iService.te_selectsum(testcode);
		
		System.out.println("작성한 배점의 총 합은 ? "+total);
		
		return "te_selectsum";
	}
	
	// 선택형 문제 수정 (문제, 문항)
	@RequestMapping(value="/sel_Content_Modify.do", method=RequestMethod.GET)
	public String examsel_Modify(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController examsel_Modify{}");
		String exam = req.getParameter("exam");
		String c_answer = req.getParameter("c_answer");
		String examcode = req.getParameter("examcode");
		System.out.println("■■■■■■■exam : "+exam+", c_answer : "+c_answer+", examcode : "+examcode);
		Exam_Sel_DTO ESdto = new Exam_Sel_DTO(exam, examcode, c_answer);
		
		String examcontent = req.getParameter("examcontent");
		String examnum = req.getParameter("examnum");
		Map<String, String> map = new HashMap<String, String>();
		map.put("examcontent", examcontent);
		map.put("examnum", examnum);
		map.put("examcode", examcode);
		System.out.println("■■■■■■■examcontent : "+examcontent+", examnum : "+examnum+", examcode : "+examcode);
		boolean isc = iService.examsel_Modify(ESdto, map);
		
		return "examsel_Modify";
	}
	
	// 서술형 문제 수정
	@RequestMapping(value="/desc_Exam_Modify.do", method=RequestMethod.GET)
	public String examdes_modify(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController desc_Exam_Modify {}");
		String examcode = req.getParameter("examcode");
		String exam = req.getParameter("exam");
		String explanation = req.getParameter("explanation");
		String standard = req.getParameter("standard");
		String c_answer = req.getParameter("c_answer");
		System.out.println("examcode : "+examcode+", exam : "+exam+", explanation : "+explanation+", standard : "+standard+", c_answer : "+c_answer);
		Exam_Des_DTO EDdto = new Exam_Des_DTO(exam, examcode, explanation, standard, c_answer);
		boolean isc = iService.examdes_modify(EDdto);
		
		
		return "desc_Exam_Modify";
	}
	
	
	//등록된 서술형문제 조회
	@RequestMapping(value="/desc_Detail.do", method=RequestMethod.GET)
	public String te_select(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController te_select{}");
		String testcode = req.getParameter("testcode");
		String examcode = req.getParameter("examcode");
		System.out.println("■■■■■■■■testcode= : "+testcode+", examcode : "+examcode);
		Map<String, String> map = new HashMap<String, String>();
		map.put("testcode", testcode);
		map.put("examcode", examcode);
		Exam_Des_DTO dto = iService.te_select(map);
		System.out.println(dto);
		return "te_select";
	}
	
	//등록된 선택형문제 조회
	@RequestMapping(value="/sel_Detail.do", method=RequestMethod.GET)
	public String te_testselect(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController te_testselect{}");
		String testcode = req.getParameter("testcode");
		String examcode = req.getParameter("examcode");
		System.out.println("testcode : "+testcode+", examcode : "+examcode);
		Map<String, String> map = new HashMap<String, String>();
		map.put("testcode", testcode);
		map.put("examcode", examcode);
		Exam_Sel_DTO dto = iService.te_testselect(map);
		System.out.println(dto);
		return "te_testselect";
	}

	//과제에 해당하는 문제리스트 조회(서술형)
	@RequestMapping(value="/desc_ListForm.do",method=RequestMethod.GET)
	public String te_selectlist(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController te_selectlist{}");
		String testcode = req.getParameter("testcode");
		System.out.println("testcode : "+testcode);
		List<Test_Exam_DTO> list =  iService.te_selectlist(testcode);
		for(Test_Exam_DTO dto:list) {
			System.out.println(dto);
		}
		return "te_selectlist";
	}

	//과제에 해당하는 문제리스트 조회(선택형)
	@RequestMapping(value="/sel_ListForm.do", method=RequestMethod.GET)
	public String te_testselectlist(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController te_testselectlist{}");
		String testcode = req.getParameter("testcode");
		System.out.println("testcode : "+testcode);
		List<Test_Exam_DTO> list = iService.te_testselectlist(testcode);
		for(Test_Exam_DTO dto : list) {
			System.out.println(dto);
		}
		return "te_testselectlist";
	}
	
	//선택형 문제에 해당하는 문항 조회
	@RequestMapping(value="/detail_Content.do", method=RequestMethod.GET)
	public String content_select(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController content_select{}");
		String examcode = req.getParameter("examcode");
		System.out.println("examcode : "+examcode);
		List<ContentSelect_DTO> list = iService.content_select(examcode);
		for(ContentSelect_DTO dto : list) {
			System.out.println(dto);
		}
		return "content_select";
	}
	
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
	
	//과제에 해당하는 문제,답 조회(서술형)
	@RequestMapping(value="/desc_Detail_Exam.do", method=RequestMethod.GET)
	public String answerd_select(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController answerd_select{}");
		String id = req.getParameter("id");
		String examcode = req.getParameter("examcode");
		String examnum = req.getParameter("examnum");
		System.out.println("id : "+id+", examcode : "+examcode+", examnum : "+examnum);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("examcode", examcode);
		map.put("examnum", examnum);
		Answer_Des_DTO dto = iService.answerd_select(map);
		System.out.println(dto);
		return "answerd_select";
	}
	
	//과제에 해당하는 문제,답 조회(선택형)
	@RequestMapping(value="/sel_Detail_Exam.do", method=RequestMethod.GET)
	public String answers_select(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController answers_select{}");
		String id = req.getParameter("id");
		String examcode = req.getParameter("examcode");
		System.out.println("id : "+id+", examcode : "+examcode);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("examcode", examcode);
		Answer_Sel_DTO dto = iService.answers_select(map);
		System.out.println(dto);
		return "answers_select";
	}
	
	//선택형 문제 자동 점수 등록 
	@RequestMapping(value="/test_Sel_Score.do", method=RequestMethod.GET)
	public String score_inserts(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController score_inserts {}");
		String id = req.getParameter("id");
		String testcode = req.getParameter("testcode");
		String examcode = req.getParameter("examcode");
		System.out.println("id : "+id+", testcode : "+testcode+", examcodee : "+examcode);
		Score_DTO sdto = new Score_DTO(id, "", testcode, examcode, 0);
		boolean isc = iService.score_inserts(sdto);
		System.out.println("선택형 문제 자동 점수 등록 성공?"+isc);
		return "score_inserts";
	}
	
	//서술형 문제 점수 등록 
	@RequestMapping(value="test_Desc_Score.do", method=RequestMethod.GET)
	public String score_insertd(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController score_insertd {}");
		String id = req.getParameter("id");
		String testcode = req.getParameter("testcode");
		String examcode = req.getParameter("examcode");
		int score = Integer.parseInt(req.getParameter("score"));
		System.out.println("id : "+id+", testcode : "+testcode+", examcode : "+examcode+", score : "+score);
		Score_DTO sdto = new Score_DTO(id, "", testcode, examcode, score);
		boolean isc = iService.score_insertd(sdto);
		System.out.println("서술형 문제 점수 등록 성공 ? "+isc);
		return "score_insertd";
	}
	
	//총점 점수 조회
	@RequestMapping(value="/test_Total_Result.do", method=RequestMethod.GET)
	public String score_selectsum(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("TESTController score_selectsum {}");
		String id = req.getParameter("id");
		String testcode = req.getParameter("testcode");
		System.out.println("id : "+id+", testcode : "+testcode);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("testcode", testcode);
		Score_DTO dto = iService.score_selectsum(map);
		System.out.println(dto);
		return "score_selectsum";
	}
}
