package happy.jaj.prj;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import happy.jaj.prj.dtos.Admin_DTO;
import happy.jaj.prj.dtos.App_Form_DTO;
import happy.jaj.prj.dtos.Course_DTO;
import happy.jaj.prj.model.Absent_IService;

@Controller
public class AbsentController {

	private Logger logger = LoggerFactory.getLogger(AbsentController.class);
	
	@Autowired
	private Absent_IService absent_IService;
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	// 완성
	// 리스트 폼으로 이동
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/absentListForm.do", method=RequestMethod.GET)
	public String absent_List_Form(HttpSession session, Model model) {
		logger.info("AbsentController absent_List_Form 실행");
		Map<String, String> lmap = (Map<String, String>) session.getAttribute("member");
		lmap.put("stm", "N");
		if (lmap.get("table").trim().equalsIgnoreCase("Student")) {
			List<App_Form_DTO> list = absent_IService.student_absent_list(lmap);
			JSONArray jArray = new JSONArray();
			JSONObject data = null;
			for (int i = 0; i < list.size(); i++) {
				data = new JSONObject();
				data.put("form_seq", list.get(i).getForm_seq());
				data.put("app_date", list.get(i).getApp_date());
				data.put("process_date", list.get(i).getProcess_date());
				data.put("coursecode", list.get(i).getCoursecode());
				data.put("coursename", list.get(i).getCoursename());
				data.put("stm", list.get(i).getStm());
				data.put("start_date", list.get(i).getStart_date());
				data.put("recipient_id", list.get(i).getRecipient_id());
				data.put("absent_days", list.get(i).getAbsent_days());
				
				jArray.add(data);
			}
			model.addAttribute("jArray", jArray);
		} else {
			List<App_Form_DTO> list = absent_IService.recipient_absent_list(lmap);
			JSONArray jArray = new JSONArray();
			JSONObject data = null;
			for (int i = 0; i < list.size(); i++) {
				data = new JSONObject();
				data.put("form_seq", list.get(i).getForm_seq());
				data.put("app_date", list.get(i).getApp_date());
				data.put("process_date", list.get(i).getProcess_date());
				data.put("student_name", list.get(i).getStudent_name());
				data.put("coursecode", list.get(i).getCoursecode());
				data.put("coursename", list.get(i).getCoursename());
				data.put("stm", list.get(i).getStm());
				data.put("start_date", list.get(i).getStart_date());
				data.put("recipient_id", list.get(i).getRecipient_id());
				data.put("absent_days", list.get(i).getAbsent_days());
				
				jArray.add(data);
			}
			model.addAttribute("jArray", jArray);
		}
		return "absent";
	}
	
	// 완성
	// 자신의 신청내역 리스트 상태별로 조회(학생) ajax
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/absentList.do", method=RequestMethod.POST, produces="application/text; charset=UTF-8")
	public @ResponseBody String student_absent_list(@RequestBody String stm, HttpSession session) {
		logger.info("AbsentController student_absent_list 실행 {}", stm);
		Map<String, String> lmap = (Map<String, String>) session.getAttribute("member");
		System.out.println(stm);
		String inStm = stm.substring(0, 1);
		System.out.println(inStm);
		lmap.put("stm", inStm);
		List<App_Form_DTO> list = absent_IService.student_absent_list(lmap);
		System.out.println(list.toString());
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONObject data = null;
		for (int i = 0; i < list.size(); i++) {
			data = new JSONObject();
			data.put("form_seq", list.get(i).getForm_seq());
			data.put("app_date", list.get(i).getApp_date());
			data.put("process_date", list.get(i).getProcess_date());
			data.put("coursecode", list.get(i).getCoursecode());
			data.put("coursename", list.get(i).getCoursename());
			data.put("stm", list.get(i).getStm());
			data.put("start_date", list.get(i).getStart_date());
			data.put("recipient_id", list.get(i).getRecipient_id());
			data.put("absent_days", list.get(i).getAbsent_days());
			
			jArray.add(data);
		}
		System.out.println("===="+jArray);
		
		json.put("lists", jArray);
		System.out.println(json);
		System.out.println(json.toJSONString());
		return json.toJSONString();
	}
	
