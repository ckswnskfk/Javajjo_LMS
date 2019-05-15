package happy.jaj.prj.dtos;

public class Attended_DTO {

	private String seq;
	private String id;
	private String name;
	private String a_check;
	private String regdate;
	
	public Attended_DTO() {
	}

	public Attended_DTO(String seq, String id, String name, String a_check, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.a_check = a_check;
		this.regdate = regdate;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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

	public String getA_check() {
		return a_check;
	}

	public void setA_check(String a_check) {
		this.a_check = a_check;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "A_DTO [seq=" + seq + ", id=" + id + ", name=" + name + ", a_check=" + a_check + ", regdate=" + regdate
				+ "]";
	}

	
	
	
}
