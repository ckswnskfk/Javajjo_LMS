package happy.jaj.prj.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.App_Form_DTO;
import happy.jaj.prj.dtos.Course_DTO;

@Service
public class Absent_Service implements Absent_IService {

	private Logger logger = LoggerFactory.getLogger(Absent_Service.class);
	
	@Autowired
	private Absent_Interface absent_Interface;
	
	// 신청서 조회
	// 자신의 신청내역 리스트 상태별로 조회(학생)
	@Override
	public List<App_Form_DTO> student_absent_list(Map<String, String> map) {
		logger.info("Absent_Interface student_absent_list 실행");
		return absent_Interface.student_absent_list(map);
	}

	// 클릭해서 각 신청을 상세조회(처리중)
	@Override
	public App_Form_DTO absent_detail_no(String seq) {
		logger.info("Absent_Interface absent_detail 실행");
		return absent_Interface.absent_detail_no(seq);
	}
	
	// 클릭해서 각 신청을 상세조회(승인)
	@Override
	public Map<String, Object> absent_detail_yes(Map<String, String> map) {
		logger.info("Absent_Interface absent_detail 실행");
		String seq = map.get("seq");
		App_Form_DTO dto = absent_Interface.absent_detail_yes(seq);
		String newfilename = absent_Interface.select_signature(map);
		Map<String, Object> yesMap = new HashMap<String, Object>();
		yesMap.put("dto", dto);
		yesMap.put("newfilename", newfilename);
		return yesMap;
	}

	// 클릭해서 각 신청을 상세조회(반려시)
	@Override
	public App_Form_DTO absent_detail_return(String seq) {
		logger.info("Absent_Interface absent_detail_return 실행");
		return absent_Interface.absent_detail_return(seq);
	}

	// 강사, 관리자가 자신의 과정의 학생들것만의 내역서 리스트 보기
	@Override
	public List<App_Form_DTO> recipient_absent_list(Map<String, String> map) {
		logger.info("Absent_Interface recipient_absent_list 실행");
		return absent_Interface.recipient_absent_list(map);
	}

	
	// 결석 신청
	// 결석 신청하려는 과정을 선택
	@Override
	public Course_DTO absent_course(String id) {
		logger.info("Absent_Interface recipient_absent_list 실행");
		return absent_Interface.absent_course(id);
	}

	// 관리자를 선택
	@Override
	public Admin_DTO absent_admin() {
		logger.info("Absent_Interface absent_admin 실행");
		return absent_Interface.absent_admin();
	}

	// 학생이 결석 신청을 함+신청 상태 테이블 연결
	@Override
	public int insert_absent_form(App_Form_DTO dto) {
		logger.info("Absent_Interface insert_absent_form 실행");
		int n = absent_Interface.insert_absent_form(dto);
		n += absent_Interface.insert_is_approve(dto.getStudent_id());
		return n;
	}

	// 강사 및 관리자가 미승인 사유를 작성+동시에 승인 여부 수정
	@Override
	public int insert_unapprove_reason(Map<String, String> map) {
		logger.info("Absent_Interface insert_unapprove_reason 실행");
		Map<String, String> insertMap = new HashMap<String, String>();
		insertMap.put("seq", map.get("seq"));
		insertMap.put("unapproved_reason", map.get("unapproved_reason"));
		int n = absent_Interface.insert_unapprove_reason(insertMap);
		Map<String, String> updateMap = new HashMap<String, String>();
		updateMap.put("seq", map.get("seq"));
		updateMap.put("stm", map.get("stm"));
		n += absent_Interface.update_is_approve_Re(updateMap);
		return n;
	}

	// 강사 및 관리자가 승인을 함
	@Override
	public int update_is_approve_Yes(Map<String, String> map) {
		logger.info("Absent_Interface update_is_approve_Yes 실행");
		return absent_Interface.update_is_approve_Yes(map);
	}

}
