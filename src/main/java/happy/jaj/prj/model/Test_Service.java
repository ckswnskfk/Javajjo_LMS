package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import happy.jaj.prj.dtos.Test_DTO;
import happy.jaj.prj.dtos.Test_Exam_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;

@Service
public class Test_Service implements Test_IService {
	
	@Autowired
	private Test_Interface test_Interface;
	
	private Logger logger = LoggerFactory.getLogger(Test_Service.class);
	
	@Transactional
	@Override
	public boolean test_Transaction(Test_DTO Tdto, Subject_Test_DTO Sdto) {
		logger.info("Test_Service test_Transaction {}",Sdto);
		int n = test_Interface.test_insert(Tdto);
		System.out.println("과제 추가 성공 ? "+(n>0?true:false));
		Sdto.setTestcode(Tdto.getTestcode());
		boolean isc = test_Interface.se_insert(Sdto);
		System.out.println("과목에 과제등록 성공 ? "+isc);
		return isc;
	}

//	@Override
//	public boolean test_insert(Test_DTO dto) {
//		return test_Interface.test_insert(dto);
//	}
//
//	@Override
//	public boolean se_insert(Subject_Test_DTO dto) {
//		return test_Interface.se_insert(dto);
//	}

	@Override
	public List<Test_DTO> test_select(Map<String, String> map) {
		logger.info("Test_Service test_select {}",map);
		return test_Interface.test_select(map);
	}

	@Override
	public Subject_Test_DTO se_select(Map<String, String> map) {
		logger.info("Test_Service se_select {}",map);
		return test_Interface.se_select(map);
	}

	@Override
	public Subject_Test_DTO se_testselect(String seq) {
		logger.info("Test_Service se_testselect {}",seq);
		return test_Interface.se_testselect(seq);
	}

	@Override
	public boolean examdes_insert(Exam_Des_DTO edto, Test_Exam_DTO tdto) {
		logger.info("Test_Service examdes_insert ");
		boolean isc = test_Interface.examdes_insert(edto);
		tdto.setExamcode(edto.getExamcode());
		return test_Interface.te_insert(tdto);
	}
	
	//과제에 문제등록 
	@Override
	public boolean te_insert(Test_Exam_DTO dto) {
		logger.info("Test_Service te_insert {}",dto);
		return test_Interface.te_insert(dto);
	}
	
	// 선택형 문제 등록 + 선택형 문항 등록
	@Transactional
	@Override
	public boolean examsel_Transaction(Exam_Sel_DTO Edto, List<ContentSelect_DTO> list, Test_Exam_DTO TEdto) {	
		logger.info("Test_Service examsel_Transaction 1 {}",Edto);
		logger.info("Test_Service examsel_Transaction 2 {}",list);
		int n = test_Interface.examsel_insert(Edto);
		System.out.println("선택형 문제 등록 성공? "+(n>0? true:false));

		int cnt = 0;
		System.out.println(cnt);
		System.out.println(Edto.getExamcode());
		for(ContentSelect_DTO dto:list) {
//			System.out.println("여기");
			dto.setExamcode(Edto.getExamcode());
			test_Interface.content_insert(dto);
			cnt++;
		}
		boolean isc;
		if(list.size()==cnt) {
			TEdto.setExamcode(Edto.getExamcode());
			isc = test_Interface.te_insert(TEdto);
		}else {
			isc = false;
		}
		return isc;
	}

	//선택형 문제 수정
	@Transactional
	@Override
	public boolean examsel_Modify(Exam_Sel_DTO ESdto, List<ContentSelect_DTO> list) {
		logger.info("Test_Service examsel_Modify {}",ESdto);

		boolean isc1 = test_Interface.examsel_modify(ESdto);
		System.out.println("선택형 문제 수정 성공? "+isc1);
		int cnt =0;
		
		for(ContentSelect_DTO dto: list) {
			boolean isc2 = test_Interface.content_modify(dto);
			if(isc2 == true) {
				cnt++;
			}
		}
	
		return (cnt==list.size())? true:false;
	}
	
	//서술형 문제 수정
	@Override
	public boolean examdes_modify(Exam_Des_DTO dto) {
		logger.info("Test_Service examdes_modify {}",dto);
		boolean isc = test_Interface.examdes_modify(dto);
		System.out.println("서술형 문제 수정 성공 ?"+isc);
		return isc;
	}

	@Override
	public boolean te_modify(Test_Exam_DTO dto) {		
		logger.info("Test_Service te_modify {}",dto);
		return test_Interface.te_modify(dto);
	}

	@Override
	public int te_selectsum(String seq) {
		logger.info("Test_Service te_selectsum {}",seq);
		return test_Interface.te_selectsum(seq);
	}

	@Override
	public Exam_Des_DTO te_select(Map<String, String> map) {
		logger.info("Test_Service te_select {}",map);
		return test_Interface.te_select(map);
	}

	@Override
	public Exam_Sel_DTO te_testselect(Map<String, String> map) {
		logger.info("Test_Service te_testselect {}",map);
		return (Exam_Sel_DTO)test_Interface.te_testselect(map);
	}
	
	@Override
	public List<Test_Exam_DTO> te_selectlist(String seq){
		logger.info("Test_Service te_selectlist {}",seq);
		return test_Interface.te_selectlist(seq);
	}
	
