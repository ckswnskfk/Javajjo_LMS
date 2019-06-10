package happy.jaj.prj.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import happy.jaj.prj.model.Attended_IService;





public class ExecuteUsingQuartz {
	   
	   @Autowired
	   private Attended_IService attended_Iservice;
	   
	   public void otp() throws Exception {
		   Integer n = null;
	      boolean del =attended_Iservice.cal_attended_null();
	      System.out.println(del);
	   }	
}