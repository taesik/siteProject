package com.mvc.register.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mvc.register.vo.RegisterVO;

public class RegisterDAO {
   private DataSource ds;
   
   private static RegisterDAO instance=null;
   public static RegisterDAO getInstance() {
      if(instance==null) {
         instance=new RegisterDAO();
      }
      return instance;
   }
   
   private RegisterDAO() {}
   private Connection getConnection() throws Exception{
      Context ctx=new InitialContext();
      ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
      return ds.getConnection();
   }
   
   /****************************************************************************
    * memberInsert() 메소드 : 회원 등록
    * @return boolean 리턴
    ****************************************************************************/
   public boolean memberInsert(RegisterVO vo) {
      Connection con=null;
      PreparedStatement pstmt=null;
      boolean result=false;
      StringBuffer sql=new StringBuffer();
      sql.append("insert into member(id, passwd, name, address, phone, email) ");
      sql.append("values(?,?,?,?,?,?)");
      
      try {
         con=getConnection();
         pstmt=con.prepareStatement(sql.toString());
         
         pstmt.setString(1, vo.getId());
         pstmt.setString(2, vo.getPasswd());
         pstmt.setString(3, vo.getName());
         pstmt.setString(4, vo.getAddress());
         pstmt.setString(5, vo.getPhone());
         pstmt.setString(6, vo.getEmail());
         
         int count=pstmt.executeUpdate();
         
         if(count==1) result=true;
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         try {
            if(pstmt!=null) pstmt.close();
            if(con!=null) con.close();
         }catch(SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }
}