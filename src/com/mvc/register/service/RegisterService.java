package com.mvc.register.service;

import com.mvc.register.dao.RegisterDAO;
import com.mvc.register.vo.RegisterVO;

public class RegisterService {
	private static RegisterService service= new RegisterService();
	private RegisterDAO dao = RegisterDAO.getInstance();
	
	private RegisterService() {
		
	}
	
	public static RegisterService getInstance() {
		return service;
	}
	//  로직 메서드들...
	
	public boolean login(String id , String pw) {
		RegisterVO member = dao.selectOne(id);
		
		if(member == null){
			return false;
		}
		else {
			if (member.getPasswd().equals(pw)) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	
	
	public String loginCheck(String id, String passwd) {
		String logon_id;
		
		
		return id;
	}
	public boolean join(String id, String pw, String name, String email) {
		if (dao.selectOne(id)==null) {
			RegisterVO member = new RegisterVO();
			member.setId(id);
			member.setPasswd(pw);
			member.setName(name);
			member.setEmail(email);
			dao.insertMember(member);
			return true;
		}
		else 
			return false;
	}
	
	
}
