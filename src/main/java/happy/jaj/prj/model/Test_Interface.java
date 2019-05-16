package happy.jaj.prj.model;

import java.util.Map;

import happy.jaj.prj.dtos.Test_DTO;

public interface Test_Interface {
	
	public int testInsert(Test_DTO dto); 
	
	public Test_DTO testSelect(Map<String, String> map);
	

}
