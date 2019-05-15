package happy.jaj.prj.model;

import java.util.Map;

import happy.jaj.prj.dtos.Student_DTO;

public interface Member_IService {
	
//	loginMember : 로그인
	public Student_DTO loginMember(Map<String, String> map); 
}
