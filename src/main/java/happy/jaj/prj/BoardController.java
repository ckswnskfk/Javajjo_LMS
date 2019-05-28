package happy.jaj.prj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value="/notice_list.do", method=RequestMethod.GET)
	public String notice_Allselect(Model model) {
		logger.info("BoardController notice_Allselect 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<Notice_DTO> lists = board_IService.notice_Allselect(dto);
		model.addAttribute("lists", lists);
		return "notice_List";
	}
	
	@RequestMapping(value="/notice_detail.do", method=RequestMethod.GET)
	public String notice_oneselect(String seq, Model model) {
		logger.info("BoardController notice_oneselect 실행");
		Notice_DTO dto = board_IService.notice_oneselect(seq);
		boolean isc =board_IService.notice_readcount(seq);
		if(isc) {
			model.addAttribute("dto", dto);
		}
		return "notice_Detail";
	}
	
	@RequestMapping(value="/notice_form.do", method=RequestMethod.GET)
	public String notice_form() {
		logger.info("BoardController notice_form 실행");
		return "notice_Form";
	}
	
	@RequestMapping(value="/notice_write.do", method=RequestMethod.POST)
	public String notice_insert(Notice_DTO dto, Model model) {
		logger.info("BoardController notice_insert 실행");
		boolean isc = board_IService.notice_insert(dto);
		if(isc) {
			logger.info("notice_insert 성공");
		}
		return "redirect:/notice_list.do";
	}
	
	
	@RequestMapping(value="/notice_search.do", method=RequestMethod.GET)
	public String notice_find(@RequestParam Map<String, String> map, Model model) {
		logger.info("BoardController notice_find 실행");
		RowNum_DTO dto = new RowNum_DTO();
		map.put("start", String.valueOf(dto.getStart()));
		map.put("last", String.valueOf(dto.getLast()));
		List<Notice_DTO> lists =board_IService.notice_find(map);
		model.addAttribute("lists", lists);
		return "notice_List";
	}
	
	@RequestMapping(value="/file_infoboardlist.do", method=RequestMethod.GET)
	public String file_infoboardlist(Model model) {
		logger.info("BoardController file_infoboardlist 실행");
		RowNum_DTO dto = new RowNum_DTO();
		List<FileBoard_DTO> lists = board_IService.file_infoboardlist(dto);
		model.addAttribute("lists", lists);
		return "file_BoardList";
	}
	@RequestMapping(value="/file_infodetailboard.do", method=RequestMethod.GET)
	public String file_infodetailboard(String seq, Model model) {
		logger.info("BoardController file_infodetailboard 실행");
		FileBoard_DTO dto = board_IService.file_infodetailboard(seq);
		boolean isc = board_IService.file_inforeadcount(seq);
		if(isc) {
			model.addAttribute("dto", dto);
		}
		return "file_DetailBoard";
	}
	@RequestMapping(value="/file_infodeleteboard.do", method=RequestMethod.GET)
	public String file_infodeleteboard(String[] seq, Model model) {
		logger.info("BoardController file_infodeleteboard 실행");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("list", seq);
		boolean isc = board_IService.file_infodeleteboard(map);
		if(isc) {
			logger.info("file_infodeleteboard 성공");
		}
		return "redirect:/file_infoboardlist.do";
	}
	
	@RequestMapping(value="/file_infomodifyboardform.do", method=RequestMethod.POST)
	public String file_infomodifyboardform(Model model, String seq) {
		logger.info("BoardController file_infomodifyboardform 실행");
		FileBoard_DTO dto = board_IService.file_infodetailboard(seq);
		model.addAttribute("dto", dto);
		return "file_ModifyBoard";
	}
	
	@RequestMapping(value="/file_infomodifyboard.do", method=RequestMethod.POST)
	public String file_infomodifyboard(FileBoard_DTO dto) {
		logger.info("BoardController file_infomodifyboard 실행");
		board_IService.file_infomodifyboard(dto);
		return "redirect:/file_infoboardlist.do";
	}
	
	@RequestMapping(value="/file_infowriteboardform.do", method=RequestMethod.GET)
	public String file_infowriteboardform() {
		logger.info("BoardController file_infowriteboardform 실행");
		return "file_WriteBoard";
	}
	
	@RequestMapping(value="/file_infowriteboard.do", method=RequestMethod.POST)
	public String file_infowriteboard(FileBoard_DTO dto) {
		logger.info("BoardController file_infowriteboard 실행");
		boolean isc = board_IService.file_infowriteboard(dto);
		if(isc) {
			logger.info("file_infowriteboard 성공");
		}
		return "redirect:/file_infoboardlist.do";
	}
	@RequestMapping(value="/file_infosearchboard.do", method=RequestMethod.GET)
	public String file_infosearchboard(@RequestParam Map<String, Object> map, Model model) {
		logger.info("BoardController file_infosearchboard 실행");
		RowNum_DTO dto = new RowNum_DTO();
		int start = dto.getStart();
		int last = dto.getLast();
		map.put("start", start);
		map.put("last", last);
		List<FileBoard_DTO> lists = board_IService.file_infosearchboard(map);
		model.addAttribute("lists", lists);
		return "file_BoardList";
	}
	
	@RequestMapping(value="/room_main.do", method=RequestMethod.GET)
	public String room_main(Model model) {
		logger.info("BoardController room_main 실행");
		List<Empty_DTO> lists = board_IService.room_boardlist();
		model.addAttribute("lists", lists);
		return "room_Main";
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
	@RequestMapping(value="/room_add.do", method=RequestMethod.POST)
	public String room_add(Empty_DTO dto) {
		logger.info("BoardController room_add 실행");
		board_IService.room_add(dto);
		return "redirect:/room_main.do";
	}
	
}
