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
			//�ڵ尡 ������Ʈ�ѷ����� �����ϴ���
			if (code!= null&& code.equals("1")) {
				request.setAttribute("errorMsg", "�����Ϸῡ ������ �־� ��� �� �ٽ� �Է��� �ּ���");	
			}
			
		return "/board/updateForm";
	}

}
