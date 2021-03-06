package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happy.jaj.prj.dtos.Empty_DTO;
import happy.jaj.prj.dtos.FileBoard_DTO;
import happy.jaj.prj.dtos.Notice_DTO;
import happy.jaj.prj.dtos.Room_Empty_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;

@Service
public class Board_Service implements Board_IService {

	private Logger logger = LoggerFactory.getLogger(Board_Service.class);
	
	@Autowired
	private Board_Interface board_interface;
	
	// 공지사항 전체 조회
	@Override
	public List<Notice_DTO> notice_Allselect(RowNum_DTO dto) {
		logger.info("notice_Allselect Service 실행 {}", dto);
		return board_interface.notice_Allselect(dto);
	}
	
	// 공지사항 갯수 조회
	@Override
	public int notice_Allselect_count() {
		logger.info("notice_Allselect_count Service 실행 {}");
		return board_interface.notice_Allselect_count();
	}

	// 공지사항 상세 조회
	@Override
	public Notice_DTO notice_oneselect(String seq) {
		logger.info("notice_oneselect Service 실행 {}", seq);
		return board_interface.notice_oneselect(seq);
	}

	// 공지사항 글 작성
	@Override
	public boolean notice_insert(Notice_DTO dto) {
		logger.info("notice_insert Service 실행 {}", dto);
		return board_interface.notice_insert(dto);
	}

	// 공지사항 글 검색
	@Override
	public List<Notice_DTO> notice_find(Map<String, String> map) {
		logger.info("notice_find Service 실행 {}", map);
		return board_interface.notice_find(map);
	}
	
	// 공지사항 글 검색 갯수 조회
	@Override
	public int notice_find_count(String title) {
		logger.info("notice_find_count Service 실행 {}", title);
		return board_interface.notice_find_count(title);
	}

	// 공지사항 조회수 증가
	@Override
	public boolean notice_readcount(String seq) {
		logger.info("notice_readcount Service 실행 {}", seq);
		return board_interface.notice_readcount(seq);
	}

	/* ----------------------- 자료 게시판 ---------------------*/
	// 자료게시판 전체 조회
	@Override
	public List<FileBoard_DTO> file_infoboardlist(RowNum_DTO dto) {
		logger.info("file_infoboardlist Service 실행 {}", dto);
		return board_interface.file_infoboardlist(dto);
	}
	
	// 자료게시판 전체 조회 갯수
	@Override
	public int file_infoboardlist_count() {
		logger.info("file_infoboardlist Service 실행 {}");
		return board_interface.file_infoboardlist_count();
	}
	
	// 자료게시판 글 검색 갯수
	@Override
	public int file_infosearchboard_count(Map<String, Object> map) {
		logger.info("file_infosearchboard Service 실행 {}", map);
		return board_interface.file_infosearchboard_count(map);
	}
	
	// 자료게시판 상세 조회
	@Override
	public FileBoard_DTO file_infodetailboard(String seq) {
		logger.info("file_infodetailboard Service 실행 {}", seq);
		return board_interface.file_infodetailboard(seq);
	}

	// 자료게시판 글 삭제
	@Override
	public boolean file_infodeleteboard(Map<String, String[]> map) {
		logger.info("file_infodeleteboard Service 실행 {}", map);
		return board_interface.file_infodeleteboard(map);
	}

	// 자료게시판 글 수정
	@Override
	public boolean file_infomodifyboard(FileBoard_DTO dto) {
		logger.info("file_infomodifyboard Service 실행 {}", dto);
		return board_interface.file_infomodifyboard(dto);
	}

	//자료게시판 글 작성
	@Override
	public boolean file_infowriteboard(FileBoard_DTO dto) {
		logger.info("file_infowriteboard Service 실행 {}", dto);
		return board_interface.file_infowriteboard(dto);
	}

	// 자료게시판 글 검색
	@Override
	public List<FileBoard_DTO> file_infosearchboard(Map<String, Object> map) {
		logger.info("file_infosearchboard Service 실행 {}", map);
		return board_interface.file_infosearchboard(map);
	}
	
	// 자료게시판 조회수 증가
	@Override
	public boolean file_inforeadcount(String seq) {
		logger.info("file_inforeadcount Service 실행 {}", seq);
		return board_interface.file_inforeadcount(seq);
	}

	/*------------------- 빈강의실 ---------------------*/
	// 강의실 조회
	@Override
	public List<Empty_DTO> room_boardlist() {
		logger.info("room_boardlist Service 실행");
		return board_interface.room_boardlist();
	}
	
	// 강의실  상세 조회
	@Override
	public Empty_DTO room_detailboardlist(String code) {
		logger.info("room_detailboardlist Service 실행");
		return board_interface.room_detailboardlist(code);
	}

	// 빈 강의실 조회
	@Override
	public int room_emptyboardlist(Map<String, String> map) {
		logger.info("room_emptyboardlist Service 실행 {}", map);
		return board_interface.room_emptyboardlist(map);
	}

	// 빈강의실 예약 확인
	@Override
	public String room_empty_check(Room_Empty_DTO dto) {
		logger.info("room_empty_check Service 실행 {}", dto);
		return board_interface.room_empty_check(dto);
	}

	// 빈 강의실 예약
	@Override
	public boolean room_empty_request(Room_Empty_DTO dto) {
		logger.info("room_empty_request Service 실행 {}", dto);
		return board_interface.room_empty_request(dto);
	}

	// 빈 강의실 예약 취소
	@Override
	public boolean room_empty_cancle(Room_Empty_DTO dto) {
		logger.info("room_empty_cancle Service 실행 {}", dto);
		return board_interface.room_empty_cancle(dto);
	}

	// 강의실 추가
	@Override
	public boolean room_add(Empty_DTO dto) {
		logger.info("room_add Service 실행 {}", dto);
		return board_interface.room_add(dto);
	}
	
	// 빈강의실 캘린더 이벤트 추가용 날짜와 코드 조회
	public List<Room_Empty_DTO> room_event(String id) {
		logger.info("room_add Service 실행 {}", id);
		return board_interface.room_event(id);
	}

}
