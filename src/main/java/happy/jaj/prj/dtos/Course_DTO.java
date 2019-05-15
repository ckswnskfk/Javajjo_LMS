package happy.jaj.prj.dtos;

public class Course_DTO {

	private String course_code;
	private String course_name;
	private String start_date;
	private String id;
	private String teacher_name;
	private String time;
	private String courseCnt;
	
	public Course_DTO() {
	}

	public Course_DTO(String course_code, String course_name, String start_date, String id, String teacher_name,
			String time, String courseCnt) {
		super();
		this.course_code = course_code;
		this.course_name = course_name;
		this.start_date = start_date;
		this.id = id;
		this.teacher_name = teacher_name;
		this.time = time;
		this.courseCnt = courseCnt;
	}
	
	public Course_DTO(String course_code, String course_name, String start_date, String id,
			String time, String courseCnt) {
		super();
		this.course_code = course_code;
		this.course_name = course_name;
		this.start_date = start_date;
		this.id = id;
		this.time = time;
		this.courseCnt = courseCnt;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCourseCnt() {
		return courseCnt;
	}

	public void setCourseCnt(String courseCnt) {
		this.courseCnt = courseCnt;
	}

	@Override
	public String toString() {
		return "Course_DTO [course_code=" + course_code + ", course_name=" + course_name + ", start_date=" + start_date
				+ ", id=" + id + ", teacher_name=" + teacher_name + ", time=" + time + ", courseCnt=" + courseCnt + "]";
	}

	
}
