package happy.jaj.prj.model;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happy.jaj.prj.dtos.Member_DTO;

@Service
public class Member_Service implements Member_IService {

	private Logger logger = LoggerFactory.getLogger(Member_Service.class);
	
	@Autowired
	private Member_Interface member_Interface;

	@Override
	public Member_DTO loginMember(Map<String, String> map) {
		return member_Interface.loginMember(map);
	}

	
	
	

}
