package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

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

public interface Test_Interface {
	
	// 수강학생조회
	public List<Student_DTO> test_coursestu(String coursecode);
	
	// 문제갯수 조회
	public int test_examcount(String testcode);
	
	//수강과정 조회
	public List<Course_DTO> test_courselist(String id);
	
	// 과제의 문제 삭제
	public boolean test_deltestexam(Test_Exam_DTO dto);
	
	// 과제에 연결되 문제 모두 삭제 
	public boolean test_deltestall(String testcode);
	
	// 과정명이 동일한 과정들 중 회차를 선택해 문제 조회
	public List<Test_Exam_DTO> test_coursecopy(Map<String, String> map);
	
	// 과목유형과 과제유형동일한 문제 조회(서술형)
	public List<Exam_Des_DTO> test_typedesc(String subjecttype);
	
	// 과목유형과 과제유형동일한 문제 조회(선택형) 
	public List<Exam_Sel_DTO> test_typesel(String subjecttype);
	
	//시험의 최대 번호 조회
	public int test_maxexamnum(String testcode);
	
	// 담당 과정 조회
	public Course_DTO test_course(String seq);
			
	// 과정에 해당하는 과목 조회
	public List<Subject_DTO> test_subject(String seq);
			
	// 과정명이 동일한 회차 조회
	public List<Course_DTO> test_coursecnt(String seq);
	
	// 과제 추가 
	public int test_insert(Test_DTO dto); 
	//과목유형이 동일한 과제 조회
	public List<Test_DTO> test_select(Map<String, String> map);
	//과목에 과제등록(연결)
	public boolean se_insert(Subject_Test_DTO dto);
	//과목의 과목유형과 과제유형이 동일한 과제 조회
	public Subject_Test_DTO se_select(Map<String, String> map);
	//과목에 해당하는 과제 조회
	public Subject_Test_DTO se_testselect(String seq);
	//문제 등록(서술형)
	public boolean examdes_insert(Exam_Des_DTO dto);
	//문제 등록(선택형) 
	public int examsel_insert(Exam_Sel_DTO dto);
	//문제 수정(선택형)
	public boolean examsel_modify(Exam_Sel_DTO dto);
	//문제 수정 (서술형)
	public boolean examdes_modify(Exam_Des_DTO dto);
	//과제에 문제등록(연결)
	public boolean te_insert(Test_Exam_DTO dto);
	//과제에 연결된 문제수정(다이나믹쿼리)
	public boolean te_modify(Test_Exam_DTO dto);
	//문제 총점 조회
	public int te_selectsum(String seq);
	//과제에 해당하는 문제 조회(서술형)
	public Exam_Des_DTO te_select(Map<String, String> map);
	//과제에 해당하는 문제 조회(선택형)
	public Exam_Sel_DTO te_testselect(Map<String, String> map);
	
	//과제에 해당하는 문제리시트 조회(서술형)
	public List<Test_Exam_DTO> te_selectlist(String seq);
	//과제에 해당하는 문제리시트 조회(선택형)
	public List<Test_Exam_DTO> te_testselectlist(String seq);
	
	//문항 등록
	public boolean content_insert(ContentSelect_DTO dto);
	//문항 수정
	public boolean content_modify(ContentSelect_DTO dto);
	//문제의 문항 조회
	public List<ContentSelect_DTO> content_select(String seq);
	//학생 서술형 답안 등록
	public boolean answerd_insert(Answer_Des_DTO dto);
	//학생 서술형 답안수정(답변경, 파일변경)
	public boolean answerd_modify(Answer_Des_DTO dto);
	//(강사)학생 서술형 답안 조회
	public  Answer_Des_DTO answerd_select(Map<String, String> map);
	//학생 선택형 답안 등록
	public boolean answers_insert(Answer_Sel_DTO dto);
	//학생 선택형 답안 등록 수정
	public boolean answers_modify(Answer_Sel_DTO dto);
	//학생 선택형 답안 등록 조회
	public String answers_select(Map<String, String> map);
	//등록_학생점수채점(서술형)
	public boolean score_insertd(Score_DTO dto);
	//등록-자동채점(선택형)
	public boolean score_inserts(Score_DTO dto);
	//학생 서술형 점수 수정
	public boolean score_modify(Score_DTO dto);
	//학생 점수 조회
	public Score_DTO score_select(Map<String, String> map);
	// 총점 조회
	public Score_DTO score_selectsum(Map<String, String> map);
	//서술형 문제 조회
	public Exam_Des_DTO test_examdesc(String examcode);
	//선택형 문제 조회
	public Exam_Sel_DTO test_examsel(String examcode);
	

}
