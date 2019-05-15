package happy.jaj.prj.dtos;

public class Member_DTO {

	private String id;
	private String name;
	private String pw;
	private String gender;
	private String birth;
	private String addr;
	private String admin_check;
	
	public Member_DTO() {
	}

	public Member_DTO(String id, String name, String pw, String gender, String birth, String addr, String admin_check) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.gender = gender;
		this.birth = birth;
		this.addr = addr;
		this.admin_check = admin_check;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAdmin_check() {
		return admin_check;
	}

	public void setAdmin_check(String admin_check) {
		this.admin_check = admin_check;
	}

	@Override
	public String toString() {
		return "Member_DTO [id=" + id + ", name=" + name + ", pw=" + pw + ", gender=" + gender + ", birth=" + birth
				+ ", addr=" + addr + ", admin_check=" + admin_check + "]";
	}
	
	
}