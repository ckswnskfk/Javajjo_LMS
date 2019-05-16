package happy.jaj.prj.model;

import java.util.Map;

import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Test_DTO;

@Repository
public class Test_Dao implements Test_Interface {

	@Override
	public int testInsert(Test_DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Test_DTO testSelect(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
