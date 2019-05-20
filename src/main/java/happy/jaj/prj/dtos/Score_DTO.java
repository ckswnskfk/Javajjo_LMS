package happy.jaj.prj.dtos;

public class Score_DTO {

	private String id;
	private String examcheck;
	private String testcode;
	private String examcode;
	private int score;
	private String name;
	
	
	
	@Override
	public String toString() {
		return "Score_DTO [id=" + id + ", examcheck=" + examcheck + ", testcode=" + testcode + ", examcode=" + examcode
				+ ", score=" + score + "]";
	}
	public Score_DTO(String id, String examcheck, String testcode, String examcode, int score) {
		super();
		this.id = id;
		this.examcheck = examcheck;
		this.testcode = testcode;
		this.examcode = examcode;
		this.score = score;
	}
	public Score_DTO(String name, String id, int score) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
	}
	public Score_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExamcheck() {
		return examcheck;
	}
	public void setExamcheck(String examcheck) {
		this.examcheck = examcheck;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	

}
