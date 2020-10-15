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
		//코드가 서브컨트롤러에서 실패하는지
		//if (code!= null&& code.equals("1")) {
		//	request.setAttribute("errorMsg", "수정완료에 문제가 있어 잠시 후 다시 입력해 주세요");	
		//}
		
		
		return "/register/loginController.do";
	}

}
