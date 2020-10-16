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
		return ds.getConnection(); //���� javax.activation.DataSource�� ã�� �� ���ٰ� ���. �����.
	}
	
	/****************************************************************************
	 * 
	 * boardList() �޼��� : �Խ��� ��� ��ȸ (�˻�ó�� ����)
	 * @return ArrayList<BoardVO> ����
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
			
			if ("title".equals(vo.getSearch())) {  //�˻� ����
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
				data.setRepStep(rs.getInt("repstep"));
				data.setRepRoot(rs.getInt("reproot"));
				data.setRepIndent(rs.getInt("repindent"));
				
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
			//seq�Ⱦ��� max �̾ƴٰ� 1���ؼ� ���Ÿ� preparedStatement�� �ΰ� �������Ѵ�
			//�ƴϸ� �ȿ� select max�� ����Ѵ�.
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
	* boardDetail() �޼���: �� ������ ó�� �޼���.
	* @param �Խù� ��ȣ. 
	* @return BoardVO ����
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
			//����� ��й�ȣ�� ��ȸ �����ʴ´�.
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setAuthor(rs.getString("author"));
				vo.setContent(rs.getString("content"));
				vo.setWriteday(rs.getString("writeday"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRepRoot(rs.getInt("reproot"));
				vo.setRepStep(rs.getInt("repstep"));
				vo.setRepIndent(rs.getInt("repindent"));
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
	* boardPasswdChk() �޼���: ��й�ȣ ��ȸ �޼���.
	* @param �Խù� ��ȣ, ��й�ȣ. 
	* @return int ����
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
			if (rs.next()) { //0 �ƴϸ� 1������ �׷��� ������ rs.next()�� �Ѵ�.
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
	* boardUpdate() �޼���: �Խù� ���� ó�� �޼���.
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
			sql.append("update board set title=?, content=? ");  //���� ���� ��ǥ �ָ�ȵȴ�. �̰� �� �׷����� ���� �ȵȴ� �����.
			if(vo.getPasswd()!="") sql.append(", passwd = ? "); //��й�ȣ�� ������� ������ ��й�ȣ�� ������Ʈ �ϰٴ�.
			sql.append("where num=?"); 							//���� �ٽ� ����� 
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getContent());
			if(vo.getPasswd()!="") {
				pstmt.setString(3, vo.getPasswd());
				pstmt.setInt(4, vo.getNum());
			}else {
				pstmt.setInt(3, vo.getNum());
			}
			
			int count=pstmt.executeUpdate(); //����� ���� ��
			System.out.println("execute Query Ȯ��"+ count);
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
	* boardDelete() �޼���: �Խù� ���� ó�� �޼���.
	* @param �Խù� ��ȣ, ��й�ȣ. 
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
	/***********************
	 * makeReply �޼��� : �亯���� ���� repStep 1 ����.
	 * @param _root
	 * @param _step
	 */
	 
		public void makeReply(int _root,int _step) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con=getConnection();
				
				StringBuffer query = new StringBuffer();
				query.append("update board set repstep =repstep +1 ");
				query.append("where reproot = ? and repstep > ? ");
				
				pstmt= con.prepareStatement(query.toString());
				pstmt.setInt(1,_root);
				pstmt.setInt(2,_step);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	
	/************************
	 * replyInsert() �޼��� : �亯 �Է� ó��
	 * @param vo
	 * @return
	 *************************/
	
	
	
	public boolean replyInsert(BoardVO vo) {
		//���� �ö󰡸� insert�� �ִ�. �װɺ���. ����
		 makeReply(vo.getRepRoot(),vo.getRepStep());
		 Connection con=null;
         PreparedStatement pstmt=null;
         boolean result= false;
			
			int rowCount=0;	
		try{
			StringBuffer sql = new StringBuffer();
			con=getConnection();
			//seq�Ⱦ��� max �̾ƴٰ� 1���ؼ� ���Ÿ� preparedStatement�� �ΰ� �������Ѵ�
			//�ƴϸ� �ȿ� select max�� ����Ѵ�.
			sql.append("insert into board(num,title,author,content,reproot, ");
			sql.append("repstep ,repindent,passwd )");
			sql.append("values(board_seq.nextVal,?,?,?,?,?,?,?)");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getAuthor());
			pstmt.setString(3,vo.getContent());
			pstmt.setInt(4,vo.getRepRoot());
			pstmt.setInt(5,vo.getRepStep()+1);
			pstmt.setInt(6,vo.getRepIndent()+1);
			pstmt.setString(7,vo.getPasswd());
			
			
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
}
