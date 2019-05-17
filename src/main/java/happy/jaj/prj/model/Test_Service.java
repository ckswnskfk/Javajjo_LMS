package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happy.jaj.prj.dtos.Answer_Des_DTO;
import happy.jaj.prj.dtos.Answer_Sel_DTO;
import happy.jaj.prj.dtos.ContentSelect_DTO;
import happy.jaj.prj.dtos.Exam_Des_DTO;
import happy.jaj.prj.dtos.Exam_Sel_DTO;
import happy.jaj.prj.dtos.Score_DTO;
import happy.jaj.prj.dtos.Subject_Test_DTO;
import happy.jaj.prj.dtos.Test_DTO;
import happy.jaj.prj.dtos.Test_Exam_DTO;

@Service
public class Test_Service implements Test_IService {
	
	@Autowired
	private Test_Interface test_Interface;
	
	@Override
	public boolean test_Transaction(Test_DTO Tdto, Subject_Test_DTO Sdto) {
		int n = test_Interface.test_insert(Tdto);
		System.out.println("과제 등록 성공 ? "+(n>0?true:false));
		Sdto.setTestcode(Tdto.getTestcode());
		return test_Interface.se_insert(Sdto);
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
		return test_Interface.test_select(map);
	}

	@Override
	public Subject_Test_DTO se_select(Map<String, String> map) {
		return test_Interface.se_select(map);
	}

	@Override
	public Subject_Test_DTO se_testselect(String seq) {
		return test_Interface.se_testselect(seq);
	}

	@Override
	public boolean examdes_insert(Exam_Des_DTO dto) {
		return test_Interface.examdes_insert(dto);
	}
	
	@Override
	public boolean te_insert(Test_Exam_DTO dto) {
		return test_Interface.te_insert(dto);
	}

	@Override
	public boolean examsel_Transaction(Exam_Sel_DTO Edto, ContentSelect_DTO Cdto) {	
		int n = test_Interface.examsel_insert(Edto);
		System.out.println("선택형 문제 등록 성공? "+(n>0? true:false));
		Cdto.setExamcode(Edto.getExamcode());
		return test_Interface.content_insert(Cdto);
	}

	@Override
	public boolean examsel_Modify(Exam_Sel_DTO dto, Map<String, String> map) {
		boolean isc1 = test_Interface.examsel_modify(dto);
		boolean isc2 = test_Interface.content_modify(map);
		return isc1==true && isc2==true ? true:false;
	}
	
	@Override
	public boolean examdes_modify(Exam_Des_DTO dto) {
		return test_Interface.examdes_modify(dto);
	}

	@Override
	public boolean te_modify(Test_Exam_DTO dto) {		
		return test_Interface.te_modify(dto);
	}

	@Override
	public int te_selectsum(String seq) {
		return test_Interface.te_selectsum(seq);
	}

	@Override
	public Exam_Des_DTO te_select(Map<String, String> map) {
		return test_Interface.te_select(map);
	}

	@Override
	public Exam_Sel_DTO te_testselect(Map<String, String> map) {
		return (Exam_Sel_DTO)test_Interface.te_testselect(map);
	}
	
	@Override
	public List<Exam_Des_DTO> te_selectlist(String seq){
		return test_Interface.te_selectlist(seq);
	}
	
	@Override
	public List<Exam_Sel_DTO> te_testselectlist(String seq){
		return test_Interface.te_testselectlist(seq);
	}

	@Override
	public List<ContentSelect_DTO> content_select(String seq) {
		return test_Interface.content_select(seq);
	}

//	@Override
//	public boolean content_modify(Map<String, String> map) {
//		
//		return test_Interface.content_modify(map);
//	}

	@Override
	public boolean answerd_insert(Answer_Des_DTO dto) {
		
		return test_Interface.answerd_insert(dto);
	}

	@Override
	public boolean answerd_modify(Answer_Des_DTO dto) {
		
		return test_Interface.answerd_modify(dto);
	}

	@Override
	public  Answer_Des_DTO answerd_select(Map<String, String> map) {
		
		return test_Interface.answerd_select(map);
	}

	@Override
	public boolean answers_insert(Answer_Sel_DTO dto) {
		
		return test_Interface.answers_insert(dto);
	}

	@Override
	public boolean answers_modify(Answer_Sel_DTO dto) {
		
		return test_Interface.answers_modify(dto);
	}

	@Override
	public Answer_Sel_DTO answers_select(Map<String, String> map) {
		
		return test_Interface.answers_select(map);
	}

	@Override
	public boolean score_insertd(Score_DTO dto) {
		
		return test_Interface.score_insertd(dto);
	}

	@Override
	public boolean score_inserts(Score_DTO dto) {
		
		return test_Interface.score_inserts(dto);
	}

	@Override
	public boolean score_modify(Score_DTO dto) {
		
		return test_Interface.score_modify(dto);
	}

	@Override
	public Score_DTO score_select(Map<String, String> map) {
		
		return test_Interface.score_select(map);
	}

	@Override
	public Score_DTO score_selectsum(Map<String, String> map) {
		
		return test_Interface.score_selectsum(map);
	}
	
	

}
