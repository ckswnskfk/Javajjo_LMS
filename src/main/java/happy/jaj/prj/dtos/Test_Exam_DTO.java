package happy.jaj.prj.dtos;

public class Test_Exam_DTO {

	private String testcode;
	private String examcode;
	private String allot;
	private String examnum;
	
	public Test_Exam_DTO() {
	}

	public Test_Exam_DTO(String testcode, String examcode, String allot, String examnum) {
		super();
		this.testcode = testcode;
		this.examcode = examcode;
		this.allot = allot;
		this.examnum = examnum;
	}

	public String getTestcode() {
		return testcode;
	}

	public void setTestcode(String testcode) {
		this.testcode = testcode;
	}

	public String getExamcode() {
		return examcode;
	}

	public void setExamcode(String examcode) {
		this.examcode = examcode;
	}

	public String getAllot() {
		return allot;
	}

	public void setAllot(String allot) {
		this.allot = allot;
	}

	public String getExamnum() {
		return examnum;
	}

	public void setExamnum(String examnum) {
		this.examnum = examnum;
	}

	@Override
	public String toString() {
		return "Test_Exam_DTO [testcode=" + testcode + ", examcode=" + examcode + ", allot=" + allot + ", examnum="
				+ examnum + "]";
	}

	
}
