package happy.jaj.prj.dtos;

public class Admin_DTO {

	private String id;
	private String name;
	private String pw;
	
	public Admin_DTO() {
	}

	public Admin_DTO(String id, String name, String pw) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
	}

	public Admin_DTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Admin_DTO [id=" + id + ", name=" + name + ", pw=" + pw + "]";
	}
	
	
}
