package com.mvc.register.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
		return ds.getConnection(); //���� javax.activation.DataSource�� ã�� �� ���ٰ� ���. �����.
	}
	

	

}
