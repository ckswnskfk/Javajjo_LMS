package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.App_Form_DTO;
import happy.jaj.prj.dtos.Course_DTO;

public interface Absent_Interface {
	
	// 신청서 조회
	// 자신의 신청내역 리스트 상태별로 조회(학생)
	public List<App_Form_DTO> student_absent_list(Map<String, String> map);
	
	// 클릭해서 각 신청을 상세조회(처리중)
	public App_Form_DTO absent_detail_no(String seq);
	
	// 클릭해서 각 신청을 상세조회(승인)
	public App_Form_DTO absent_detail_yes(String seq);
	// 승인인 신청의 경우 사인 이미지 조회
	public String select_signature(Map<String, String> map);
	
	// 클릭해서 각 신청을 상세조회(반려시)
	public App_Form_DTO absent_detail_return(String seq);
	
	// 강사, 관리자가 자신의 과정의 학생들것만의 내역서 리스트 보기
	public List<App_Form_DTO> recipient_absent_list(Map<String, String> map);
	
	
	// 결석 신청
	// 결석 신청하려는 과정을 선택
	public List<Course_DTO> absent_course(String id);
	
	// 관리자를 선택
	public Admin_DTO absent_admin();
	
	// 학생이 결석 신청을 함
	public int insert_absent_form(App_Form_DTO dto);
	// 신청 상태 테이블 연결
	public int insert_is_approve(String id);
	
	// 강사 및 관리자가 미승인 사유를 작성
	public int insert_unapprove_reason(Map<String, String> map);
	// 동시에 처리일 업데이트(미승인)
	public int update_process_date_re(String seq);
	// 동시에 승인 여부 수정
	public int update_is_approve_Re(Map<String, String> map);
	
	// 강사 및 관리자가 승인을 함
	public int update_is_approve_Yes(Map<String, String> map);
	// 동시에 처리일 업데이트(승인)
	public int update_process_date_yes(String seq);
	
	// 사인 이미지를 이미 등록했는지 확인
	public int chk_signature(String id);
	
	// 강사 및 관리자가 사인이미지를 등록
	public int add_signature(Map<String, String> map);
}
