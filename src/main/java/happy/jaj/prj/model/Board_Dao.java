package happy.jaj.prj.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import happy.jaj.prj.dtos.Empty_DTO;
import happy.jaj.prj.dtos.FileBoard_DTO;
import happy.jaj.prj.dtos.Notice_DTO;
import happy.jaj.prj.dtos.Room_Empty_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;

@Repository
public class Board_Dao implements Board_Interface {

	private Logger logger = LoggerFactory.getLogger(Board_Dao.class);
	
	private final String NS_Not="happy.jaj.prj.NoticeBoard_Mapper.";
	private final String NS_Fil="happy.jaj.prj.FileBoard_Mapper.";
	private final String NS_Emp="happy.jaj.prj.EmptyBoard_Mapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 공지사항 전체 조회
	@Override
	public List<Notice_DTO> notice_Allselect(RowNum_DTO dto) {
		logger.info("notice_Allselect Dao 실행 {}", dto);
		return sqlSession.selectList(NS_Not+"notice_Allselect", dto);
	}

	// 공지사항 상세 조회
	@Override
	public Notice_DTO notice_oneselect(String seq) {
		logger.info("notice_oneselect Dao 실행 {}", seq);
		return sqlSession.selectOne(NS_Not+"notice_oneselect", seq);
	}

	// 공지사항 글 작성
	@Override
	public boolean notice_insert(Notice_DTO dto) {
		logger.info("notice_insert Dao 실행 {}", dto);
		return ((sqlSession.insert(NS_Not+"notice_insert", dto)) >0 );
	}
	
	// 공지사항 글 검색
	@Override
	public List<Notice_DTO> notice_find(Map<String, String> map) {
		logger.info("notice_find Dao 실행 {}", map);
		return sqlSession.selectList(NS_Not+"notice_find", map);
	}

	// 공지사항 조회수 증가
	@Override
	public boolean notice_readcount(String seq) {
		logger.info("notice_readcount Dao 실행 {}", seq);
		return ((sqlSession.update(NS_Not+"notice_readcount", seq)) > 0);
	}

	/* ----------------------- 자료 게시판 ---------------------*/
	// 자료게시판 전체 조회
	@Override
	public List<FileBoard_DTO> file_infoboardlist(RowNum_DTO dto) {
		logger.info("file_infoboardlist Dao 실행 {}", dto);
		return sqlSession.selectList(NS_Fil+"file_infoboardlist", dto);
	}

	// 자료게시판 상세 조회
	@Override
	public FileBoard_DTO file_infodetailboard(String seq) {
		logger.info("file_infodetailboard Dao 실행 {}", seq);
		return sqlSession.selectOne(NS_Fil+"file_infodetailboard", seq);
	}

	// 자료게시판 글 삭제
	@Override
	public boolean file_infodeleteboard(Map<String, String[]> map) {
		logger.info("file_infodeleteboard Dao 실행 {}", map);
		return ((sqlSession.delete(NS_Fil+"file_infodeleteboard", map)) > 0);
	}
	
	// 자료게시판 글 수정
	@Override
	public boolean file_infomodifyboard(FileBoard_DTO dto) {
		logger.info("file_infomodifyboard Dao 실행 {}", dto);
		return ((sqlSession.update(NS_Fil+"file_infomodifyboard", dto)) > 0);
	}

	//자료게시판 글 작성
	@Override
	public boolean file_infowriteboard(FileBoard_DTO dto) {
		logger.info("file_infowriteboard Dao 실행 {}", dto);
		return ((sqlSession.insert(NS_Fil+"file_infowriteboard", dto)) > 0);
	}

	// 자료게시판 글 검색
	@Override
	public List<FileBoard_DTO> file_infosearchboard(Map<String, Object> map) {
		logger.info("file_infosearchboard Dao 실행 {}", map);
		return sqlSession.selectList(NS_Fil+"file_infosearchboard", map);
	}

	// 자료게시판 조회수 증가
	@Override
	public boolean file_inforeadcount(String seq) {
		logger.info("file_inforeadcount Dao 실행 {}", seq);
		return ((sqlSession.update(NS_Fil+"file_inforeadcount", seq)) > 0);
	}

	/*------------------- 빈강의실 ---------------------*/
	// 강의실 조회
	@Override
	public List<Empty_DTO> room_boardlist() {
		logger.info("room_boardlist Dao 실행");
		return sqlSession.selectList(NS_Emp+"room_boardlist");
	}
	
	// 강의실 상세 조회
	@Override
	public Empty_DTO room_detailboardlist(String code) {
		logger.info("room_detailboardlist Dao 실행");
		return sqlSession.selectOne(NS_Emp+"room_detailboardlist",code);
	}

	// 빈 강의실 조회
	@Override
	public int room_emptyboardlist(Map<String, String> map) {
		logger.info("room_emptyboardlist Dao 실행 {}", map);
		return sqlSession.selectOne(NS_Emp+"room_emptyboardlist", map);
	}

	// 빈강의실 예약 확인
	@Override
	public String room_empty_check(Room_Empty_DTO dto) {
		logger.info("room_empty_check Dao 실행 {}", dto);
		return sqlSession.selectOne(NS_Emp+"room_empty_check", dto);
	}

	// 빈 강의실 예약
	@Override
	public boolean room_empty_request(Room_Empty_DTO dto) {
		logger.info("room_empty_request Dao 실행 {}", dto);
		return ((sqlSession.insert(NS_Emp+"room_empty_request", dto)) > 0);
	}

	// 빈 강의실 예약 취소
	@Override
	public boolean room_empty_cancle(Room_Empty_DTO dto) {
		logger.info("room_empty_cancle Dao 실행 {}", dto);
		return ((sqlSession.delete(NS_Emp+"room_empty_cancle", dto)) > 0);
	}

	// 강의실 추가
	@Override
	public boolean room_add(Empty_DTO dto) {
		logger.info("room_add Dao 실행 {}", dto);
		return ((sqlSession.insert(NS_Emp+"room_add", dto)) > 0 );
	}

}
