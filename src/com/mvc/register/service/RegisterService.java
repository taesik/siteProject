package com.mvc.register.service;

import com.mvc.register.dao.RegisterDAO;
import com.mvc.register.vo.RegisterVO;

public class RegisterService {
   private static RegisterService service=new RegisterService();
   private RegisterDAO dao=RegisterDAO.getInstance();
   
   private RegisterService() {}
   
   public static RegisterService getInstance() {
      return service;
   }
   
   public boolean memberInsert(RegisterVO vo) {
      boolean result=dao.memberInsert(vo);
      return result;
   }
}