package com.mvc.common.controller;

import java.util.HashMap;
import java.util.Map;

import com.mvc.board.controller.DeleteBoardController;
import com.mvc.board.controller.DetailBoardController;
import com.mvc.board.controller.GetBoardListController;
import com.mvc.board.controller.InsertBoardController;
import com.mvc.board.controller.InsertFormController;
import com.mvc.board.controller.InsertReplyController;
import com.mvc.board.controller.ReplyFormController;
import com.mvc.board.controller.UpdateBoardController;
import com.mvc.board.controller.UpdateFormController;
import com.mvc.register.controller.InsertInfoController;
import com.mvc.register.controller.InsertRegisterController;
import com.mvc.register.controller.LoginController;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		/* 답변형 게시판 처리*/
		//여기서 mappings = new HashMap<String, GetBoardListController>();
		//줬다면  GetBoardListController만 줬을 것이다.
		//인터페이스 Controller로 주고 구현 클래스 다 담게 다형성을이용
//		하였다.
		mappings.put("/board/getBoardList.do" ,new GetBoardListController());
		mappings.put("/board/insertForm.do", new InsertFormController());
		//담는다 식별자(uri)가 들어오면 로직을 처리할 애가 있어야 하는데  그 로직 처리할 경로를 설정하는 것이다.
		mappings.put("/board/insertBoard.do", new InsertBoardController());
		mappings.put("/board/detailBoard.do", new DetailBoardController());
		mappings.put("/board/passwdCheck.do", new PasswdCheckController());
		mappings.put("/board/updateForm.do", new UpdateFormController());
		mappings.put("/board/updateBoard.do", new UpdateBoardController());
		mappings.put("/board/deleteBoard.do", new DeleteBoardController());
		mappings.put("/board/replyForm.do", new ReplyFormController());
		mappings.put("/board/insertReply.do", new InsertReplyController());
		
	//=============5조 팀프로젝트 =============
	      mappings.put("/register/login.do", new LoginController());
	      mappings.put("/register/insertRegister.do", new InsertRegisterController());
	      mappings.put("/register/insertInfo.do", new InsertInfoController());
	
	
	}
	public Controller getController(String path) {
		return mappings.get(path);
	}
	
}
