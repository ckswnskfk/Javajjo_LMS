package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.App_Form_DTO;
import happy.jaj.prj.dtos.Course_DTO;

public interface Absent_IService {

	// 신청서 조회
	// 자신의 신청내역 리스트 상태별로 조회(학생)
	public List<App_Form_DTO> student_absent_list(Map<String, String> map);

	// 클릭해서 각 신청을 상세조회(처리중)
	public App_Form_DTO absent_detail_no(String seq);
	
	// 클릭해서 각 신청을 상세조회(승인), 사인 이미지 조회
	public Map<String, Object> absent_detail_yes(Map<String, String> map);

	// 클릭해서 각 신청을 상세조회(반려시)
	public App_Form_DTO absent_detail_return(String seq);

	// 강사, 관리자가 자신의 과정의 학생들것만의 내역서 리스트 보기
	public List<App_Form_DTO> recipient_absent_list(Map<String, String> map);

	
	// 결석 신청
	// 결석 신청하려는 과정을 선택
	public List<Course_DTO> absent_course(String id);
	
	// 관리자를 선택
	public Admin_DTO absent_admin();
	
	// 학생이 결석 신청을 함+신청 상태 테이블 연결
	public int insert_absent_form(App_Form_DTO dto);
	
	// 강사 및 관리자가 미승인 사유를 작성+동시에 처리일 업데이트(미승인)+동시에 승인 여부 수정
	public int insert_unapprove_reason(Map<String, String> map);
	
	// 강사 및 관리자가 승인을 함+동시에 처리일 업데이트(승인)
	public int update_is_approve_Yes(Map<String, String> map);
	
}
