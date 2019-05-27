package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Attended_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Student_DTO;

@Repository
public class Attended_Dao implements Attended_Interface {

	
	private Logger logger = LoggerFactory.getLogger(Attended_Dao.class);
	private final String NS_Attended = "happy.jaj.prj.Attended_Mapper.";
//	private final String NS_Course = "happy.jaj.prj.Course_Mapper.";
//	private final String NS_Student ="happy.jaj.prj.Member_Mapper.";
	
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
//  cal_stucos : 학생 과정 조회
	@Override
	public List<Course_DTO> cal_stucos(String id) {
		logger.info("Attended_Dao cal_stucos 실행{}", id);
		return sqlSessionTemplate.selectList(NS_Attended+"cal_stucos", id);
	}
//	cal_stuatt : 학생 출결 조회
	@Override
	public List<Attended_DTO> cal_stuatt(String id) {
		logger.info("Attended_Dao cal_stucos 실행{}");
		return sqlSessionTemplate.selectList(NS_Attended+"cal_stuatt", id);
	}
//	cal_cosview : 강사 과정 조회
	@Override
	public Course_DTO cal_cosview(String id) {
		logger.info("Attended_Dao cal_cosview 실행{}", id);
		return sqlSessionTemplate.selectOne(NS_Attended+"cal_cosview", id);
	}
//	cal_monlist : 강사 캘린더 출결 조회
	@Override
	public String cal_monlist(Map<String, String> map) {
		logger.info("Attended_Dao cal_monlist 실행{}", map);
		return  sqlSessionTemplate.selectOne(NS_Attended+"cal_monlist", map);
	}
//	cal_daylist : 강사 출석부 조회
	@Override
	public List<Attended_DTO> cal_daylist(String regdate) {
		logger.info("Attended_Dao cal_daylist 실행{}", regdate);
		return sqlSessionTemplate.selectList(NS_Attended+"cal_daylist", regdate);
	}
//	cal_monatt : 강사 학생 출석 상세 조회
	@Override
	public List<Student_DTO> cal_detail(String id) {
		logger.info("Attended_Dao cal_detail 실행{}", id);
		return sqlSessionTemplate.selectList(NS_Attended+"cal_detail", id);
	}
	
	
	
	// 결석 문자 발송 ( 생각중 )
//	@Override
//	public boolean cal_sms() {
//	logger.info("Attended_Dao cal_stucos 실행{}", seq);
//		boolean isc = false;
//		if()
//		return false;
//	}
	//	cal_attended : 출석 및 퇴실,결석
	@Override
	public boolean cal_attended(Attended_DTO dto) {
		logger.info("Attended_Dao cal_attended 실행{}", dto);
		int n = sqlSessionTemplate.insert(NS_Attended+"cal_attended", dto);
		return n>0?true:false;
	}
	@Override
	public List<Attended_DTO> cal_sms(String a_check) {
		logger.info("Attended_Dao cal_sms 실행{}");
		return sqlSessionTemplate.selectList(NS_Attended+"cal_sms", a_check);
	}

	

}
