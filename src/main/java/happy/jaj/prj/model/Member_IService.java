package happy.jaj.prj.model;

import java.util.Map;

import happy.jaj.prj.dtos.Member_DTO;

public interface Member_IService {
	
//	loginMember : 로그인
	public Member_DTO loginMember(Map<String, String> map); 
}
