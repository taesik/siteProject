package com.mvc.register.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.common.controller.Controller;
import com.mvc.register.service.RegisterService;
import com.mvc.register.vo.RegisterVO;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		RegisterService service = RegisterService.getInstance();
		//RegisterVO data = service.updateForm(num);
		
		//request.setAttribute("updateData", data);
		//�ڵ尡 ������Ʈ�ѷ����� �����ϴ���
		//if (code!= null&& code.equals("1")) {
		//	request.setAttribute("errorMsg", "�����Ϸῡ ������ �־� ��� �� �ٽ� �Է��� �ּ���");	
		//}
		
		
		return "/register/loginController.do";
	}

}
