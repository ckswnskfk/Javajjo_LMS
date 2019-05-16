package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.App_Form_DTO;

@Repository
public class Absent_Dao implements Absent_Interface {

	private Logger logger = LoggerFactory.getLogger(Absent_Dao.class);
	private final String NS = "happy.jaj.prj.Absent_Mapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<App_Form_DTO> student_absent_list(Map<String, String> map) {
		logger.info("Absent_Dao student_absent_list 실행");
		return sqlSessionTemplate.selectList(NS+"student_absent_list", map);
	}

	@Override
	public App_Form_DTO absent_detail(String seq) {
		logger.info("Absent_Dao absent_detail");
		return sqlSessionTemplate.selectOne(NS+"absent_detail", seq);
	}

	@Override
	public String select_signature(Map<String, String> map) {
		logger.info("Absent_Dao select_signature");
		return sqlSessionTemplate.selectOne(NS+"select_signature", map);
	}

	@Override
	public App_Form_DTO absent_detail_return(String seq) {
		logger.info("Absent_Dao absent_detail_return");
		return sqlSessionTemplate.selectOne(NS+"absent_detail_return", seq);
	}

}
