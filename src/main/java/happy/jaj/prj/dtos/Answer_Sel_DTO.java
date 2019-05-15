package happy.jaj.prj.dtos;

public class Answer_Sel_DTO {

	private String id;
	private String examcode;
	private String examnum;
	private String answer;
	
	public Answer_Sel_DTO() {
	}

	public Answer_Sel_DTO(String id, String examcode, String examnum, String answer) {
		super();
		this.id = id;
		this.examcode = examcode;
		this.examnum = examnum;
		this.answer = answer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExamcode() {
		return examcode;
	}

	public void setExamcode(String examcode) {
		this.examcode = examcode;
	}

	public String getExamnum() {
		return examnum;
	}

	public void setExamnum(String examnum) {
		this.examnum = examnum;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer_Sel_DTO [id=" + id + ", examcode=" + examcode + ", examnum=" + examnum + ", answer=" + answer
				+ "]";
	}

	
	
	
}
