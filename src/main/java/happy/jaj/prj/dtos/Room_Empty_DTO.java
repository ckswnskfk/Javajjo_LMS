package happy.jaj.prj.dtos;

public class Room_Empty_DTO {

	private String code;
	private String id;
	private String name;
	private String regdate;
	
	public Room_Empty_DTO() {
	}
	

	public Room_Empty_DTO(String code, String id, String regdate) {
		super();
		this.code = code;
		this.id = id;
		this.regdate = regdate;
	}


	public Room_Empty_DTO(String code, String id, String name, String regdate) {
		super();
		this.code = code;
		this.id = id;
		this.name = name;
		this.regdate = regdate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Room_Empty_DTO [code=" + code + ", id=" + id + ", name=" + name + ", regdate=" + regdate + "]";
	}

	
	
}
