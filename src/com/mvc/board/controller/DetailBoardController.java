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
		 //��Ʈ�ѷ� ���鶧�� ���� �Ķ���Ͱ� �ִ� �� Ȯ���Ѵ�.
		 
		 BoardService service = BoardService.getInstance();
		 //�ν��Ͻ� ����
		 service.readCount(num);//��ȸ�� ����
		 
		 //��ȸ�� ������ ����� ������ �´�.
		 BoardVO data = service.boardDetail(num);
		 request.setAttribute("detail", data);
		 //���� �갡 ����Ű�� �� VO �̴�.
		 //�׷��� jsp���� ${detail.title}�̶�� �Ἥ Ȯ���Ѵ�.
		 
		 return "/board/detailBoard";
	}

}
