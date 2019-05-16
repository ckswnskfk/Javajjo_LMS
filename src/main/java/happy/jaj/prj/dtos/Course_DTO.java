package happy.jaj.prj.dtos;

public class Course_DTO {

	private String coursecode;
	private String coursename;
	private String startdate;
	private String id;
	private String teacher_name;
	private String time;
	private String coursecnt;
	
	public Course_DTO() {
	}

	public Course_DTO(String coursecode, String coursename, String startdate, String id, String teacher_name,
			String time, String coursecnt) {
		super();
		this.coursecode = coursecode;
		this.coursename = coursename;
		this.startdate = startdate;
		this.id = id;
		this.teacher_name = teacher_name;
		this.time = time;
		this.coursecnt = coursecnt;
	}
	
	public Course_DTO(String coursecode, String coursename, String startdate, String id,
			String time, String coursecnt) {
		super();
		this.coursecode = coursecode;
		this.coursename = coursename;
		this.startdate = startdate;
		this.id = id;
		this.time = time;
		this.coursecnt = coursecnt;
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

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
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

	public String getCoursecnt() {
		return coursecnt;
	}

	public void setCoursecnt(String coursecnt) {
		this.coursecnt = coursecnt;
	}

	@Override
	public String toString() {
		return "Course_DTO [coursecode=" + coursecode + ", coursename=" + coursename + ", startdate=" + startdate
				+ ", id=" + id + ", teacher_name=" + teacher_name + ", time=" + time + ", coursecnt=" + coursecnt + "]";
	}

	
}
