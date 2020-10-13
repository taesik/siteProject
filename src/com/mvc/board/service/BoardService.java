package com.mvc.board.service;

import java.util.ArrayList;

import com.mvc.board.dao.BoardDAO;
import com.mvc.board.vo.BoardVO;

public class BoardService {
	private static BoardService service = new BoardService();
	private BoardDAO dao =BoardDAO.getInstance();
	
	private BoardService() {}
	
	public static BoardService getInstance() {
		return service;
	}
	
	public ArrayList<BoardVO> boardList() {
		ArrayList<BoardVO> list = dao.boardList();
		return list;
	}
	
	public boolean boardInsert(BoardVO vo) {
		boolean result=dao.boardInsert(vo);
		return result;
	}//���� ������ �� ����̴�.

	public void readCount(String _num) {
		dao.readCount(_num);
		
	}

	public BoardVO boardDetail(String _num) {
		BoardVO vo = dao.boardDetail(_num);
		vo.setContent(vo.getContent().toString().replace("\n", "<br/>"));// \n�� br tag��
		//  \n ��  <br>�� �ٲٰٴٴ� ��.�׷��� ����Ʈ�� ���� ���Ͱ��� ������ �Ǽ� �����̵ȴ�.
		return vo;
	}
	public int boardPasswdChk(String _num,String _passwd) {
		int result = dao.boardPasswdChk(_num, _passwd);
		return result;
	}
}
