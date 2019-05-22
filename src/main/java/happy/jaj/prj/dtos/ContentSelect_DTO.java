package happy.jaj.prj.dtos;

public class ContentSelect_DTO {
	
	private String examcode;
	private String contentnum;
	private String examcontent;
	
	
	public ContentSelect_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "ContentSelect_DTO [examcode=" + examcode + ", contentnum=" + contentnum + ", examcontent=" + examcontent
				+ "]";
	}


	public ContentSelect_DTO(String examcode, String contentnum, String examcontent) {
		super();
		this.examcode = examcode;
		this.contentnum = contentnum;
		this.examcontent = examcontent;
	}


	public String getExamcode() {
		return examcode;
	}
	public void setExamcode(String examcode) {
		this.examcode = examcode;
	}
	public String getContentnum() {
		return contentnum;
	}
	public void setContentnum(String contentnum) {
		this.contentnum = contentnum;
	}
	public String getExamcontent() {
		return examcontent;
	}
	public void setExamcontent(String examcontent) {
		this.examcontent = examcontent;
	}
	
	

}
