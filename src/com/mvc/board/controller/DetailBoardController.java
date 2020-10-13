package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class DetailBoardController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		 String num=request.getParameter("num");
		 //컨트롤러 만들때는 받은 파라미터가 있는 지 확인한다.
		 
		 BoardService service = BoardService.getInstance();
		 //인스턴스 생성
		 service.readCount(num);//조회수 증가
		 
		 //조회수 증가한 대상을 가지고 온다.
		 BoardVO data = service.boardDetail(num);
		 request.setAttribute("detail", data);
		 //실제 얘가 가리키는 건 VO 이다.
		 //그래서 jsp에서 ${detail.title}이라고 써서 확인한다.
		 
		 return "/board/detailBoard";
	}

}
