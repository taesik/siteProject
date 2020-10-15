package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class UpdateFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
			String num = request.getParameter("num");
			String code = request.getParameter("code");
			
			BoardService service = BoardService.getInstance();
			BoardVO data = service.updateForm(num);
			
			request.setAttribute("updateData", data);
			//코드가 서브컨트롤러에서 실패하는지
			if (code!= null&& code.equals("1")) {
				request.setAttribute("errorMsg", "수정완료에 문제가 있어 잠시 후 다시 입력해 주세요");	
			}
			
		return "/board/updateForm";
	}

}
