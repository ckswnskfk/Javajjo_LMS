package happy.jaj.prj;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import happy.jaj.prj.dtos.Member_DTO;
import happy.jaj.prj.model.Member_IService;

@Controller
public class TestController {

	private Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private Member_IService iMember;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Controller login {} ");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		Member_DTO dto = iMember.loginMember(map);
		if(dto != null) {
			return "main";
		}
		return "dd";
	}

	
}
