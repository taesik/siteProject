package com.mvc.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private String charset;
   private HandlerMapping handlerMapping;
   private ViewResolver viewResolver;
   
   public void init() throws ServletException {
      charset=this.getInitParameter("charset");
      
      handlerMapping=new HandlerMapping();
      viewResolver=new ViewResolver();
      viewResolver.setPrefix("/WEB-INF");
      viewResolver.setSuffix(".jsp");
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      process(request,response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      request.setCharacterEncoding(charset);
      process(request, response);
   }
   
   private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      //1. 클라이언트의 요청 path 정보를 추출한다.
      //요청 url에서 http://localhost:8080/siteProject/board/getBoardList.do
      String uri=request.getRequestURI();
      //URI 인 /siteProject/board/getBoardList.do를 얻는다.
      //System.out.println("요청 URI: "+uri);
      
      //String path=uri.subString(uri.lastIndexOf("/"));
      //uri에서 마지막 /를 찾아 /getBoardList.do를 얻는다.
      
      String path=uri.substring(request.getContextPath().length());
      //URI에서 /siteProject를 찾아 나머지 /board/getBoardList.do를 얻는다.
      System.out.println("path: "+path);
      
      //2.HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
      Controller ctrl=handlerMapping.getController(path);
      
      //3.검색된 Controller를 실행한다.
      String viewName=ctrl.execute(request, response);
      
      //4.viewResolver를 통해 viewName에 해당하는 화면을 검색한다.
      String view=null;
      if(!viewName.contains(".do")) {
      view=viewResolver.getView(viewName);
      //5. 검색된 화면으로 이동한다.
      try {
         RequestDispatcher dispatcher=request.getRequestDispatcher(view);
         dispatcher.forward(request, response);
      }catch(Exception ex) {
         System.out.println("forward 오류: "+ex);
      }
   }else {
	   view = "/siteProject" + viewName;
	   response.sendRedirect(view);
   	}

   }
}