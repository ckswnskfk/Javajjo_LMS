package happy.jaj.prj.dtos;

public class UserCourse_DTO {

	private String id;
	private String coursecode;
	private String regdate;
	
	
	
	@Override
	public String toString() {
		return "UserCourse_DTO [id=" + id + ", coursecode=" + coursecode + ", regdate=" + regdate + "]";
	}
	public UserCourse_DTO(String id, String coursecode) {
		super();
		this.id = id;
		this.coursecode = coursecode;
	}
	public UserCourse_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserCourse_DTO(String id, String coursecode, String regdate) {
		super();
		this.id = id;
		this.coursecode = coursecode;
		this.regdate = regdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCoursecode() {
		return coursecode;
	}
	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	

}
