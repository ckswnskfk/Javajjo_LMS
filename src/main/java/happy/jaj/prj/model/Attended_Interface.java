package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import happy.jaj.prj.dtos.Attended_DTO;
import happy.jaj.prj.dtos.UserCourse_DTO;

public interface Attended_Interface {

	
	//  cal_stucos : 학생 과정 조회
	public List<UserCourse_DTO> cal_stucos(String seq);
	
	//	cal_stuatt : 학생 출결 조회
	public Attended_DTO cal_stuatt(String id);
	
	//	cal_cosview : 강사 과정 조회
	public String cal_cosview(String id);
	
	//	cal_monlist : 강사 캘린더 출결 조회
	public Attended_DTO cal_monlist(Map<String, String> map);	

	//	cal_daylist : 강사 출석부 조회
	public Attended_DTO cal_daylist(Map<String, String>map);
	
	//	cal_monatt : 강사 학생 출석 상세 조회
	public Attended_DTO cal_monatt(String id);
	
	// 결석 문자 발송 ( 생각중 )
	public boolean cal_sms();
	
	//	cal_attended : 출석 및 퇴실,결석
	public Attended_DTO cal_attended(String id);

	
	
}
