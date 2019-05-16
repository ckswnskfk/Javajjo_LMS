package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Attended_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;

@Repository
public class Attended_Dao implements Attended_Interface {

	
	private Logger logger = LoggerFactory.getLogger(Attended_Dao.class);
	private final String NS_Attended = "happy.jaj.prj.Attended_Mapper.";
	private final String NS_Course = "happy.jaj.prj.Course_Mapper.";
	private final String NS_Student ="happy.jaj.prj.Member_Mapper.";
	
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
//  cal_stucos : 학생 과정 조회
	@Override
	public List<UserCourse_DTO> cal_stucos(String seq) {
		return sqlSession.selectList(NS_Attended+"cal_stucos", seq);
	}
//	cal_stuatt : 학생 출결 조회
	@Override
	public Attended_DTO cal_stuatt(String id) {
		Attended_DTO dto = null;
		dto = sqlSession.selectOne(NS_Student+"cal_stuatt", id);
		return dto;
	}

//	cal_cosview : 강사 과정 조회
	@Override
	public String cal_cosview(String id) {
		String cos = sqlSession.selectOne(NS_Attended+"cal_cosview", id);
		return cos;
	}
//	cal_monlist : 강사 캘린더 출결 조회
	@Override
	public Attended_DTO cal_monlist(Map<String, String> map) {
		Attended_DTO dto = null;
		dto = (Attended_DTO) sqlSession.selectList(NS_Attended+"cal_monlist", map);
		return dto;
	}
//	cal_daylist : 강사 출석부 조회
	@Override
	public Attended_DTO cal_daylist(Map<String, String> map) {
		Attended_DTO dto = null;
		dto = (Attended_DTO) sqlSession.selectList(NS_Attended+"cal_daylist", map);
		return null;
	}
//	cal_monatt : 강사 학생 출석 상세 조회
	@Override
	public Attended_DTO cal_monatt(String id) {
		Attended_DTO dto = null;
		dto = sqlSession.selectOne(NS_Attended+"cal_monatt", id);
		return dto;
	}
	// 결석 문자 발송 ( 생각중 )
//	@Override
//	public boolean cal_sms() {
//		boolean isc = false;
//		if()
//		return false;
//	}
	//	cal_attended : 출석 및 퇴실,결석
	@Override
	public Attended_DTO cal_attended(String id) {
		Object dto = null;
		dto = sqlSession.insert(NS_Attended+"cal_attended"+id);
		return (Attended_DTO) dto;
	}
	@Override
	public boolean cal_sms() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
