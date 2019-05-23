package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happy.jaj.prj.dtos.Attended_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Student_DTO;
@Service
public class Attended_Service implements Attended_IService {
	
	private Logger logger = LoggerFactory.getLogger(Attended_Service.class);
	
	
	@Autowired
	private Attended_Interface attended_Interface;

	
	
//  cal_stucos : 학생 과정 조회
	@Override
	public List<Course_DTO> cal_stucos(String id) {
		logger.info("Attended_Service cal_stucos 실행");
		return attended_Interface.cal_stucos(id);
	}

	
//	cal_stuatt : 학생 출결 조회
	@Override
	public List<Attended_DTO> cal_stuatt(String id) {
		logger.info("Attended_Service cal_stuatt 실행" , id);
		return attended_Interface.cal_stuatt(id);
	}

//	cal_cosview : 강사 과정 조회
	@Override
	public Course_DTO cal_cosview(String id) {
		logger.info("Attended_Service cal_cosview 실행");
		return attended_Interface.cal_cosview(id);
	}
	//	cal_monlist : 강사 캘린더 출결 조회
	@Override
	public List<Attended_DTO> cal_monlist(Map<String, String> map) {
		logger.info("Attended_Service cal_monlist 실행", map);
		return (List<Attended_DTO>) attended_Interface.cal_monlist(map);
	}
	//	cal_daylist : 강사 출석부 조회
	@Override
	public List<Attended_DTO> cal_daylist(String regdate) {
		logger.info("Attended_Service cal_daylist 실행", regdate);
		return (List<Attended_DTO>)attended_Interface.cal_daylist(regdate);
	}
	//	cal_monatt : 강사 학생 출석 상세 조회
	@Override
	public List<Student_DTO> cal_detail(String id) {
		logger.info("Attended_Service cal_detail 실행");
		return (List<Student_DTO>) attended_Interface.cal_detail(id);
	}
	// 결석 문자 발송 ( 생각중 )
	@Override
	public List<Attended_DTO> cal_sms(String a_check) {
		logger.info("Attended_Service cal_sms 실행");
		return attended_Interface.cal_sms(a_check);
	}
	
	
	//	cal_attended : 출석 및 퇴실,결석
	@Override
	public boolean cal_attended(Attended_DTO dto) {
		logger.info("Attended_Service cal_attended 실행");
		return attended_Interface.cal_attended(dto);
	}



}
