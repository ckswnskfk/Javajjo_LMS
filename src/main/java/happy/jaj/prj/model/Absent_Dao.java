package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.App_Form_DTO;
import happy.jaj.prj.dtos.Course_DTO;

@Repository
public class Absent_Dao implements Absent_Interface {

	private Logger logger = LoggerFactory.getLogger(Absent_Dao.class);
	private final String NS = "happy.jaj.prj.Absent_Mapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 신청서 조회
	// 자신의 신청내역 리스트 상태별로 조회(학생)
	@Override
	public List<App_Form_DTO> student_absent_list(Map<String, String> map) {
		logger.info("Absent_Dao student_absent_list 실행 {}", map);
		return sqlSessionTemplate.selectList(NS+"student_absent_list", map);
	}

	// 클릭해서 각 신청을 상세조회(처리중)
	@Override
	public App_Form_DTO absent_detail_no(String seq) {
		logger.info("Absent_Dao absent_detail_no 실행 {}", seq);
		return sqlSessionTemplate.selectOne(NS+"absent_detail", seq);
	}
	
	// 클릭해서 각 신청을 상세조회(승인)
	@Override
	public App_Form_DTO absent_detail_yes(String seq) {
		logger.info("Absent_Dao absent_detail_yes 실행 {}", seq);
		return sqlSessionTemplate.selectOne(NS+"absent_detail", seq);
	}
	// 승인인 신청의 경우 사인 이미지 조회
	@Override
	public String select_signature(Map<String, String> map) {
		logger.info("Absent_Dao select_signature 실행 {}", map);
		return sqlSessionTemplate.selectOne(NS+"select_signature", map);
	}
	
	// 클릭해서 각 신청을 상세조회(반려시)
	@Override
	public App_Form_DTO absent_detail_return(String seq) {
		logger.info("Absent_Dao absent_detail_return 실행 {}", seq);
		return sqlSessionTemplate.selectOne(NS+"absent_detail_return", seq);
	}

	// 강사, 관리자가 자신의 과정의 학생들것만의 내역서 리스트 보기
	@Override
	public List<App_Form_DTO> recipient_absent_list(Map<String, String> map) {
		logger.info("Absent_Dao recipient_absent_list 실행 {}", map);
		return sqlSessionTemplate.selectList(NS+"recipient_absent_list", map);
	}

	
	// 결석 신청
	// 결석 신청하려는 과정을 선택
	@Override
	public Course_DTO absent_course(String id) {
		logger.info("Absent_Dao absent_course 실행 {}", id);
		return sqlSessionTemplate.selectOne(NS+"absent_course", id);
	}

	// 관리자를 선택
	@Override
	public Admin_DTO absent_admin() {
		logger.info("Absent_Dao absent_admin 실행");
		return sqlSessionTemplate.selectOne(NS+"absent_admin");
	}

	// 학생이 결석 신청을 함
	@Override
	public int insert_absent_form(App_Form_DTO dto) {
		logger.info("Absent_Dao insert_absent_form 실행 {}", dto);
		return sqlSessionTemplate.insert(NS+"insert_absent_form", dto);
	}
	// 신청 상태 테이블 연결
	@Override
	public int insert_is_approve(String id) {
		logger.info("Absent_Dao insert_is_approve 실행 {}", id);
		return sqlSessionTemplate.insert(NS+"insert_is_approve", id);
	}

	// 강사 및 관리자가 미승인 사유를 작성
	@Override
	public int insert_unapprove_reason(Map<String, String> map) {
		logger.info("Absent_Dao insert_unapprove_reason 실행 {}", map);
		return sqlSessionTemplate.insert(NS+"insert_unapprove_reason", map);
	}
	// 동시에 승인 여부 수정
	@Override
	public int update_is_approve_Re(Map<String, String> map) {
		logger.info("Absent_Dao update_is_approve_Re 실행 {}", map);
		return sqlSessionTemplate.insert(NS+"update_is_approve_Re", map);
	}

	// 강사 및 관리자가 승인을 함
	@Override
	public int update_is_approve_Yes(Map<String, String> map) {
		logger.info("Absent_Dao update_is_approve_Yes 실행 {}", map);
		return sqlSessionTemplate.update(NS+"update_is_approve_Yes", map);
	}


}
