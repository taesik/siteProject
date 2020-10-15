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
	
	public ArrayList<BoardVO> boardList(BoardVO vo) {
		ArrayList<BoardVO> list = dao.boardList(vo);
		return list;
	}
	
	public boolean boardInsert(BoardVO vo) {
		boolean result=dao.boardInsert(vo);
		return result;
	}//수정 삭제도 이 방식이다.

	public void readCount(String _num) {
		dao.readCount(_num);
		
	}

	public BoardVO boardDetail(String _num) {
		BoardVO vo = dao.boardDetail(_num);
		vo.setContent(vo.getContent().toString().replace("\n", "<br/>"));// \n을 br tag로
		//  \n 을  <br>로 바꾸겟다는 것.그래야 콘텐트에 쓴게 엔터값이 적용이 되서 저장이된다.
		return vo;
	}
	public int boardPasswdChk(String _num,String _passwd) {
		int result = dao.boardPasswdChk(_num, _passwd);
		return result;
	}

	public BoardVO updateForm(String _num) {
		BoardVO vo =dao.boardDetail(_num);
		return vo;
	}

	public boolean boardUpdate(BoardVO vo) {
		boolean result = dao.boardUpdate(vo);
		return result;
	}

	public void boardDelete(String _num) {
		dao.boardDelete(_num);
	}
}
