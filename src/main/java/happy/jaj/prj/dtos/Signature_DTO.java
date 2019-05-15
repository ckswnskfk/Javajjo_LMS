package happy.jaj.prj.dtos;

public class Signature_DTO {

	private String signature_id;
	private String filename;
	private String newfilename;
	
	public Signature_DTO() {
		super();
	}

	public Signature_DTO(String signature_id, String filename, String newfilename) {
		super();
		this.signature_id = signature_id;
		this.filename = filename;
		this.newfilename = newfilename;
	}

	public String getSignature_id() {
		return signature_id;
	}

	public void setSignature_id(String signature_id) {
		this.signature_id = signature_id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewfilename() {
		return newfilename;
	}

	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}

	@Override
	public String toString() {
		return "Signature_DTO [signature_id=" + signature_id + ", filename=" + filename + ", newfilename=" + newfilename
				+ "]";
	}

	

}
