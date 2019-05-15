package happy.jaj.prj.dtos;

public class Answer_Des_DTO {

	private String id;
	private String examcode;
	private String examnum;
	private String answer;
	private String originfile;
	private String newfilename;
	
	public Answer_Des_DTO() {
	}

	public Answer_Des_DTO(String id, String examcode, String examnum, String answer, String originfile,
			String newfilename) {
		super();
		this.id = id;
		this.examcode = examcode;
		this.examnum = examnum;
		this.answer = answer;
		this.originfile = originfile;
		this.newfilename = newfilename;
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

	public String getOriginfile() {
		return originfile;
	}

	public void setOriginfile(String originfile) {
		this.originfile = originfile;
	}

	public String getNewfilename() {
		return newfilename;
	}

	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}

	@Override
	public String toString() {
		return "Answer_Des_DTO [id=" + id + ", examcode=" + examcode + ", examnum=" + examnum + ", answer=" + answer
				+ ", originfile=" + originfile + ", newfilename=" + newfilename + "]";
	}

	
	
	
}
