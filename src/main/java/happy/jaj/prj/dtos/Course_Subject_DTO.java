package happy.jaj.prj.dtos;

public class Course_Subject_DTO {

	private String seq;
	private String coursecode;
	private String subjectcode;
	private String subjecttime;
	private String content;
	private String startdate;
	
	public Course_Subject_DTO() {
	}

	public Course_Subject_DTO(String seq) {
		super();
		this.seq = seq;
	}

	public Course_Subject_DTO(String seq, String coursecode, String subjectcode, String subjecttime, String content,
			String startdate) {
		super();
		this.seq = seq;
		this.coursecode = coursecode;
		this.subjectcode = subjectcode;
		this.subjecttime = subjecttime;
		this.content = content;
		this.startdate = startdate;
	}

	public Course_Subject_DTO(String coursecode, String subjectcode, String subjecttime, String content,
			String startdate) {
		super();
		this.coursecode = coursecode;
		this.subjectcode = subjectcode;
		this.subjecttime = subjecttime;
		this.content = content;
		this.startdate = startdate;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public String getSubjectcode() {
		return subjectcode;
	}

	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}

	public String getSubjecttime() {
		return subjecttime;
	}

	public void setSubjecttime(String subjecttime) {
		this.subjecttime = subjecttime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	@Override
	public String toString() {
		return "Course_Subject_DTO [seq=" + seq + ", coursecode=" + coursecode + ", subjectcode=" + subjectcode
				+ ", subjecttime=" + subjecttime + ", content=" + content + ", startdate=" + startdate + "]";
	}
	
}