	// 완성
	// 결석신청 폼으로
	@RequestMapping(value="/to_app_form.do", method=RequestMethod.GET)
	public String to_App_Form() {
		logger.info("AbsentController to_App_Form 실행");
		return "app_Form";
	}
	
	// 완성
	// 클릭해서 각 신청을 상세조회(승인)
	@RequestMapping(value="/absent_detail_yes.do", method=RequestMethod.GET)
	public String absent_detail_yes(@RequestParam Map<String, String> map, HttpServletRequest req) {
		logger.info("AbsentController absent_detail_yes 실행");
		Map<String, Object> yesMap = absent_IService.absent_detail_yes(map);
		System.out.println(yesMap);
		req.setAttribute("yesMap", yesMap);
		return "absent_detail";
	}
	
	// 상세조회한 신청서에서 첨부파일 다운
	@RequestMapping(value="/download.do", method=RequestMethod.GET)
	public ModelAndView download(HttpServletRequest req, HttpServletResponse resp, String newfilename) {
		String fullPath = uploadPath+"\\"+newfilename;
		File file = new File(fullPath);
		// download라는 id를 가진 Bean에 downloadFile 이라는 이름의 file을 전달
		return new ModelAndView("download","downloadFile",file);
	}

	// 승인으로 통합해서 사용
//	// 클릭해서 각 신청을 상세조회(반려시)
//	@RequestMapping(value="/absent_detail_return.do", method=RequestMethod.GET)
//	public String absent_detail_return(HttpServletRequest req) {
//		logger.info("AbsentController absent_detail_return 실행");
//		String seq = req.getParameter("seq");
//		App_Form_DTO dto = absent_IService.absent_detail_return(seq);
//		req.setAttribute("dto", dto);
//		return "absent_detail";
//	}
	
	// 완성
	// 강사, 관리자가 자신의 과정의 학생들것만의 내역서 리스트 보기
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/recipient_absent_list.do", method=RequestMethod.POST, produces="application/text; charset=UTF-8")
	public @ResponseBody String recipient_absent_list(@RequestBody String stm, HttpSession session) {
		logger.info("AbsentController recipient_absent_list 실행");
		Map<String, String> lmap = (Map<String, String>) session.getAttribute("member");
		System.out.println(stm);
		String inStm = stm.substring(0, 1);
		System.out.println(inStm);
		lmap.put("stm", inStm);
		List<App_Form_DTO> list = absent_IService.recipient_absent_list(lmap);
		System.out.println(list);
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONObject data = null;
		for (int i = 0; i < list.size(); i++) {
			data = new JSONObject();
			data.put("form_seq", list.get(i).getForm_seq());
			data.put("app_date", list.get(i).getApp_date());
			data.put("process_date", list.get(i).getProcess_date());
			data.put("student_name", list.get(i).getStudent_name());
			data.put("coursecode", list.get(i).getCoursecode());
			data.put("coursename", list.get(i).getCoursename());
			data.put("stm", list.get(i).getStm());
			data.put("start_date", list.get(i).getStart_date());
			data.put("recipient_id", list.get(i).getRecipient_id());
			data.put("absent_days", list.get(i).getAbsent_days());
			
			jArray.add(data);
		}
		
		
		System.out.println(jArray);
		json.put("lists", jArray);
		System.out.println(json);
		return json.toJSONString();
	}
	
	// 완성
	// 결석 신청
	// 결석 신청하려는 과정을 선택
	@RequestMapping(value="/absent_course.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Course_DTO> absent_course(@RequestBody String id) {
		logger.info("AbsentController absent_course 실행");
		System.out.println(id);
		String inid = id.substring(0, 11);
		System.out.println(inid);
		List<Course_DTO> list = absent_IService.absent_course(inid);
		System.out.println("list = " + list);
		Course_DTO dto = null;
		Map<String, Course_DTO> mapp = new HashMap<String, Course_DTO>();
		for (int i = 0; i < list.size(); i++) {
			dto = new Course_DTO();
			dto = list.get(i);
			mapp.put("dto"+i, dto);
		}
		System.out.println("mapp = " + mapp);
		return mapp;
	}
	
//	// 관리자를 선택 (관리자 한 명이라 안쓸것 같음)
//	@RequestMapping(value="/absent_admin.do", method=RequestMethod.GET)
//	public String absent_admin(HttpServletRequest req) {
//		logger.info("AbsentController absent_admin 실행");
//		Admin_DTO dto = absent_IService.absent_admin();
//		req.setAttribute("dto", dto);
//		return "chanju_index";
//	}
	
