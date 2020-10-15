package com.mvc.register.service;

import com.mvc.register.dao.RegisterDAO;

public class RegisterService {
	private static RegisterService instance= new RegisterService();
	private RegisterDAO dao = RegisterDAO.getInstance();
	
	private RegisterService() {}
	
	public static RegisterService getInstance() {
		return instance;
	}
	//  로직 메서드들...
	public 
	
}
