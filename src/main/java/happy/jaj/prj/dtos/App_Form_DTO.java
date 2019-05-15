package happy.jaj.prj.dtos;

public class App_Form_DTO {

	private String form_seq;
	private String app_date;
	private String student_id;
	private String student_name;
	private String recipient_id;
	private String coursecode;
	private String coursename;
	private String reason;
	private String start_date;
	private String absent_days;
	private String filename;
	private String newfilename;
	private String stm;
	private String unapproved_reason;
	
	public App_Form_DTO() {
	}

	public App_Form_DTO(String form_seq, String app_date, String student_id, String recipient_id, String coursecode,
			String reason, String start_date, String absent_days, String filename, String newfilename) {
		super();
		this.form_seq = form_seq;
		this.app_date = app_date;
		this.student_id = student_id;
		this.recipient_id = recipient_id;
		this.coursecode = coursecode;
		this.reason = reason;
		this.start_date = start_date;
		this.absent_days = absent_days;
		this.filename = filename;
		this.newfilename = newfilename;
	}

	public App_Form_DTO(String form_seq, String app_date, String coursecode, String coursename, String start_date,
			String stm) {
		super();
		this.form_seq = form_seq;
		this.app_date = app_date;
		this.coursecode = coursecode;
		this.coursename = coursename;
		this.start_date = start_date;
		this.stm = stm;
	}

	public App_Form_DTO(String form_seq, String app_date, String coursename, String reason, String start_date,
			String absent_days, String filename, String newfilename, String stm) {
		super();
		this.form_seq = form_seq;
		this.app_date = app_date;
		this.coursename = coursename;
		this.reason = reason;
		this.start_date = start_date;
		this.absent_days = absent_days;
		this.filename = filename;
		this.newfilename = newfilename;
		this.stm = stm;
	}

	public App_Form_DTO(String form_seq, String app_date, String coursecode, String coursename, String reason,
			String start_date, String absent_days, String filename, String newfilename, String stm,
			String unapproved_reason) {
		super();
		this.form_seq = form_seq;
		this.app_date = app_date;
		this.coursecode = coursecode;
		this.coursename = coursename;
		this.reason = reason;
		this.start_date = start_date;
		this.absent_days = absent_days;
		this.filename = filename;
		this.newfilename = newfilename;
		this.stm = stm;
		this.unapproved_reason = unapproved_reason;
	}

	public App_Form_DTO(String form_seq, String app_date, String student_name, String coursename) {
		super();
		this.form_seq = form_seq;
		this.app_date = app_date;
		this.student_name = student_name;
		this.coursename = coursename;
	}

	public String getForm_seq() {
		return form_seq;
	}

	public void setForm_seq(String form_seq) {
		this.form_seq = form_seq;
	}

	public String getApp_date() {
		return app_date;
	}

	public void setApp_date(String app_date) {
		this.app_date = app_date;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getRecipient_id() {
		return recipient_id;
	}

	public void setRecipient_id(String recipient_id) {
		this.recipient_id = recipient_id;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getAbsent_days() {
		return absent_days;
	}

	public void setAbsent_days(String absent_days) {
		this.absent_days = absent_days;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewfilename() {
		return newfilename;
	}

	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}

	public String getStm() {
		return stm;
	}

	public void setStm(String stm) {
		this.stm = stm;
	}

	public String getUnapproved_reason() {
		return unapproved_reason;
	}

	public void setUnapproved_reason(String unapproved_reason) {
		this.unapproved_reason = unapproved_reason;
	}

	@Override
	public String toString() {
		return "App_Form_DTO [form_seq=" + form_seq + ", app_date=" + app_date + ", student_id=" + student_id
				+ ", student_name=" + student_name + ", recipient_id=" + recipient_id + ", coursecode=" + coursecode
				+ ", coursename=" + coursename + ", reason=" + reason + ", start_date=" + start_date + ", absent_days="
				+ absent_days + ", filename=" + filename + ", newfilename=" + newfilename + ", stm=" + stm
				+ ", unapproved_reason=" + unapproved_reason + "]";
	}

	
	
}
