package happy.jaj.prj.dtos;

public class Exam_Des_DTO {

	private String exam;
	private String examcode;
	private String explanation;
	private String standard;
	private String c_answer;
	private String examnum;
	private String allot;
	
	public Exam_Des_DTO() {
	}
	
	public Exam_Des_DTO(String exam, String examcode, String explanation, String standard, String c_answer,
			String examnum, String allot) {
		super();
		this.exam = exam;
		this.examcode = examcode;
		this.explanation = explanation;
		this.standard = standard;
		this.c_answer = c_answer;
		this.examnum = examnum;
		this.allot = allot;
	}

	public Exam_Des_DTO(String exam, String examcode, String explanation, String standard, String examnum,
			String allot) {
		super();
		this.exam = exam;
		this.examcode = examcode;
		this.explanation = explanation;
		this.standard = standard;
		this.examnum = examnum;
		this.allot = allot;
	}

	public Exam_Des_DTO(String exam, String examcode, String explanation, String standard, String c_answer) {
		super();
		this.exam = exam;
		this.examcode = examcode;
		this.explanation = explanation;
		this.standard = standard;
		this.c_answer = c_answer;
	}

	public Exam_Des_DTO(String exam, String explanation, String standard, String c_answer) {
		super();
		this.exam = exam;
		this.explanation = explanation;
		this.standard = standard;
		this.c_answer = c_answer;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getExamcode() {
		return examcode;
	}

	public void setExamcode(String examcode) {
		this.examcode = examcode;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getC_answer() {
		return c_answer;
	}

	public void setC_answer(String c_answer) {
		this.c_answer = c_answer;
	}

	public String getExamnum() {
		return examnum;
	}

	public void setExamnum(String examnum) {
		this.examnum = examnum;
	}

	public String getAllot() {
		return allot;
	}

	public void setAllot(String allot) {
		this.allot = allot;
	}

	@Override
	public String toString() {
		return "Exam_Des_DTO [exam=" + exam + ", examcode=" + examcode + ", explanation=" + explanation + ", standard="
				+ standard + ", c_answer=" + c_answer + ", examnum=" + examnum + ", allot=" + allot + "]";
	}

	
	
}
