package happy.jaj.prj.dtos;

public class TestSession_DTO {
	
	private String coursename;
	private String subjectcode;
	private String subjectname;
	private String subjecttype;
	private String examtype;
	private String testname;
	private String testcode;
	private String testday;
	
	
	public TestSession_DTO(String coursename, String subjectcode, String subjectname, String subjecttype,
			String examtype, String testname, String testcode, String testday) {
		super();
		this.coursename = coursename;
		this.subjectcode = subjectcode;
		this.subjectname = subjectname;
		this.subjecttype = subjecttype;
		this.examtype = examtype;
		this.testname = testname;
		this.testcode = testcode;
		this.testday = testday;
	}
	public TestSession_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getSubjectcode() {
		return subjectcode;
	}
	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
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
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getTestcode() {
		return testcode;
	}
	public void setTestcode(String testcode) {
		this.testcode = testcode;
	}
	public String getTestday() {
		return testday;
	}
	public void setTestday(String testday) {
		this.testday = testday;
	}
	
	
	
	

}
