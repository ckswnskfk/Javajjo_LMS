package happy.jaj.prj.dtos;

public class Exam_Sel_DTO {

	private String exam;
	private String examcode;
	private String c_answer;
	private String examnum;
	private String allot;
	
	public Exam_Sel_DTO(String exam, String examcode, String c_answer, String examnum, String allot) {
		super();
		this.exam = exam;
		this.examcode = examcode;
		this.c_answer = c_answer;
		this.examnum = examnum;
		this.allot = allot;
	}

	public Exam_Sel_DTO(String exam, String examcode, String c_answer) {
		super();
		this.exam = exam;
		this.examcode = examcode;
		this.c_answer = c_answer;
	}

	public Exam_Sel_DTO(String exam, String examcode, String examnum, String allot) {
		super();
		this.exam = exam;
		this.examcode = examcode;
		this.examnum = examnum;
		this.allot = allot;
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

	public String getC_answer() {
		return c_answer;
	}

	public void setC_answer(String c_answer) {
		this.c_answer = c_answer;
	}

	@Override
	public String toString() {
		return "Exam_Sel_DTO [exam=" + exam + ", examcode=" + examcode + ", c_answer=" + c_answer + ", examnum="
				+ examnum + ", allot=" + allot + "]";
	}

	
}
