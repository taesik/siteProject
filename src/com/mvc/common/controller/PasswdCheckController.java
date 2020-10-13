package com.mvc.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;

public class PasswdCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		String passwd = request.getParameter("passwd");
		
		BoardService service = BoardService.getInstance();
		int result = service.boardPasswdChk(num, passwd);
		
		request.setAttribute("resultData", result);
		
		return "/common/resultData";//web-inf/common/resultData.jsp
	}

}
