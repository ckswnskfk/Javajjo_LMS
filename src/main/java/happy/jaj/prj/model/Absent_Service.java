package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happy.jaj.prj.dtos.App_Form_DTO;

@Service
public class Absent_Service implements Absent_IService {

	private Logger logger = LoggerFactory.getLogger(Absent_Service.class);
	
	@Autowired
	private Absent_Interface absent_Interface;
	
	@Override
	public List<App_Form_DTO> student_absent_list(Map<String, String> map) {
		logger.info("Absent_Interface student_absent_list 실행");
		return absent_Interface.student_absent_list(map);
	}

	@Override
	public App_Form_DTO absent_detail(String seq) {
		logger.info("Absent_Interface absent_detail 실행");
		return absent_Interface.absent_detail(seq);
	}

	@Override
	public String select_signature(Map<String, String> map) {
		logger.info("Absent_Interface select_signature 실행");
		return absent_Interface.select_signature(map);
	}

	@Override
	public App_Form_DTO absent_detail_return(String seq) {
		logger.info("Absent_Interface absent_detail_return 실행");
		return absent_Interface.absent_detail_return(seq);
	}

}
