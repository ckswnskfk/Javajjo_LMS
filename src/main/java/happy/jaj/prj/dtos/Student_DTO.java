package happy.jaj.prj.dtos;

public class Student_DTO {

	private String id;
	private String name;
	private String pw;
	private String gender;
	private String birth;
	private String addr;
	private String s_check;
	private String regdate;
	private String a_check;
	private String sum;
	
	public Student_DTO() {
	}

	public Student_DTO(String id, String name, String pw, String gender, String birth, String addr, String s_check) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.gender = gender;
		this.birth = birth;
		this.addr = addr;
		this.s_check = s_check;
	}
	
	public Student_DTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Student_DTO(String id, String name, String sum) {
		super();
		this.id = id;
		this.name = name;
		this.sum = sum;
	}
	
	

	public Student_DTO(String id, String name, String gender, String birth, String regdate, String a_check) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.regdate = regdate;
		this.a_check = a_check;
	}
	
	

	public String getS_check() {
		return s_check;
	}

	public void setS_check(String s_check) {
		this.s_check = s_check;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getA_check() {
		return a_check;
	}

	public void setA_check(String a_check) {
		this.a_check = a_check;
	}

	public Student_DTO(String id, String name, String gender, String birth) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
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

	public String gets_check() {
		return s_check;
	}

	public void sets_check(String s_check) {
		this.s_check = s_check;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "Student_DTO [id=" + id + ", name=" + name + ", pw=" + pw + ", gender=" + gender + ", birth=" + birth
				+ ", addr=" + addr + ", s_check=" + s_check + ", regdate=" + regdate + ", a_check=" + a_check
				+ ", sum=" + sum + "]";
	}

	
	
}