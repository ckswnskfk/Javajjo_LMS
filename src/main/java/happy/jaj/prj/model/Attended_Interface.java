package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import happy.jaj.prj.dtos.Attended_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.dtos.Student_DTO;

public interface Attended_Interface {

	
	//  cal_stucos : 학생 과정 조회
	public List<Course_DTO> cal_stucos(String id);
	
	//	cal_stuatt : 학생 출결 조회
	public List<Attended_DTO> cal_stuatt(String id);
	
	//	cal_cosview : 강사 과정 조회
	public Course_DTO cal_cosview(String id);
	
	//	cal_monlist : 강사 캘린더 출결 조회
	public String cal_monlist(Map<String, String> map);	

	//	cal_daylist : 강사 출석부 조회
	public List<Attended_DTO> cal_daylist(String regdate);
	
	//	cal_monatt : 강사 학생 출석 상세 조회
	public List<Student_DTO> cal_detail(String id);
	
	// 	cal_sms : 결석 문자 발송 
	public List<Student_DTO> cal_sms(String regdate);
	
	//	cal_attended : 입실
	public boolean cal_attended(String id);

	// 퇴실
	public boolean cal_exit(String id);
	
	// 해당일 출석체크 돼있는지 확인
	public int cal_chk(String id);
}
