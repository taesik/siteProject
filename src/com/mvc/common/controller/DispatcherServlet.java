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
      //1. Ŭ���̾�Ʈ�� ��û path ������ �����Ѵ�.
      //��û url���� http://localhost:8080/siteProject/board/getBoardList.do
      String uri=request.getRequestURI();
      //URI �� /siteProject/board/getBoardList.do�� ��´�.
      //System.out.println("��û URI: "+uri);
      
      //String path=uri.subString(uri.lastIndexOf("/"));
      //uri���� ������ /�� ã�� /getBoardList.do�� ��´�.
      
      String path=uri.substring(request.getContextPath().length());
      //URI���� /siteProject�� ã�� ������ /board/getBoardList.do�� ��´�.
      System.out.println("path: "+path);
      
      //2.HandlerMapping�� ���� path�� �ش��ϴ� Controller�� �˻��Ѵ�.
      Controller ctrl=handlerMapping.getController(path);
      
      //3.�˻��� Controller�� �����Ѵ�.
      String viewName=ctrl.execute(request, response);
      
      //4.viewResolver�� ���� viewName�� �ش��ϴ� ȭ���� �˻��Ѵ�.
      String view=null;
      if(!viewName.contains(".do")) {
      view=viewResolver.getView(viewName);
      //5. �˻��� ȭ������ �̵��Ѵ�.
      try {
         RequestDispatcher dispatcher=request.getRequestDispatcher(view);
         dispatcher.forward(request, response);
      }catch(Exception ex) {
         System.out.println("forward ����: "+ex);
      }
   }else {
	   view = "/siteProject" + viewName;
	   response.sendRedirect(view);
   	}

   }
}