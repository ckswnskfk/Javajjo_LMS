package happy.jaj.prj.dtos;

public class Subject_DTO {

	private String subject_code;
	private String subject_name;
	private String subject_type;
	private String exam_type;
	private String time;
	private String typecode;
	
	public Subject_DTO() {
	}

	public Subject_DTO(String subject_code, String subject_name, String subject_type, String exam_type, String time) {
		super();
		this.subject_code = subject_code;
		this.subject_name = subject_name;
		this.subject_type = subject_type;
		this.exam_type = exam_type;
		this.time = time;
	}
	
	public Subject_DTO(String subject_code, String subject_name, String subject_type, String typecode) {
		super();
		this.subject_code = subject_code;
		this.subject_name = subject_name;
		this.subject_type = subject_type;
		this.typecode = typecode;
	}

	public String getSubject_code() {
		return subject_code;
	}

	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public String getSubject_type() {
		return subject_type;
	}

	public void setSubject_type(String subject_type) {
		this.subject_type = subject_type;
	}

	public String getExam_type() {
		return exam_type;
	}

	public void setExam_type(String exam_type) {
		this.exam_type = exam_type;
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
		return "Subject_DTO [subject_code=" + subject_code + ", subject_name=" + subject_name + ", subject_type="
				+ subject_type + ", exam_type=" + exam_type + ", time=" + time + ", typecode=" + typecode + "]";
	}

	
	
}
