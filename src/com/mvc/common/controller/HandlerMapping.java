package com.mvc.common.controller;

import java.util.HashMap;
import java.util.Map;

import com.mvc.board.controller.DeleteBoardController;
import com.mvc.board.controller.DetailBoardController;
import com.mvc.board.controller.GetBoardListController;
import com.mvc.board.controller.InsertBoardController;
import com.mvc.board.controller.InsertFormController;
import com.mvc.board.controller.UpdateBoardController;
import com.mvc.board.controller.UpdateFormController;
import com.mvc.register.controller.LoginController;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		/* �亯�� �Խ��� ó��*/
		//���⼭ mappings = new HashMap<String, GetBoardListController>();
		//��ٸ�  GetBoardListController�� ���� ���̴�.
		//�������̽� Controller�� �ְ� ���� Ŭ���� �� ��� ���������̿�
//		�Ͽ���.
		mappings.put("/board/getBoardList.do" ,new GetBoardListController());
		mappings.put("/board/insertForm.do", new InsertFormController());
		//��´� �ĺ���(uri)�� ������ ������ ó���� �ְ� �־�� �ϴµ�  �� ���� ó���� ��θ� �����ϴ� ���̴�.
		mappings.put("/board/insertBoard.do", new InsertBoardController());
		mappings.put("/board/detailBoard.do", new DetailBoardController());
		mappings.put("/board/passwdCheck.do", new PasswdCheckController());
		mappings.put("/board/updateForm.do", new UpdateFormController());
		mappings.put("/board/updateBoard.do", new UpdateBoardController());
		mappings.put("/board/deleteBoard.do", new DeleteBoardController());
	
	//=============5�� ��������Ʈ =============
		mappings.put("/register/login.do", new LoginController());
		mappings.put("/register/insertForm.do", new InsertFormController());
	
	
	
	
	}
	public Controller getController(String path) {
		return mappings.get(path);
	}
	
}
