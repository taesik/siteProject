package com.mvc.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class GetBoardListController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		BoardService service = BoardService.getInstance();
		ArrayList<BoardVO> list = service.boardList();
		
		request.setAttribute("list", list);
		//request라는 내장 객체에 속성으로 저장해서 
		
		return "/board/getBoardList"; //HandlerMapping 클래스내에서 사용한다.
	}
	
}
