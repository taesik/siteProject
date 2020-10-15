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
		//���� ��û�� null �� ó��
		if(search==null) {
			search="all";
		}
		
		BoardVO vo = new BoardVO();
		vo.setSearch(search);
		vo.setKeyword(request.getParameter("keyword"));
		
		BoardService service = BoardService.getInstance();
		 //ArrayList<BoardVO> list=service.boardList();
	      ArrayList<BoardVO> list=service.boardList(vo);
	      
	      request.setAttribute("list",list);  //���⼭ �Ӽ� �߰� �ϰ� ����ó �������� ��   �̰� ����ó ������ request�� ����.
	      request.setAttribute("data", vo);

		
		
		
		//request��� ���� ��ü�� �Ӽ����� �����ؼ� 
		
		return "/board/getBoardList"; //HandlerMapping Ŭ���������� ����Ѵ�.
	}
	
}
