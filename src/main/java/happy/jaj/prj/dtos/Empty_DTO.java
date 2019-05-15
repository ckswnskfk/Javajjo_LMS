package happy.jaj.prj.dtos;

public class Empty_DTO {

	private String code;
	private String name;
	private String personel;
	
	public Empty_DTO() {
	}

	public Empty_DTO(String code, String name, String personel) {
		super();
		this.code = code;
		this.name = name;
		this.personel = personel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonel() {
		return personel;
	}

	public void setPersonel(String personel) {
		this.personel = personel;
	}

	@Override
	public String toString() {
		return "Empty_DTO [code=" + code + ", name=" + name + ", personel=" + personel + "]";
	}

	
	
	
}
