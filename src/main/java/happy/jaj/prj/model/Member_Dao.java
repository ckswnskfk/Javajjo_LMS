package happy.jaj.prj.model;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Student_DTO;

@Repository
public class Member_Dao implements Member_Interface {

	private final String NS = "happy.jaj.prj.Member_Mapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Student_DTO loginMember(Map<String, String> map) {
		return sqlSession.selectOne(NS+"a_login", map);
	}

	
	
	
	
}