	@Override
	public List<Test_Exam_DTO> te_testselectlist(String seq){
		logger.info("Test_Service te_testselectlist {}",seq);
		return test_Interface.te_testselectlist(seq);
	}

	//선택형 문제 문항 조회
	@Override
	public List<ContentSelect_DTO> content_select(String seq) {
		logger.info("Test_Service content_select {}",seq);
		return test_Interface.content_select(seq);
	}

//	@Override
//	public boolean content_modify(Map<String, String> map) {
//		
//		return test_Interface.content_modify(map);
//	}

	@Override
	public boolean answerd_insert(Answer_Des_DTO dto) {
		logger.info("Test_Service answerd_insert {}",dto);
		return test_Interface.answerd_insert(dto);
	}

	@Override
	public boolean answerd_modify(Answer_Des_DTO dto) {
		logger.info("Test_Service answerd_modify {}",dto);
		return test_Interface.answerd_modify(dto);
	}

	@Override
	public  Answer_Des_DTO answerd_select(Map<String, String> map) {
		logger.info("Test_Service answerd_select {}",map);
		return test_Interface.answerd_select(map);
	}

	@Override
	public boolean answers_insert(Answer_Sel_DTO dto) {
		logger.info("Test_Service answers_insert {}",dto);
		return test_Interface.answers_insert(dto);
	}

	@Override
	public boolean answers_modify(Answer_Sel_DTO dto) {
		logger.info("Test_Service answers_modify {}",dto);
		return test_Interface.answers_modify(dto);
	}

	@Override
	public String answers_select(Map<String, String> map) {
		logger.info("Test_Service answers_select {}",map);
		return test_Interface.answers_select(map);
	}

	@Override
	public boolean score_insertd(Score_DTO dto) {
		logger.info("Test_Service score_insertd {}",dto);
		return test_Interface.score_insertd(dto);
	}

	@Override
	public boolean score_inserts(Score_DTO dto) {
		logger.info("Test_Service score_inserts {}",dto);
		return test_Interface.score_inserts(dto);
	}

	@Override
	public boolean score_modify(Score_DTO dto) {
		logger.info("Test_Service score_modify {}",dto);
		return test_Interface.score_modify(dto);
	}

	@Override
	public Score_DTO score_select(Map<String, String> map) {
		logger.info("Test_Service score_select {}",map);
		return test_Interface.score_select(map);
	}

	@Override
	public Score_DTO score_selectsum(Map<String, String> map) {
		logger.info("Test_Service score_selectsum {}",map);
		return test_Interface.score_selectsum(map);
	}

	@Override
	public Course_DTO test_course(String seq) {
		 logger.info("Test_Service test_course {}",seq);
		return test_Interface.test_course(seq);
	}

	@Override
	public List<Subject_DTO> test_subject(String seq) {
		 logger.info("Test_Service test_subject {}",seq);
		return test_Interface.test_subject(seq);
	}

	@Override
	public List<Course_DTO> test_coursecnt(String seq) {
		 logger.info("Test_Service test_coursecnt {}",seq);
		return test_Interface.test_coursecnt(seq);
	}

	@Override
	public int test_maxexamnum(String testcode) {
		logger.info("Test_Service test_maxexamnum{}",testcode);
		return test_Interface.test_maxexamnum(testcode);
	}

	@Override
	public List<Test_Exam_DTO> test_coursecopy(Map<String, String> map) {
		logger.info("Test_Service test_coursecopy {}", map);
		return test_Interface.test_coursecopy(map);
	}

	@Override
	public List<Exam_Des_DTO> test_typedesc(String subjecttype) {
		logger.info("Test_Service test_typedesc{}" ,subjecttype);
		return test_Interface.test_typedesc(subjecttype);
	}

	@Override
	public List<Exam_Sel_DTO> test_typesel(String subjecttype) {
		logger.info("Test_Service test_typesel{}" ,subjecttype);
		return test_Interface.test_typesel(subjecttype);
	}

	@Override
	public Exam_Des_DTO test_examdesc(String examcode) {
		logger.info("Test_Service test_examdesc{}", examcode);
		return test_Interface.test_examdesc(examcode);
	}

	@Override
	public Exam_Sel_DTO test_examsel(String examcode) {
		logger.info("Test_Service test_examsel{}", examcode);
		return test_Interface.test_examsel(examcode);
	}

	@Override
	public boolean test_deltestexam(Test_Exam_DTO dto) {
		logger.info("Test_Service test_deltestexam {}", dto);
		return test_Interface.test_deltestexam(dto);
	}

	@Override
	public boolean test_deltestall(String testcode) {
		logger.info("Test_Service test_deltestall {}",testcode);
		return test_Interface.test_deltestall(testcode);
	}

	@Override
	public List<Course_DTO> test_courselist(String id) {
		logger.info("Test_Service test_courselist {}",id);
		return test_Interface.test_courselist(id);
	}

	@Override
	public int test_examcount(String testcode) {
		logger.info("Test_Service test_examcount {}", testcode);
		return test_Interface.test_examcount(testcode);
	}

	@Override
	public List<Student_DTO> test_coursestu(String coursecode) {
		logger.info("Test_Service test_coursestu {}", coursecode);
		return test_Interface.test_coursestu(coursecode);
	}

	@Override
	public String test_examcodeselect(Map<String, String> map) {
		logger.info("Test_Service test_examcodeselect {}",map);
		return test_Interface.test_examcodeselect(map);
	}
	
	

}
