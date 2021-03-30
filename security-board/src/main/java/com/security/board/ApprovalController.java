package com.security.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.board.dao.ApprovalBoardDAO;

@CrossOrigin(origins = "http://localhost:8080") // 추가
@RestController
public class ApprovalController {
	@Autowired
	SimpleMessageDAO smd;

	@RequestMapping("/select")
	public List<Map<String, ?>> getMessages() {
		return smd.selectAll();
	}

	@Autowired
	ApprovalBoardDAO sbd;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Map<String, Object>> List() {
		return sbd.getBoardList();
	}

	@RequestMapping(value = "/reqlist", method = RequestMethod.GET)
	public List<Map<String, Object>> ReqList(HttpServletRequest request) {
		String username = "";
		if (request.getParameter("username") != null)
			username = request.getParameter("username");
		System.out.println("name ==" + username);
		return sbd.getReqBoardList(username);
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public Map<String, Object> Read(HttpServletRequest request) {
		int id = 0;
		if (request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id"));
		return sbd.getBoardRead(id);
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String Write(@RequestBody Map<String, Object> param) throws Exception {

		return sbd.getBoardWrite(param);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String Edit(@RequestBody Map<String, Object> param) {
		return sbd.getBoardEdit(param);
	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public String Complete(@RequestBody Map<String, Object> param) {
		return sbd.getBoardComplete(param);
	}

	@RequestMapping(value = "/prewrite")
	public List<Object> PreWrite() {
		return sbd.getPreWrite();
	}
}
