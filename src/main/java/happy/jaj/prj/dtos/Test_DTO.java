package happy.jaj.prj.dtos;

public class Test_DTO {

	private String testcode;
	private String testname;
	private String subjecttype;
	private String examtype;
	
	
	
	public Test_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Test_DTO(String testcode, String testname, String subjecttype, String examtype) {
		super();
		this.testcode = testcode;
		this.testname = testname;
		this.subjecttype = subjecttype;
		this.examtype = examtype;
	}
	

	@Override
	public String toString() {
		return "Test_DTO [testcode=" + testcode + ", testname=" + testname + ", subjecttype=" + subjecttype
				+ ", examtype=" + examtype + "]";
	}

	public String getTestcode() {
		return testcode;
	}
	public void setTestcode(String testcode) {
		this.testcode = testcode;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getSubjecttype() {
		return subjecttype;
	}
	public void setSubjecttype(String subjecttype) {
		this.subjecttype = subjecttype;
	}
	public String getExamtype() {
		return examtype;
	}
	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}
	
	
	

}
