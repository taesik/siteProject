package com.mvc.register.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.common.controller.Controller;

public class InsertInfoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "/register/insertInfo.do";
	}

}
