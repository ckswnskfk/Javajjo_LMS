package happy.jaj.prj.dtos;

public class FileBoard_DTO {

	private String seq;
	private String id;
	private String title;
	private String content;
	private String regdate;
	private String readcount;
	private String filename;
	private String newfilename;
	
	
	public FileBoard_DTO() {
	}
	
	
	public FileBoard_DTO(String seq, String title, String content, String filename, String newfilename) {
		super();
		this.seq =seq;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
	}
	
	


	public FileBoard_DTO(String id, String title, String content, String regdate, String filename, String newfilename) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.filename = filename;
		this.newfilename = newfilename;
	}


	public FileBoard_DTO(String seq, String id, String title, String content, String regdate, String readcount,
			String filename, String newfilename) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.readcount = readcount;
		this.filename = filename;
		this.newfilename = newfilename;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getReadcount() {
		return readcount;
	}
	public void setReadcount(String readcount) {
		this.readcount = readcount;
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
		return "FileBoard_DTO [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + ", readcount=" + readcount + ", filename=" + filename + ", newfilename=" + newfilename
				+ "]";
	}
	
	

}
