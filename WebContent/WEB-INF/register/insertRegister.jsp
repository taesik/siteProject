<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            //등록 버튼을 눌렀을 때.
            $("#subBtn").click(function(){
               var address=$("#postNum").val()+" "+$("#basicAddress").val()+" "+$("#restAddress").val();
               var phone=$("#firstPhoneNum").val()+""+$("#secondPhoneNum").val()+""+$("#lastPhoneNum").val();
               $("#address").val(address);
               $("#phone").val();
               
               if($("#id").val().replace(/\s/g,"")==""){
                  alert("아이디를 입력해주세요");
                  $("#id").focus();
                  return;
               }else if($("#passwd").val().replace(/\s/g,"")==""){
                  alert("비밀번호를 입력해주세요");
                  $("#passwd").focus();
                  return;
               }else if($("#name").val().replace(/\s/g,"")==""){
                  alert("이름을 입력해주세요");
                  $("#name").focus();
                  return;
               }else if($("#address").val().replace(/\s/g,"")==""){
                  alert("주소를 입력해주세요");
                  $("#postNum").focus();
                  return;
               }else if($("#phone").val().replace(/\s/g,"")==""){
                  alert("전화번호를 입력해주세요");
                  $("#phone").focus();
                  return;
               }else if($("#email").val().replace(/\s/g,"")==""){
                  alert("이메일을 입력해주세요");
                  $("#email").focus();
                  return;
               }else{
                  $("#f_writeForm").attr({
                     "method":"post",
                     "action":"/siteProject/register/insertInfo.do"
                  });
                  $("#f_writeForm").submit();
               }
            })
         })
      </script>
      <!--[if lt IE 9]>
      <script src="../js/html5shiv.js"></script>
      <![endif]-->
      
   </head>
   
   <body>
      <div id="container">
         <h2>회원가입</h2>
         <form id="f_writeForm">
            <input type="hidden" name="address" id="address" />   
            <input type="hidden" name="phone" id="phone" />      
            <div id="insertContainer">
               <table class="table table-bordered">
                  <tr>
                     <td>아이디</td>
                     <td><input type="text" name="id" id="id" maxlength="20"></td>
                  </tr>
                  <tr>
                     <td>비밀번호</td>
                     <td><input type="password" name="passwd" id="passwd" maxlength="16" /></td>
                  </tr>
                  <tr>
                     <td>비밀번호 확인</td>
                     <td><input type="password" id="chkPasswd" maxlength="16" /></td>
                  </tr>
                  <tr>
                     <td>이름</td>
                     <td><input type="text" id="name" name="name" maxlength="10" /></td>
                  </tr>
                  <tr>
                     <td rowspan="3">주소</td>
                     <td><input type="text" id="postNum" name="postNum" maxlength="8" size="8" /> 우편번호</td>
                  </tr>
                  <tr>
                     <td><input type="text" id="basicAddress" name="basicAddress" maxlength="50" /> 기본주소</td>
                  </tr>
                  <tr>
                     <td><input type="text" id="restAddress" name="restAddress" maxlength="50" /> 나머지주소</td>
                  </tr>
                  <tr>
                     <td>휴대전화</td>
                     <td>
                        <input type="text" id="firstPhoneNum" maxlength="3" size="3" value="010"/> - 
                        <input type="text" id="secondPhoneNum" size="4" maxlength="4" /> - 
                        <input type="text" id="lastPhoneNum" size="4" maxlength="4" />
                     </td>
                  </tr>
                  <tr>
                     <td>이메일</td>
                     <td>
                        <input type="text" id="email" name="email" maxlength="40" />
                     </td>
                  </tr> 
               </table>
            </div>
         </form>
         <div id="btnGroup">
            <input type="button" id="subBtn" value="등록" />
            <input type="button" id="loginBtn" value="취소" />
         </div>
      </div>
   </body>
</html>