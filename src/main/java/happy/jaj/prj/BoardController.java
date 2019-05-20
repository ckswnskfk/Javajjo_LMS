package happy.jaj.prj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import happy.jaj.prj.dtos.Empty_DTO;
import happy.jaj.prj.dtos.FileBoard_DTO;
import happy.jaj.prj.dtos.Notice_DTO;
import happy.jaj.prj.dtos.Room_Empty_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.model.Board_IService;

@Controller
public class BoardController {

	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private Board_IService board_IService;
	
	@RequestMapping(value="/notice_List.do", method=RequestMethod.GET)
	public String notice_Allselect(HttpServletRequest req) {
		logger.info("BoardController notice_Allselect 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<Notice_DTO> lists = board_IService.notice_Allselect(dto);
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	
	@RequestMapping(value="/notice_Detail.do", method=RequestMethod.GET)
	public String notice_oneselect(HttpServletRequest req) {
		logger.info("BoardController notice_oneselect 실행");
		String seq = req.getParameter("seq");
		Notice_DTO dto = board_IService.notice_oneselect(seq);
		req.setAttribute("dto", dto);
		return "jemin_index";
	}
	
	@RequestMapping(value="/notice_Write.do", method=RequestMethod.GET)
	public String notice_insert(HttpServletRequest req) {
		logger.info("BoardController notice_insert 실행");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id = req.getParameter("id");
		Notice_DTO dto = new Notice_DTO(title, content, id);
		board_IService.notice_insert(dto);
		return "jemin_index";
	}
	
	@RequestMapping(value="/notice_Search.do", method=RequestMethod.GET)
	public String notice_find(HttpServletRequest req) {
		logger.info("BoardController notice_find 실행");
		RowNum_DTO dto = new RowNum_DTO();
		String start = Integer.toString(dto.getStart());
		String last = Integer.toString(dto.getLast());
		String title = req.getParameter("title");
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", start);
		map.put("last", last);
		map.put("title", title);
		List<Notice_DTO> lists =board_IService.notice_find(map);
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	
	@RequestMapping(value="/notice_Readcount.do", method=RequestMethod.GET)
	public String notice_readcount(HttpServletRequest req) {
		logger.info("BoardController notice_readcount 실행");
		String seq = req.getParameter("seq");
		board_IService.notice_readcount(seq);
		return "jemin_index";
	}
	@RequestMapping(value="/file_infoboardlist.do", method=RequestMethod.GET)
	public String file_infoboardlist(HttpServletRequest req) {
		logger.info("BoardController file_infoboardlist 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<FileBoard_DTO> lists = board_IService.file_infoboardlist(dto);
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	@RequestMapping(value="/file_infodetailboard.do", method=RequestMethod.GET)
	public String file_infodetailboard(HttpServletRequest req) {
		logger.info("BoardController file_infodetailboard 실행");
		String seq = req.getParameter("seq");
		FileBoard_DTO dto = board_IService.file_infodetailboard(seq);
		req.setAttribute("dto", dto);
		return "jemin_index";
	}
	@RequestMapping(value="/file_infodeleteboard.do", method=RequestMethod.GET)
	public String file_infodeleteboard(HttpServletRequest req) {
		logger.info("BoardController file_infodeleteboard 실행");
		String[] seq = req.getParameterValues("seq");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("list", seq);
		board_IService.file_infodeleteboard(map);
		return "jemin_index";
	}
	@RequestMapping(value="/file_infomodifyboard.do", method=RequestMethod.GET)
	public String file_infomodifyboard(HttpServletRequest req) {
		logger.info("BoardController file_infomodifyboard 실행");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");
		FileBoard_DTO dto = new FileBoard_DTO(seq, title, content, "", "");
		board_IService.file_infomodifyboard(dto);
		return "jemin_index";
	}
	@RequestMapping(value="/file_infowriteboard.do", method=RequestMethod.GET)
	public String file_infowriteboard(HttpServletRequest req) {
		logger.info("BoardController file_infowriteboard 실행");
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String filename = req.getParameter("filename");
		String newfilename = req.getParameter("newfilename");
		FileBoard_DTO dto = new FileBoard_DTO(id, title, content, "", filename, newfilename);
		board_IService.file_infowriteboard(dto);
		return "jemin_index";
	}
	@RequestMapping(value="/file_infosearchboard.do", method=RequestMethod.GET)
	public String file_infosearchboard(HttpServletRequest req) {
		logger.info("BoardController file_infosearchboard 실행");
		RowNum_DTO dto = new RowNum_DTO();
		int start = dto.getStart();
		int last = dto.getLast();
		String title = req.getParameter("title");
		String id = req.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("last", last);
		map.put("title", title);
		map.put("id", id);
		List<FileBoard_DTO> lists = board_IService.file_infosearchboard(map);
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	@RequestMapping(value="/file_inforeadcount.do", method=RequestMethod.GET)
	public String file_inforeadcount(HttpServletRequest req) {
		logger.info("BoardController file_inforeadcount 실행");
		String seq = req.getParameter("seq");
		board_IService.file_inforeadcount(seq);
		return "jemin_index";
	}
	@RequestMapping(value="/room_boardlist.do", method=RequestMethod.GET)
	public String room_boardlist(HttpServletRequest req) {
		logger.info("BoardController room_boardlist 실행");
		List<Empty_DTO> lists = board_IService.room_boardlist();
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	@RequestMapping(value="/room_emptyboardlist.do", method=RequestMethod.GET)
	public String room_emptyboardlist(HttpServletRequest req) {
		logger.info("BoardController room_emptyboardlist 실행");
		String regdate = req.getParameter("regdate");
		List<Room_Empty_DTO> lists = board_IService.room_emptyboardlist(regdate);
		req.setAttribute("lists", lists);
		return "jemin_index";
	}
	@RequestMapping(value="/room_empty_check.do", method=RequestMethod.GET)
	public String room_empty_check(HttpServletRequest req) {
		logger.info("BoardController room_empty_check 실행");
		String code = req.getParameter("code");
		String id = req.getParameter("id");
		String regdate = req.getParameter("regdate");
		Room_Empty_DTO dto = new Room_Empty_DTO(code, id, regdate);
		String room = board_IService.room_empty_check(dto);
		req.setAttribute("room", room);
		return "jemin_index";
	}
	@RequestMapping(value="/room_empty_request.do", method=RequestMethod.GET)
	public String room_empty_request(HttpServletRequest req) {
		logger.info("BoardController room_empty_request 실행");
		String code = req.getParameter("code");
		String id = req.getParameter("id");
		String regdate = req.getParameter("regdate");
		Room_Empty_DTO dto = new Room_Empty_DTO(code, id, regdate);
		board_IService.room_empty_request(dto);
		return "jemin_index";
	}
	@RequestMapping(value="/room_empty_cancle.do", method=RequestMethod.GET)
	public String room_empty_cancle(HttpServletRequest req) {
		logger.info("BoardController room_empty_cancle 실행");
		String code = req.getParameter("code");
		String id = req.getParameter("id");
		String regdate = req.getParameter("regdate");
		Room_Empty_DTO dto = new Room_Empty_DTO(code, id, regdate);
		board_IService.room_empty_cancle(dto);
		return "jemin_index";
	}
	@RequestMapping(value="/room_add.do", method=RequestMethod.GET)
	public String room_add(HttpServletRequest req) {
		logger.info("BoardController room_add 실행");
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String personel = req.getParameter("personel");
		Empty_DTO dto = new Empty_DTO(code, name, personel);
		board_IService.room_add(dto);
		return "jemin_index";
	}
	
}
