package happy.jaj.prj.dtos;

public class Subject_DTO {

	private String coursecode;
	private String subjectcode;
	private String subjectname;
	private String subjecttype;
	private String examtype;
	private String time;
	private String typecode;
	private String seq;
	
	
	public Subject_DTO() {
	}

	public Subject_DTO(String subjectcode, String subjectname, String subjecttype, String examtype, String time) {
		super();
		this.subjectcode = subjectcode;
		this.subjectname = subjectname;
		this.subjecttype = subjecttype;
		this.examtype = examtype;
		this.time = time;
	}
	
	
	public Subject_DTO(String coursecode, String subjectcode, String subjectname, String subjecttype, String examtype,
			String time, String typecode, String seq) {
		super();
		this.coursecode = coursecode;
		this.subjectcode = subjectcode;
		this.subjectname = subjectname;
		this.subjecttype = subjecttype;
		this.examtype = examtype;
		this.time = time;
		this.typecode = typecode;
		this.seq = seq;
	}

	public Subject_DTO(String subjectcode, String subjectname, String subjecttype, String typecode) {
		super();
		this.subjectcode = subjectcode;
		this.subjectname = subjectname;
		this.subjecttype = subjecttype;
		this.typecode = typecode;
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

	public String getsubjectcode() {
		return subjectcode;
	}

	public void setsubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}

	public String getsubjectname() {
		return subjectname;
	}

	public void setsubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getsubjecttype() {
		return subjecttype;
	}

	public void setsubjecttype(String subjecttype) {
		this.subjecttype = subjecttype;
	}

	public String getexamtype() {
		return examtype;
	}

	public void setexamtype(String examtype) {
		this.examtype = examtype;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	@Override
	public String toString() {
		return "Subject_DTO [subjectcode=" + subjectcode + ", subjectname=" + subjectname + ", subjecttype="
				+ subjecttype + ", examtype=" + examtype + ", time=" + time + ", typecode=" + typecode + "]";
	}

	
	
}
