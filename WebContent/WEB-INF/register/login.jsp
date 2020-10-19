<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />

      <title>Insert title here</title>
      
      <link rel="shortcut icon" href="/siteProject/image/icon.png" />
      <link rel="apple-touch-icon" href="/siteProject/image/icon.png" />
      
      <link rel="stylesheet" type="text/css" href="/siteProject/include/dist/css/bootstrap.min.css" />
      <link rel="stylesheet" type="text/csS" href="/siteProject/include/dist/css/bootstrap-theme.css" />
      
   
      
      <script type="text/javascript" src="/siteProject/include/js/jquery-1.12.4.min.js"></script>
      <script type="text/javascript" src="/siteProject/include/dist/js/bootstrap.min.js"></script>
      <script type="text/javascript">
         $(function(){
            $("#register").click(function(){
               location.href="/siteProject/register/insertRegister.do";
            });
            
            $("#login").click(function () {
				
			});
         });
         
      
      </script>
      <!--[if lt IE 9]>
      <script src="../js/html5shiv.js"></script>
      <![endif]-->
   </head>
   
   <body>
      <div id="container" class="text-center">
         <h2>Login</h2>
         <div id="sub-container">
            <form id="loginInfo"  class="form-horizontal">
               <div class="form-group">
                  <input type="text" id="id" name="id" placeholder="아이디" />
               </div>
               <div class="form-group">
                  <input type="password" id="password" name="password" placeholder="비밀번호" />
               </div>
               <div class="form-group">   
                  <button type="button" id="login" class="btn btn-primary">로그인</button>
                  <button type="button" id="register" class="btn btn-success">회원가입</button>
               </div>
            </form>
         </div>
         <c:if test="${member != null}"> 
         	<li>
         		${member.userName}님 환영합니다.
         	</li>
         	<li>
         		<a href= "/register/signout">로그아웃</a>
         	</li>	
         
         </c:if>
         <div id="register">
            
         </div>
      </div>
   </body>
</html>