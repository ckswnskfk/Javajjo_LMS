package happy.jaj.prj.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test_Service implements Test_IService {
	
	@Autowired
	private Test_Interface test_Interface;
	
	

}
