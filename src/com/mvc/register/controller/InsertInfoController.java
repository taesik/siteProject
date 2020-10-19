package com.mvc.register.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.common.controller.Controller;
import com.mvc.register.service.RegisterService;
import com.mvc.register.vo.RegisterVO;

public class InsertInfoController implements Controller {

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse resopnse) {
      // TODO Auto-generated method stub
      String path=null;
      System.out.println(request.getParameter("phone"));
      RegisterVO vo=new RegisterVO();
      vo.setId(request.getParameter("id"));
      vo.setPasswd(request.getParameter("passwd"));
      vo.setName(request.getParameter("name"));
      vo.setAddress(request.getParameter("address"));
      vo.setPhone(request.getParameter("phone"));
      vo.setEmail(request.getParameter("email"));
      
      RegisterService service=RegisterService.getInstance();
      boolean result = service.memberInsert(vo);
      
      if(result==true) {
         path="/register/login.do";
      }else {
         path="/register/insertRegister.do";
         request.setAttribute("errorMsg", "등록완료에 문제가 있어 잠시 후 다시 입력해 주세요.");
      }
      return path;
   }

}