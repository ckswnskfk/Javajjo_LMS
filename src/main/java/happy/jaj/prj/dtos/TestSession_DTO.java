package happy.jaj.prj.dtos;

public class TestSession_DTO {
	
	private String coursecode;
	private String coursename;
	private String subjectcode;
	private String subjectname;
	private String subjecttype;
	private String examtype;
	private String testname;
	private String testcode;
	private String testday;
	
	

	@Override
	public String toString() {
		return "TestSession_DTO [coursecode=" + coursecode + ", coursename=" + coursename + ", subjectcode="
				+ subjectcode + ", subjectname=" + subjectname + ", subjecttype=" + subjecttype + ", examtype="
				+ examtype + ", testname=" + testname + ", testcode=" + testcode + ", testday=" + testday + "]";
	}
	
	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public TestSession_DTO(String coursecode, String coursename, String subjectcode, String subjectname,
			String subjecttype, String examtype, String testname, String testcode, String testday) {
		super();
		this.coursecode = coursecode;
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
