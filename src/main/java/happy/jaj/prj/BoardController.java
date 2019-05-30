package happy.jaj.prj;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import happy.jaj.prj.dtos.Empty_DTO;
import happy.jaj.prj.dtos.FileBoard_DTO;
import happy.jaj.prj.dtos.Notice_DTO;
import happy.jaj.prj.dtos.Room_Empty_DTO;
import happy.jaj.prj.dtos.RowNum_DTO;
import happy.jaj.prj.dtos.Student_DTO;
import happy.jaj.prj.model.Board_IService;
import happy.jaj.prj.model.User_IService;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
public class BoardController {

	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private Board_IService board_IService;
	
	@Resource(name="uploadPath")
	String uploadPath;
	
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
	public String file_infowriteboard(FileBoard_DTO dto, MultipartHttpServletRequest mtReq) throws IOException {
		logger.info("BoardController file_infowriteboard 실행");
		
		
		MultipartFile reqFilename = mtReq.getFile("originalfilename");
		String filename = reqFilename.getOriginalFilename();
		String newfilename = "";
		
		// 첨부 파일이 없으면 바로 글 작성
		if(filename.trim().equalsIgnoreCase("")) {
			dto.setFilename(filename);
			dto.setNewfilename(newfilename);
			board_IService.file_infowriteboard(dto);
		}else {
			//이름이 겹치지 않기 위해 고유한 랜덤값을 추가한 파일 이름 생성
			UUID uuid = UUID.randomUUID();
			Date form = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			String today = transFormat.format(form);
			
			newfilename = uuid.toString()+"_"+today+"_"+dto.getId()+"_"+filename;
			
			File dir = new File(uploadPath);
			File target = new File(uploadPath, newfilename);
			
			// 폴더가 없다면 폴더를 생성
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			// 파일을 서버에 저장
			FileCopyUtils.copy(reqFilename.getBytes(), target);
			
			// 받아온 dto에는 파일 이름이 없기 때문에 직접 넣어주고 dao 실행
			dto.setFilename(filename);
			dto.setNewfilename(newfilename);
			board_IService.file_infowriteboard(dto);;
						
		}
		return "redirect:/file_infoboardlist.do";
	}
	
