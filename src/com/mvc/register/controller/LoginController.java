package com.mvc.register.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.common.controller.Controller;

public class LoginController implements Controller {

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse resopnse) {
      return "/register/login";
   }

}