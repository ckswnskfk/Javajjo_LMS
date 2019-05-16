package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import happy.jaj.prj.dtos.App_Form_DTO;

public interface Absent_IService {

	public List<App_Form_DTO> student_absent_list(Map<String, String> map);
	
	public App_Form_DTO absent_detail(String seq);
	
	public String select_signature(Map<String, String> map);
	
	public App_Form_DTO absent_detail_return(String seq);
}
