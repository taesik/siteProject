package com.mvc.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class GetBoardListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String search = request.getParameter("search");
		//최초 요청시 null 값 처리
		if(search==null) {
			search="all";
		}
		
		BoardVO vo = new BoardVO();
		vo.setSearch(search);
		vo.setKeyword(request.getParameter("keyword"));
		
		BoardService service = BoardService.getInstance();
		 //ArrayList<BoardVO> list=service.boardList();
	      ArrayList<BoardVO> list=service.boardList(vo);
	      
	      request.setAttribute("list",list);  //여기서 속성 추가 하고 디스패처 서블릿으로 가   이게 디스패처 서블릿의 request와 같다.
	      request.setAttribute("data", vo);

		
		
		
		//request라는 내장 객체에 속성으로 저장해서 
		
		return "/board/getBoardList"; //HandlerMapping 클래스내에서 사용한다.
	}
	
}
