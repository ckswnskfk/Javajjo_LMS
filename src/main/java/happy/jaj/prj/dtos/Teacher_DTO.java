package happy.jaj.prj.dtos;

public class Teacher_DTO {

	private String id;
	private String name;
	private String pw;
	private String coursecode;
	private String coursename;
	
	public Teacher_DTO() {
	}
	
	
	
	public Teacher_DTO(String id, String name, String pw, String coursecode, String coursename) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.coursecode = coursecode;
		this.coursename = coursename;
	}



	public Teacher_DTO(String id, String name, String pw, String coursecode) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.coursecode = coursecode;
	}
	
	
	public String getCoursename() {
		return coursename;
	}


	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	@Override
	public String toString() {
		return "Teacher_DTO [id=" + id + ", name=" + name + ", pw=" + pw + ", coursecode=" + coursecode + "]";
	}
	
	
}
