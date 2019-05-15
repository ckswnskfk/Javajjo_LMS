package happy.jaj.prj.dtos;

public class Notice_DTO {

	private String seq;
	private String title;
	private String content;
	private String id;
	private String regdate;
	private String readcount;
	
	public Notice_DTO() {
	}

	public Notice_DTO(String seq, String title, String content, String id, String regdate, String readcount) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.id = id;
		this.regdate = regdate;
		this.readcount = readcount;
	}

	public Notice_DTO(String title, String content, String id) {
		super();
		this.title = title;
		this.content = content;
		this.id = id;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Notice_DTO [seq=" + seq + ", title=" + title + ", content=" + content + ", id=" + id + ", regdate="
				+ regdate + ", readcount=" + readcount + "]";
	}

	
}
