package happy.jaj.prj.dtos;

public class ContentSelect_DTO {
	
	private String examcode;
	private int examnum;
	private String examcontent;
	
	
	public ContentSelect_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ContentSelect_DTO(String examcode, int examnum, String examcontent) {
		super();
		this.examcode = examcode;
		this.examnum = examnum;
		this.examcontent = examcontent;
	}


	public String getExamcode() {
		return examcode;
	}
	public void setExamcode(String examcode) {
		this.examcode = examcode;
	}
	public int getExamnum() {
		return examnum;
	}
	public void setExamnum(int examnum) {
		this.examnum = examnum;
	}
	public String getExamcontent() {
		return examcontent;
	}
	public void setExamcontent(String examcontent) {
		this.examcontent = examcontent;
	}
	
	

}
