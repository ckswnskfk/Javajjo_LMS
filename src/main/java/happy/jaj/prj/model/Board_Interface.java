package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import happy.jaj.prj.dtos.Empty_DTO;
import happy.jaj.prj.dtos.FileBoard_DTO;
import happy.jaj.prj.dtos.Notice_DTO;
import happy.jaj.prj.dtos.Room_Empty_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;

public interface Board_Interface {

	// 공지사항 전체 조회
	public List<Notice_DTO> notice_Allselect(RowNum_DTO dto);

	// 공지사항 글 검색 갯수 조회
	public int notice_Allselect_count();
	
	// 공지사항 상세 조회
	public Notice_DTO notice_oneselect(String seq);
	
	// 공지사항 글 작성
	public boolean notice_insert(Notice_DTO dto);
	
	// 공지사항 글 검색
	public List<Notice_DTO> notice_find(Map<String, String> map);
	
	// 공지사항 글 검색 갯수 조회
	public int notice_find_count(String title);
	
	// 공지사항 조회수 증가
	public boolean notice_readcount(String seq);
	
	// 자료게시판 전체 조회
	public List<FileBoard_DTO> file_infoboardlist(RowNum_DTO dto);
	
	// 자료게시판 전체 조회 갯수
	public int file_infoboardlist_count();
	
	// 자료게시판 글 검색 갯수
	public int file_infosearchboard_count(Map<String, Object> map);
	
	// 자료게시판 상세 조회
	public FileBoard_DTO file_infodetailboard(String seq);
	
	// 자료게시판 글 삭제
	public boolean file_infodeleteboard(Map<String, String[]> map);
	
	// 자료게시판 글 수정
	public boolean file_infomodifyboard(FileBoard_DTO dto);
	
	//자료게시판 글 작성
	public boolean file_infowriteboard(FileBoard_DTO dto);
	
	// 자료게시판 글 검색
	public List<FileBoard_DTO> file_infosearchboard(Map<String, Object> map);
	
	// 자료게시판 조회수 증가
	public boolean file_inforeadcount(String seq);
	
	// 강의실 조회
	public List<Empty_DTO> room_boardlist();
	
	// 강의실 상세 조회
	public Empty_DTO room_detailboardlist(String code);
	
	// 빈 강의실 조회
	public int room_emptyboardlist(Map<String, String> map);
	
	// 빈강의실 예약 확인
	public String room_empty_check(Room_Empty_DTO dto);
	
	// 빈 강의실 예약
	public boolean room_empty_request(Room_Empty_DTO dto);
	
	// 빈 강의실 예약 취소
	public boolean room_empty_cancle(Room_Empty_DTO dto);
	
	// 강의실 추가
	public boolean room_add(Empty_DTO dto);
	
}