	// 상세조회한 신청서에서 첨부파일 다운
	@RequestMapping(value="/file_infodownload.do", method=RequestMethod.GET)
	public ModelAndView download(String newfilename) {
		String fullPath = uploadPath+"\\"+newfilename;
		File file = new File(fullPath);
		// download라는 id를 가진 Bean에 downloadFile 이라는 이름의 file을 전달
		return new ModelAndView("download","downloadFile",file);
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
	public String room_emptyboardlist(String regdate, Model model, Room_Empty_DTO Rdto) {
		logger.info("BoardController room_emptyboardlist 실행");
		Map<String, String> map = new HashMap<String,String>();
		
		// 강의실 목록 확인
		List<Empty_DTO> lists = board_IService.room_boardlist();
		
		// 강의실 별 남은 수용인원 계산
		for(int i = 0 ; i < lists.size();i++) {
			Empty_DTO dto = lists.get(i);
			map.put("code", dto.getCode());
			map.put("regdate", regdate);
			int count = board_IService.room_emptyboardlist(map);
			int personel = (int)Integer.parseInt(dto.getPersonel());
			personel = personel-count;
			String personelStr = String.valueOf(personel);
			dto.setPersonel(personelStr);
			
			
		// 빈강의실 예약 확인 - Y면 예약 가능 N이면 예약 불가
			Rdto.setCode(dto.getCode());
			String room = board_IService.room_empty_check(Rdto);
			if(room == null || room =="") {
				room = "Y";
			}else {
				room = "N";
			}
			dto.setCheck(room);
			
			lists.set(i, dto);
		}
		
		model.addAttribute("lists", lists);
		model.addAttribute("regdate", regdate);
		return "room_EmptyBoardList";
	}
	
	@RequestMapping(value="/room_empty_request.do", method=RequestMethod.GET)
	public String room_empty_request(Room_Empty_DTO dto, Model model) {
		logger.info("BoardController room_empty_request 실행");
		
		Empty_DTO edto = board_IService.room_detailboardlist(dto.getCode());
		
		String api_key = "NCSBBEI5PLLEXGD2";
		String api_secret = "OFVHZ33HQYWVQSCCZRWTYRWZNE8ZT1LU";
		
		Message coolsms = new Message(api_key, api_secret);
		HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", dto.getId()); // 수신번호
	    params.put("from", "01065491058");
	    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
	    params.put("text", "[JAJ]빈 강의실 예약이 완료 되었습니다. \n 강의실 이름 : "+edto.getName()
	    		+ "\n날짜 : "+dto.getRegdate()); // 문자내용    
	    params.put("app_version", "JAVA SDK v1.2"); // application name and version
		boolean isc = board_IService.room_empty_request(dto);
		if(isc) {
			logger.info("강의실 예약 "+isc);
			try {
				JSONObject result = coolsms.send(params);
				if ((long)result.get("success_count") > 0) {
			          // 메시지 보내기 성공 및 전송결과 출력
			          System.out.println("성공");            
			          System.out.println("group_id : "+result.get("group_id")); // 그룹아이디
			          System.out.println("result_code : "+result.get("result_code")); // 결과코드
			          System.out.println("result_message"+result.get("result_message"));  // 결과 메시지
			          System.out.println("success_count"+result.get("success_count")); // 메시지아이디
			          System.out.println("error_count"+result.get("error_count"));  // 여러개 보낼시 오류난 메시지 수
			      } else {
			          // 메시지 보내기 실패
			          System.out.println("실패");
			          System.out.println(result.get("code")); // REST API 에러코드
			          System.out.println(result.get("message")); // 에러메시지
			      } 
			} catch (CoolsmsException e) {
				e.printStackTrace();
			}
		}
		
		
		model.addAttribute("regdate", dto.getRegdate());
		model.addAttribute("id", dto.getId());
		return "redirect:/room_emptyboardlist.do";
	}
	@RequestMapping(value="/room_empty_cancle.do", method=RequestMethod.GET)
	public String room_empty_cancle(Room_Empty_DTO dto, Model model) {
		logger.info("BoardController room_empty_cancle 실행");
		
		Empty_DTO edto = board_IService.room_detailboardlist(dto.getCode());
		
		String api_key = "NCSBBEI5PLLEXGD2";
		String api_secret = "OFVHZ33HQYWVQSCCZRWTYRWZNE8ZT1LU";
		
		Message coolsms = new Message(api_key, api_secret);
		HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", dto.getId()); // 수신번호
	    params.put("from", "01065491058");
	    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
	    params.put("text", "[JAJ]빈 강의실 예약취소가 완료 되었습니다. \n 강의실 이름 : "+edto.getName()
		+ "\n날짜 : "+dto.getRegdate()); // 문자내용        
	    params.put("app_version", "JAVA SDK v1.2"); // application name and version
		boolean isc = board_IService.room_empty_cancle(dto);
		if(isc) {
			logger.info("강의실 예약 "+isc);
			try {
				JSONObject result = coolsms.send(params);
				if ((long)result.get("success_count") > 0) {
			          // 메시지 보내기 성공 및 전송결과 출력
			          System.out.println("성공");            
			          System.out.println("group_id : "+result.get("group_id")); // 그룹아이디
			          System.out.println("result_code : "+result.get("result_code")); // 결과코드
			          System.out.println("result_message"+result.get("result_message"));  // 결과 메시지
			          System.out.println("success_count"+result.get("success_count")); // 메시지아이디
			          System.out.println("error_count"+result.get("error_count"));  // 여러개 보낼시 오류난 메시지 수
			      } else {
			          // 메시지 보내기 실패
			          System.out.println("실패");
			          System.out.println(result.get("code")); // REST API 에러코드
			          System.out.println(result.get("message")); // 에러메시지
			      } 
			} catch (CoolsmsException e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("regdate", dto.getRegdate());
		model.addAttribute("id", dto.getId());
		return "redirect:/room_emptyboardlist.do";
	}
	@RequestMapping(value="/room_add.do", method=RequestMethod.POST)
	public String room_add(Empty_DTO dto) {
		logger.info("BoardController room_add 실행");
		board_IService.room_add(dto);
		return "redirect:/room_main.do";
	}
	
}
