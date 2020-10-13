package com.mvc.common.controller;

import java.util.HashMap;
import java.util.Map;

import com.mvc.board.controller.GetBoardListController;
import com.mvc.board.controller.InsertFormController;
import com.mvc.board.controller.InsertBoardController;

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
	}
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
	
}
