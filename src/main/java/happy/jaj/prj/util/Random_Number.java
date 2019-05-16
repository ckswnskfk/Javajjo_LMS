package happy.jaj.prj.util;

public class Random_Number {
	
	public int RNumber() {
		return (int)(Math.random()*10);
	}
	
	public String Random_Pw() {
		String randomPw ="";
		for(int i = 0; i <8;i++) {
			randomPw += RNumber();
		}
		return randomPw;
	}
}
