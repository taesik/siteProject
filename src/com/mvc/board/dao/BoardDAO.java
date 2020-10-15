package com.mvc.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mvc.board.vo.BoardVO;
import com.sun.net.httpserver.Authenticator.Success;

public class BoardDAO {
	private DataSource ds;
	
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		if (instance ==null) {
			instance= new BoardDAO();
		}
		return instance;
	}
	private BoardDAO() {}
	
	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
		return ds.getConnection(); //나는 javax.activation.DataSource를 찾을 수 없다고 뜬다. 물어보자.
	}
	
	/****************************************************************************
	 * 
	 * boardList() 메서드 : 게시판 목록 조회 (검색처리 제외)
	 * @return ArrayList<BoardVO> 리턴
	 * 
	 ****************************************************************************/
	
	public ArrayList<BoardVO> boardList(BoardVO vo) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			con=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT num,author,title, ");
			query.append("to_char(writeday,'YYYY/MM/DD') writeday, ");
			query.append("readcnt, reproot, repstep,repindent from board ");
			
			if ("title".equals(vo.getSearch())) {  //검색 조건
				query.append("where title LIKE ? ");
			}else if("author".equals(vo.getSearch())) {
				query.append("where author like ? ");
			}else if("content".equals(vo.getSearch())) {
				query.append(" where content like ? ");
			}
			query.append(" order by repRoot desc, repStep asc");
			pstmt = con.prepareStatement(query.toString());
			if (!"all".equals(vo.getSearch())) {
				pstmt.setString(1, "%"+vo.getKeyword()+"%" );
			}
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO data = new BoardVO();
				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setWriteday(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setRepstep(rs.getInt("repstep"));
				data.setReproot(rs.getInt("reproot"));
				data.setRepindent(rs.getInt("repindent"));
				
				list.add(data);
			}//end while
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs!=null) rs.close();
				if (pstmt!=null) pstmt.close();
				if (con!=null) con.close();
					
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}//end select
	public boolean boardInsert(BoardVO vo){
		 Connection con=null;
         PreparedStatement pstmt=null;
         boolean result= false;
         StringBuffer sql = new StringBuffer();
			
			int rowCount=0;	
			try{
			con=getConnection();
			//seq안쓰고 max 뽑아다가 1더해서 쓸거면 preparedStatement를 두개 만들어야한다
			//아니면 안에 select max를 써야한다.
			sql.append("insert into board(num,title,author,content,reproot, repstep ,repindent,passwd )");
			sql.append("values(board_seq.nextval,?,?,?,board_seq.currval,0,0,?)");
			
			pstmt=con.prepareStatement(sql.toString());
			
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getAuthor());
			pstmt.setString(3,vo.getContent());
			pstmt.setString(4,vo.getPasswd());
			
			
			rowCount=pstmt.executeUpdate();
			if(rowCount==1)result= true;
			
		}catch(SQLException ex){
			System.out.println("boardInsert error : " + ex);
			ex.printStackTrace();
		}catch(Exception e) {
			System.out.println("error boardInsert =  "+ e);
		
		}finally{
				try {
					if (pstmt!=null) pstmt.close();
					if (con!=null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}

	/***********************************************************
	* boardDetail() 메서드: 상세 페이지 처리 메서드.
	* @param 게시물 번호. 
	* @return BoardVO 리턴
	 * @throws SQLException 
	***********************************************************/
	public BoardVO boardDetail(String _num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO vo = new BoardVO();
		try{
			con= getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("select num, author, title, content, ");
			query.append("TO_char(writeday,'YYYY-MM-DD HH24:MI:SS') writeday, ");
			query.append("readcnt, repRoot, repIndent, ");
			query.append("repStep from board where num =?");
			
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1,Integer.parseInt(_num));
			//여기는 비밀번호를 조회 하지않는다.
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setAuthor(rs.getString("author"));
				vo.setContent(rs.getString("content"));
				vo.setWriteday(rs.getString("writeday"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setReproot(rs.getInt("reproot"));
				vo.setRepstep(rs.getInt("repstep"));
				vo.setRepindent(rs.getInt("repindent"));
			}//end if
		}catch(Exception e) { 
				e.printStackTrace();
		}finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
			}catch (SQLException se){
				se.printStackTrace();
				
			}
		}
		return vo;
	}

	/***********************************************************
	* boardPasswdChk() 메서드: 비밀번호 조회 메서드.
	* @param 게시물 번호, 비밀번호. 
	* @return int 리턴
	***********************************************************/
	public  int  boardPasswdChk(String  _num, String  _passwd){
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		int result=0;
		try{
			con=getConnection();
			StringBuffer query =new StringBuffer();
			query.append("select nvl((select 1 from board where num=? and passwd = ?),0) as result from Dual");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,Integer.parseInt(_num));
			pstmt.setString(2,_passwd);
			
			rs=pstmt.executeQuery();
			if (rs.next()) { //0 아니면 1이지만 그래도 무조건 rs.next()를 한다.
				result =rs.getInt("result");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();	
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}

	/***********************************************************
	* boardUpdate() 메서드: 게시물 수정 처리 메서드.
	* @param BoardVO
	* @return boolean.
	***********************************************************/
	public boolean boardUpdate(BoardVO vo){
		Connection con =null;
		PreparedStatement pstmt=null;
		boolean success = false;
		
		
		try {
			con=getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update board set title=?, content=? ");  //여기 끝에 쉼표 주면안된다. 이거 왜 그런건지 이해 안된다 물어보자.
			if(vo.getPasswd()!="") sql.append(", passwd = ? "); //비밀번호가 비어있지 않으면 비밀번호도 업데이트 하겟다.
			sql.append("where num=?"); 							//여기 다시 물어보자 
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getContent());
			if(vo.getPasswd()!="") {
				pstmt.setString(3, vo.getPasswd());
				pstmt.setInt(4, vo.getNum());
			}else {
				pstmt.setInt(3, vo.getNum());
			}
			
			int count=pstmt.executeUpdate(); //적용된 행의 수
			System.out.println("execute Query 확인"+ count);
			if(count==1) success = true;
			
		}catch(Exception ex) {
			System.out.println("Modify error : " +ex);
		}finally{
				try {
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return success;
		
	}

	/***********************************************************
	* boardDelete() 메서드: 게시물 삭제 처리 메서드.
	* @param 게시물 번호, 비밀번호. 
	***********************************************************/
	public void boardDelete(String  _num){
		Connection con =null;
		PreparedStatement pstmt=null;
		
		try {
			con=getConnection();
			String sql = "delete from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(_num));
			pstmt.executeUpdate();
	        
			
		}catch(Exception ex) {
			ex.printStackTrace();	
		}finally {
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
		}
	
	}//end delete
	public void readCount(String _num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("update board set readcnt = readcnt +1");
			query.append("where num=?");
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(_num));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
