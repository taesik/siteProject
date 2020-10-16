package com.mvc.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mvc.register.vo.RegisterVO;

public class RegisterDAO {
	private DataSource ds; 
	private static RegisterDAO instance = new RegisterDAO();
	public static RegisterDAO getInstance() {
		if (instance ==null) {
			instance= new RegisterDAO();
		}
		return instance;
	}
	private RegisterDAO() {}
	
	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
		return ds.getConnection(); //나는 javax.activation.DataSource를 찾을 수 없다고 뜬다. 물어보자.
	}
	public  RegisterVO selectOne(String id) {
		Connection con=null;
		RegisterVO member_data = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT id,passwd from member where id = ?  ");
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				member_data = new RegisterVO();
				member_data.setId(rs.getString("id"));
				member_data.setPasswd(rs.getString("passwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		return member_data;
	}
	public void insertMember(RegisterVO member) {
		StringBuffer query = new StringBuffer();
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			query.append("insert into member(id, passwd, name, ");
			query.append("address, phone, email) ");
			query.append("value(? ,? ,? ,? ,? ,? ,? )");
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
	}
	

	

}
