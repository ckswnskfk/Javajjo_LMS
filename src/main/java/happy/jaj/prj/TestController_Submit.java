package happy.jaj.prj;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.set.SynchronizedSortedSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

import com.sun.mail.iap.Response;
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
	
	@Resource(name="uploadPath")
	String uploadPath;
	
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
		
		Map<String, String> map = new HashMap<>();
		map.put("subjectcode", dto.getSubjectcode());
		map.put("coursecode", testsession.getCoursecode());
		Subject_Test_DTO STdto = iService.se_testselect(map);
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
			
			
			return "redirect:./desc_Detail_First.do";
		}else {
			return "redirect:./sel_Detail.do";
		}
	}
	
	@RequestMapping(value="/desc_Detail_First.do", method={RequestMethod.POST, RequestMethod.GET})
	public String DescDetailFirst(HttpSession session, Model model, HttpServletResponse resp) {
		logger.info("TestController DescDetailFirst");
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println("session : "+testsession);
		Map<String, String> map = new HashMap<String, String>();
		map.put("testcode", testsession.getTestcode());
		Map<String, String> member = (Map<String, String>)session.getAttribute("member");
		
		String examnum="1";
		Answer_Des_DTO ASdto = new Answer_Des_DTO();
		ASdto.setAnswer("");
		model.addAttribute("answer",ASdto);
		
		int maxexamnum = iService.test_examcount(testsession.getTestcode());
		model.addAttribute("maxexamnum", maxexamnum);
		
		// 다음 답안 조회
		Map<String, String> answerMap = new HashMap<>();
		answerMap.put("id", member.get("id"));
		answerMap.put("examnum", String.valueOf(examnum));
		Answer_Des_DTO answerdto = iService.answerd_select(answerMap);
		if(answerdto!=null) {
			PrintWriter print;
			try {
				print = resp.getWriter();
				print.append("<script>");
				print.append("alert('이미 제출한 과제는 응시하지 못합니다.');location.href='./test_Course_Submit.do';");
				print.append("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 다음 페이지 문제조회
		System.out.println("--이동할 페이지 : "+examnum);
		map.put("examnum", examnum);
		Exam_Des_DTO dto = iService.te_select(map);
		if(dto==null) {
			System.out.println("널이다.");
			try {
				System.out.println("널이다2222.");
				resp.setContentType("text/html; charset=UTF-8"); 
				PrintWriter print = resp.getWriter();
				print.println("<script  type=\"text/javascript\">");
				print.println("alert('아직 과제가 등록 되어있지 않습니다.');location.href='./test_Course_Submit.do';");
				print.println("</script>");
				print.flush();
			} catch (IOException e) {
				System.out.println("널이다333333.");
				e.printStackTrace();
			}
		}else {
			System.out.println("널이다444444.");
			model.addAttribute("dto", dto);
			System.out.println("널이다555555.");
		}
		return "test_DetailDescription";
		
	}
	
//	@RequestMapping(value="/desc_Detail22.do", method=RequestMethod.POST)
//	public void DescDetail1(HttpSession session, Model model, String examnum, String examcode, String answer, String originalfilename, String page, HttpServletResponse resp, MultipartHttpServletRequest mtReq) {
//		System.out.println("여기");
//	}
	
	// 서술형  문제 상세조회
	@RequestMapping(value="/desc_Detail.do", method=RequestMethod.POST)
	public String DescDetail(HttpSession session, Model model, Answer_Des_DTO dto, HttpServletResponse resp, MultipartHttpServletRequest mtReq) throws IOException {
		logger.info("TestController DescDetail");
		//examnum == 현재 페이지
		// page == -1 : 이전페이지로 이동 
		// page == 0 : 다음 페이지로 이동
//		System.out.println(examnum+":"+examcode+":"+answer+":"+page);
		
		MultipartFile reqFilename = mtReq.getFile("originalfilename");
		String filename = reqFilename.getOriginalFilename();
		String newfilename = "";
		
		
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println("session : "+testsession);
		Map<String, String> map = new HashMap<String, String>();
		map.put("testcode", testsession.getTestcode());
		Map<String, String> member = (Map<String, String>)session.getAttribute("member");
		
		// 문제갯수조회
		int maxexamnum = iService.test_examcount(testsession.getTestcode());
		model.addAttribute("maxexamnum", maxexamnum);
		//-------------------------------------------------------------------------
		if(dto.getExamnum()==null) {
			String examnum="1";
			Answer_Des_DTO ASdto = new Answer_Des_DTO();
			ASdto.setAnswer("");
			model.addAttribute("answer",ASdto);
			
			// 다음 답안 조회
			Map<String, String> answerMap = new HashMap<>();
			answerMap.put("id", member.get("id"));
			answerMap.put("examnum", String.valueOf(examnum));
			Answer_Des_DTO answerdto = iService.answerd_select(answerMap);
			if(answerdto!=null) {
				PrintWriter print;
				try {
					print = resp.getWriter();
					print.append("<script>");
					print.append("alert('이미 제출한 과제는 응시하지 못합니다.');location.href='./test_Course_Submit.do';");
					print.append("</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			// 다음 페이지 문제조회
			System.out.println("--이동할 페이지 : "+examnum);
			map.put("examnum", examnum);
			Exam_Des_DTO EDdto = iService.te_select(map);
			if(dto==null) {
				System.out.println("널이다.");
				try {
					System.out.println("널이다2222.");
					resp.setContentType("text/html; charset=UTF-8"); 
					PrintWriter print = resp.getWriter();
					print.println("<script  type=\"text/javascript\">");
					print.println("alert('아직 과제가 등록 되어있지 않습니다.');location.href='./test_Course_Submit.do';");
					print.println("</script>");
					print.flush();
				} catch (IOException e) {
					System.out.println("널이다333333.");
					e.printStackTrace();
				}
			}else {
				System.out.println("널이다444444.");
				model.addAttribute("dto", dto);
			}
		}
		else { // 문제 저장
		//-------------------------------------------------------------------------
			int pagenum = Integer.parseInt(dto.getPage());// 0이면 이전, 1이면 다음 
			int examnumber=0; // 이동할 페이지
			if(pagenum==(-1)) {
				examnumber = Integer.parseInt(dto.getExamnum())-1;
			}else if(pagenum==0){				
				examnumber = Integer.parseInt(dto.getExamnum())+1; // 이동할 문제 번호
			}else {
				examnumber = pagenum; // 이동할 문제 
			}
			Map<String, String> myanswer = new HashMap<>();
			myanswer.put("id", member.get("id"));
			myanswer.put("examnum", dto.getExamnum());
			Answer_Des_DTO answerdto = iService.answerd_select(myanswer);
			System.out.println("답 : "+answerdto);
			System.out.println(dto);
			if(answerdto!= null) { // 답등록된거 있음
				Answer_Des_DTO ADdto = new Answer_Des_DTO(member.get("id"), dto.getExamcode(), dto.getExamnum(), dto.getAnswer(), "", "");
				if(filename.trim().equalsIgnoreCase("")) {
					if(answerdto.getOriginfile() == null || answerdto.getOriginfile() == "") {
						ADdto.setOriginfile("");
						ADdto.setNewfilename("");
					}else {
						ADdto.setOriginfile(answerdto.getOriginfile());
						ADdto.setNewfilename(answerdto.getNewfilename());
					}
				}else {
					File dir = new File(uploadPath+"\\test");
					// 폴더가 없다면 폴더를 생성
					if (!dir.exists()) {
						dir.mkdirs();
					}
					if(answerdto.getOriginfile() == null || answerdto.getOriginfile() == "") {
					}else {
						//수정시 원본파일 삭제
						File OriFile = new File(uploadPath+"\\test",answerdto.getNewfilename());
//					File OriFile = new File(uploadPath+"\\test",dto.getNewfilename());
						if(OriFile.exists()){
							OriFile.delete();
						}
					}
					System.out.println("■■■■■■■■ dto : 여까지 나오나1");
					//이름이 겹치지 않기 위해 고유한 랜덤값을 추가한 파일 이름 생성
					UUID uuid = UUID.randomUUID();
					Date form = new Date();
					SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
					String today = transFormat.format(form);
					
					newfilename = uuid.toString()+"_"+today+"_"+member.get("id")+"_"+filename;
					
					System.out.println("■■■■■■■■ dto : 여까지 나오나2");
					File target = new File(uploadPath+"\\test", newfilename);
					
					System.out.println("■■■■■■■■ dto : 여까지 나오나3");
					
					// 파일을 서버에 저장
					FileCopyUtils.copy(reqFilename.getBytes(), target);
					
					ADdto.setOriginfile(filename);
					ADdto.setNewfilename(newfilename);
					System.out.println("■■■■■■■■ dto : 여까지 나오나4");
				}
				boolean isc = iService.answerd_modify(ADdto);
				System.out.println("답안 수정 성공? "+isc);
			}else {
				System.out.println("■■■■■■■■ dto : 여까진 나옴 ");
				Answer_Des_DTO ADdto = new Answer_Des_DTO(member.get("id"), dto.getExamcode(), dto.getExamnum(), dto.getAnswer(), "", "");
				System.out.println("■■■■■■■■ dto :"+ADdto);
				if(filename.trim().equalsIgnoreCase("")) {
					ADdto.setOriginfile(filename);
					ADdto.setNewfilename(newfilename);
				}else {
					//이름이 겹치지 않기 위해 고유한 랜덤값을 추가한 파일 이름 생성
					UUID uuid = UUID.randomUUID();
					Date form = new Date();
					SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
					String today = transFormat.format(form);
					
					newfilename = uuid.toString()+"_"+today+"_"+member.get("id")+"_"+filename;
					
					File dir = new File(uploadPath+"\\test");
					File target = new File(uploadPath+"\\test", newfilename);
					
					// 폴더가 없다면 폴더를 생성
					if (!dir.exists()) {
						dir.mkdirs();
					}
					
					// 파일을 서버에 저장
					FileCopyUtils.copy(reqFilename.getBytes(), target);
					
					// 받아온 dto에는 파일 이름이 없기 때문에 직접 넣어주고 dao 실행
					ADdto.setOriginfile(filename);
					ADdto.setNewfilename(newfilename);
				}
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
			System.out.println("이동할 페이지 : "+examnumber);
			map.put("examnum", String.valueOf(examnumber));
			Exam_Des_DTO EDdto = iService.te_select(map);
			model.addAttribute("dto", EDdto);
		}

		return "test_DetailDescription";
	}
	
	// 상세조회한 신청서에서 첨부파일 다운
	@RequestMapping(value="/test_submitdownload.do", method=RequestMethod.GET)
	public ModelAndView download(String newfilename) {
		String fullPath = uploadPath+"\\test\\"+newfilename;
		File file = new File(fullPath);
		// download라는 id를 가진 Bean에 downloadFile 이라는 이름의 file을 전달
		return new ModelAndView("download","downloadFile",file);
	}
	
	
	// 선택형 문제 상세조회
	@RequestMapping(value="/sel_Detail.do", method={RequestMethod.POST, RequestMethod.GET})
	public String SelDetail(HttpSession session, Model model, String examnum, String examcode, String answer, String page, HttpServletResponse resp) {
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
			
			// 다음 답안 조회
			Map<String, String> answerMap = new HashMap<>();
			answerMap.put("id", member.get("id"));
			answerMap.put("examnum", String.valueOf(examnum));
			String answerstr = iService.answers_select(answerMap);
			if(answerstr!=null) {
				PrintWriter print;
				try {
					print = resp.getWriter();
					print.append("<script>");
					print.append("alert('이미 제출한 과제는 응시하지 못합니다.');location.href='./test_Course_Submit.do';");
					print.append("</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
			
			
			// 다음 페이지 문제조회
			System.out.println("이동할 페이지 : "+examnum);
			map.put("examnum", examnum);
			Exam_Sel_DTO dto = iService.te_testselect(map);
			if(dto==null) {
				System.out.println("1널이다.");
				PrintWriter print;
				try {
					System.out.println("1널이다2222.");
//					resp.setContentType("text/html; charset=UTF-8"); 
					print = resp.getWriter();
					print.append("<script>");
					print.append("alert('아직 과제가 등록 되어있지 않습니다.');location.href='./test_Course_Submit.do';");
					print.append("</script>");

				} catch (IOException e) {
					System.out.println("1널이다333333.");
					e.printStackTrace();
				}
			}else {
				System.out.println("널이다444444.");
				model.addAttribute("dto", dto);
				List<ContentSelect_DTO> contentlist = iService.content_select(dto.getExamcode());
				model.addAttribute("contentlist", contentlist);
			}
			
			
			
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
		List<Subject_DTO> list1 = iService.test_subject(coursecode);
		List<Subject_DTO> list = new ArrayList<>();
		for(Subject_DTO dto : list1) {
			if(dto.getexamtype().equals("서술형")) {
				list.add(dto);
			}
		}		
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
		System.out.println("session : "+testsession);
		System.out.println("받아온  dto : "+dto);
		if(dto.getSubjectcode()!=null) {
			testsession.setSubjectcode(dto.getSubjectcode());
			testsession.setSubjectname(dto.getSubjectname());
			testsession.setSubjecttype(dto.getSubjecttype());
			testsession.setExamtype(dto.getExamtype());	
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("subject", testsession.getSubjectcode());
		map.put("coursecode", testsession.getCoursecode());
		Subject_Test_DTO test = iService.se_testselect(map);
		
		testsession.setTestname(test.getTestname());
		testsession.setTestday(test.getTestday());
		testsession.setTestcode(test.getTestcode());
		
		List<Student_DTO> list = iService.test_coursestu(testsession.getCoursecode());
		int[] intlist = new int[list.size()];
		int i = 0;
		for(Student_DTO stu : list) {
			Map<String, String> map1 = new HashMap<>();
			map1.put("id", stu.getId());
			map1.put("testcode", testsession.getTestcode());
			int cnt = iService.score_allcheck(map1);
			intlist[i++] = cnt;
			System.out.println(cnt);
		}
		System.out.println(intlist[0]);System.out.println(Arrays.toString(intlist));
		model.addAttribute("list", list);
		model.addAttribute("countlist", intlist);
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
	
	@RequestMapping(value="/test_AllMark.do", method=RequestMethod.POST,
			produces="application/text; charset=UTF-8")
	@ResponseBody
	public String AllCheckMark(String id, String testcode, String maxnum) {
		logger.info("TestController AllCheckMark");
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("testcode", testcode);
		
		int allchk = iService.score_allcheck(map);
		System.out.println(allchk);
		String str =String.valueOf(allchk);
		
		return str; 
	}
	
	// 성적 테이블 체크 변경
	@RequestMapping(value="/score_chkupdate.do", method=RequestMethod.POST,
			produces="application/text; charset=UTF-8")
	@ResponseBody
	public void scoreChkUpdate(String id, String testcode) {
		logger.info("TestController scoreChkUpdate");
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("testcode", testcode);
		boolean isc = iService.score_chkupdate(map);
		System.out.println("체크 변경 성공 ? "+isc);
		
	}
	
	// 선택형 문제 자동 채점
	@RequestMapping(value="/test_Sel_Score.do", method=RequestMethod.POST)
	public String SelDetailExam(HttpSession session, Model model, Answer_Des_DTO answer) {
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
		
		// 세션정보 확인 
		Map<String, String> member = (Map<String, String>)session.getAttribute("member");
		String id = member.get("id");
		System.out.println("▶ id : "+id);
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		System.out.println("▶ session : "+testsession);
		
		
		Map<String, String> myanswer = new HashMap<>();
		myanswer.put("id", member.get("id"));
		myanswer.put("examnum", answer.getExamnum());
		String h_answer = iService.answers_select(myanswer);
		System.out.println("답 : "+h_answer);
		if(h_answer!= null) { // 답등록된거 있음
//			Answer_Des_DTO ADdto = new Answer_Des_DTO(member.get("id"), examcode, examnum, answer, file, "");
			Answer_Sel_DTO pageanswer = new Answer_Sel_DTO(member.get("id"), answer.getExamcode(), answer.getExamnum(), (answer==null)?"":answer.getAnswer());
			boolean isc = iService.answers_modify(pageanswer);
			System.out.println("답안 수정 성공? "+isc);
		}else {
			Answer_Sel_DTO pageanswer = new Answer_Sel_DTO(member.get("id"), answer.getExamcode(), answer.getExamnum(), (answer==null)?"":answer.getAnswer());
			System.out.println("■■■■■■■■ dto :"+pageanswer);
			boolean isc = iService.answers_insert(pageanswer);
			System.out.println("서술형 문제 답안 등록 성공 ? "+isc);
		}
		Map<String, String> map = new HashMap<>();
		map.put("testcode", testsession.getTestcode());
		map.put("coursecode", testsession.getCoursecode());
		List<Test_Exam_DTO> list = iService.te_testselectlist(map);
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
	public String testSubjectResult(String coursecode, String coursename, String coursecnt, HttpSession session, Model model) {
		logger.info("TestController testSubjectResult");
		
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
		
		return  "test_SubjectList_Result";
	}
	
	// 학생 과목 조회 
	@RequestMapping(value="/test_Subject_ResultStu.do",method=RequestMethod.GET)
	public String testSubjectResultStu(String coursecode, String coursename, String coursecnt, HttpSession session, Model model) {
		logger.info("TestController testSubjectResultStu");
		
		System.out.println("받아온 값 : "+coursecode);
		List<Subject_DTO> list = iService.test_subject(coursecode);
		for(Subject_DTO dto:list) {			
			System.out.println(dto);
		}
		
		model.addAttribute("list", list);
		TestSession_DTO testsession = new TestSession_DTO();
		testsession.setCoursename(coursename);
		testsession.setCoursecnt(coursecnt);

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
		
		Map<String, String> map = new HashMap<>();
		map.put("subjectcode", dto.getSubjectcode());
		map.put("coursecode", testsession.getCoursecode());
		Subject_Test_DTO STdto = iService.se_testselect(map);
		model.addAttribute("dto", STdto);
		System.out.println("TSdto");
		

			testsession.setTestname(STdto.getTestname());
			testsession.setTestday(STdto.getTestday());
			testsession.setTestcode(STdto.getTestcode());
		
		System.out.println("■■■■■■■■■■ session : "+testsession);
		session.setAttribute("testsession", testsession);
			
		return "test_List_ResultStu";
	}
	
	//과제 조회(강사)
	@RequestMapping(value="/test_Test_Result.do", method=RequestMethod.GET)
	public String testSubjectResult(TestSession_DTO dto, HttpSession session, Model model) {
		logger.info("TestController testSubjectResult");
		System.out.println(dto);
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		testsession.setSubjectcode(dto.getSubjectcode());
		testsession.setSubjectname(dto.getSubjectname());
		testsession.setSubjecttype(dto.getSubjecttype());
		testsession.setExamtype(dto.getExamtype());
		
		Map<String, String> map = new HashMap<>();
		map.put("subject", dto.getSubjectcode());
		map.put("coursecode", testsession.getCoursecode());
		Subject_Test_DTO STdto = iService.se_testselect(map);
		model.addAttribute("dto", STdto);
		System.out.println("TSdto");
		

			testsession.setTestname(STdto.getTestname());
			testsession.setTestday(STdto.getTestday());
			testsession.setTestcode(STdto.getTestcode());
		
		System.out.println("■■■■■■■■■■ session : "+testsession);
		session.setAttribute("testsession", testsession);
		
		return "test_List_Result";
	}
	
//	// 성적 조회
	@RequestMapping(value="/test_Total_Result.do", method=RequestMethod.GET)
	public String testTotalResult(HttpSession session, Model model) {
		logger.info("TestController testTotalResult");
		
		// 수강 학생 리스트 조회
		TestSession_DTO testsession = (TestSession_DTO)session.getAttribute("testsession");
		List<Student_DTO> list = iService.test_coursestu(testsession.getCoursecode());
		
		List<Score_DTO> scorelist = new ArrayList<>();
		// 학생 점수 조회 
		for(Student_DTO dto : list) {
			Map<String, String> map = new HashMap<>();
			map.put("testcode", testsession.getTestcode());
			map.put("id", dto.getId());
			Score_DTO sdto = iService.score_selectsum(map);
			if(sdto==null) {
				Score_DTO sdto1 = new Score_DTO();
				sdto1.setId(dto.getId());
				sdto1.setScore(-1);
				scorelist.add(sdto1);
			}else {
				
				scorelist.add(sdto);
			}
		}
		
		int avg = iService.test_avg(testsession.getTestcode());
		model.addAttribute("avg", avg);
		model.addAttribute("list", list);
		model.addAttribute("scorelist", scorelist);
		
		
		return "test_ScoreStudentList";
	}
	
	// 성적 조회
	@RequestMapping(value="/test_Total_ResultStu.do", method=RequestMethod.GET)
	public String testTotalResultStu(HttpSession session, Model model, HttpServletResponse resp) {
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
		if(dto.getId() == null) {
			System.out.println("값 없음");
			PrintWriter print;
			try {
				print = resp.getWriter();
				print.append("<script>");
				print.append("alert('아직 채점 전인 과제입니다. ');location.href='./test_Course_Submit.do';");
				print.append("</script>");
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		int avg = iService.test_avg(testsession.getTestcode());
		model.addAttribute("avg", avg);
		
		return "test_DetailScoreStu";
	}
	
	
	
	
	
	

}
