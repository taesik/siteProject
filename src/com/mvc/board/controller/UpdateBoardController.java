package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class UpdateBoardController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String path =null;
		
		String passwd = request.getParameter("passwd");
		if (passwd.isEmpty()) passwd=""; 
		
		BoardVO vo = new BoardVO();
		vo.setNum(Integer.parseInt(request.getParameter("num")));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setPasswd(passwd);

		BoardService service = BoardService.getInstance();
		boolean result = service.boardUpdate(vo);
		if (result) {
			path = "/board/detailBoard.do?num="+vo.getNum();//서버측에 전달할때 값이 여러개이면 식별자를 준다.
		}else {
			path = "/board/updateForm.do?num"+vo.getNum()+"&code=1"; //code 1번이면 수정하다가 실패해서 간거
			//스페이스바로 공백 주면 안된다.
		}
			return path;
	}
}
