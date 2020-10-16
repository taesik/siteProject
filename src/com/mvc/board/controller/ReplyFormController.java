package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class ReplyFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		String code = request.getParameter("code");
		
		BoardService service = BoardService.getInstance();
		BoardVO  vo= service.replyForm(num);
		request.setAttribute("reply", vo);
		
		//code 조건 추가 
		if (code!= null && code.equals("1")) {
			request.setAttribute("errorMsg", "답변 처리에 문제가 있어 잠시후 다시 입력해주세요");
		}
		//Handlermapping 확인하자.
		return "/board/replyForm";
	}
	
	
}