	// 완성
	// 결석 신청
	@RequestMapping(value="/insert_absent_form.do", method=RequestMethod.POST)
	public String insert_absent_form(App_Form_DTO dto, MultipartHttpServletRequest mtReq) throws IOException {
		logger.info("AbsentController insert_absent_form 실행");
//		String contextRoot = new HttpServletRequestWrapper(mtReq).getRealPath("/");
//		System.out.println(contextRoot);
		String studentId = dto.getStudent_id();
		MultipartFile reqFilename = mtReq.getFile("originalfilename");
		int n;
		String filename = reqFilename.getOriginalFilename();
		String newfilename = "";
		
		// 첨부한 파일 없으면 바로 결석 신청
		if (filename.trim().equalsIgnoreCase("")) {
			n = absent_IService.insert_absent_form(dto);
			System.out.println(n);
		} else {
			// 이름 겹치지 않기 위해 고유한 랜덤값을 추가한 파일 이름 생성
			UUID uuid = UUID.randomUUID();
			Date from = new Date();
			SimpleDateFormat transFormat  = new SimpleDateFormat("yyyy-MM-dd");
			String today = transFormat.format(from);

			newfilename = uuid.toString()+"_"+today+"_"+studentId+"_"+filename;
			
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
			n = absent_IService.insert_absent_form(dto);
		}
		return "redirect:/absentListForm.do";
	}

	
	// 강사 및 관리자가 미승인 사유를 작성+동시에 처리일 업데이트+동시에 승인 여부 수정
	@RequestMapping(value="/insert_unapprove_reason.do", method=RequestMethod.POST)
	public String insert_unapprove_reason(@RequestParam Map<String, String> map) {
		logger.info("AbsentController insert_unapprove_reason 실행");
		int n = absent_IService.insert_unapprove_reason(map);
		System.out.println(n);
		return "redirect:/absentListForm.do";
	}
	
	// 강사 및 관리자가 승인을 함
	@RequestMapping(value="/update_is_approve_Yes.do", method=RequestMethod.GET)
	public String update_is_approve_Yes(@RequestParam Map<String, String> map) {
		logger.info("AbsentController update_is_approve_Yes 실행");
		int n = absent_IService.update_is_approve_Yes(map);
		System.out.println(n);
		return "redirect:/absentListForm.do";
	}
	
	@RequestMapping(value="/chkSignature.do", method=RequestMethod.POST)
	@ResponseBody
	public int chk_signature(@RequestBody String id) {
		logger.info("AbsentController chk_signature 실행");
		String inid = id.substring(0, 11);
		return absent_IService.chk_signature(inid);
	}
	
	@RequestMapping(value="/addSignature.do", method=RequestMethod.POST)
	public String add_signature(@RequestParam Map<String, String> map, MultipartHttpServletRequest mtReq) throws IOException {
		logger.info("AbsentController add_signature 실행");
		MultipartFile reqFilename = mtReq.getFile("originalfilename");
		String signature_id = map.get("id");
		String filename = reqFilename.getOriginalFilename();
		String newfilename = "";
		String signaturePath = "C:\\Users\\ChanJu\\git\\Javajjo_LMS\\src\\main\\webapp\\upload\\signature";
		// 이름 겹치지 않기 위해 고유한 랜덤값을 추가한 파일 이름 생성
		UUID uuid = UUID.randomUUID();
		newfilename = uuid.toString()+"_"+signature_id+"_"+filename;
		
		File dir = new File(signaturePath);
		File target = new File(signaturePath, newfilename);
		
		// 폴더가 없다면 폴더를 생성
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		// 파일을 서버에 저장
		FileCopyUtils.copy(reqFilename.getBytes(), target);
		map.put("filename", filename);
		map.put("newfilename", newfilename);
		
		int n = absent_IService.add_signature(map);
		System.out.println(n);
		
		return "redirect:/absentListForm.do";
	}
}
