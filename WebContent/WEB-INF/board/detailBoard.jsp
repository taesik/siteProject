<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta charset="utf-8" />
		<!-- html4 : 파일의 인코딩 방식 지정 -->
		<!--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />-->

		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<!-- 브라우저의 호환성 보기 모드를 막고, 해당 브라우저에서 지원하는 가장 최신 버전의 방식으로 HTML 보여주도록 설정.-->
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		<!--viewport : 화면에 보이는 영역을 제어하는 기술. width는 device-width로 설정(브라우저 너비를 장치 너비에 맞추어 표시). initial-scale는 초기비율(보이는 영역과 웹 페이지를 맞춤). user-scalable는 사용자가 화면축소를 하지 못하도록 설정.-->
      			
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
				
		<!-- IE8 이하에서 HTML5를 인식시키기 위해 아래의 패스필터를 적용-->
		<!--[if lt IE 9]>
			<script src="../js/html5shiv.js"></script>
		<![endif]-->
		<style type="text/css">
			body {
        		font-family: '맑은 고딕' 돋움; font-size:0.75em; color:#333

		}
		
			th ,td{

			         border:1px solid gray;
			
			         border-width: 1px 0;
			
			         border-collapse: collapse;
			
			         text-align: center;
			
			         padding:8px;
			
		}


			td{

         border-style:dotted;

		}



			tr:hover td{

         background-color: tomato;

         cursor : pointer;

}
		
		</style>
		<script type="text/javascript" src="/siteProject/include/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/siteProject/include/js/common.js"></script>
		<script type="text/javascript">
			var butChk = 0; //butChk는 수정 버튼과 삭제 버튼을 구별하기 위한 변수
			
			$(function () {
				$("#pwdChk").hide();
				
				
				
				//수정버튼 클릭시 처리 이벤트
				$("updateForm").click(function(){
					$("#pwdChk").show();
					$("#msg").text("작성시 입력할 비밀번호를 입력해 주세요.").css("color","#000009");
					butChk=1;
				});
					
				//삭제버튼 클릭 시 처리 이벤트 
				$("#boardDelete").click(function(){
					$("#pwdChk").show();
					$("#msg").text("작성시 입력한 비밀번호를 입력해주세요").css("color","#000009");
					butChk =2;
				});
				
				//비밀번호 확인 버튼 클릭 시 처리 이벤트
				$("#pwdBut").click(function() {
					boardPwdConfirm(); // 하나 만든다.
				});//페이지가 로딩될고 할게 아니다. 딴데다 만들거다.
				
				$("pwdButCancel").click(function() {
					$("#pwdChk").hide();
					butChk = "";
				});
				
				
				
				
				
				
				
				//목록 버튼 클릭 시 처리 이벤트 
				
			});
				//비밀번호 입력간이 공백값인 걸 제어해야한다.
				//  ==========비밀 번호 클릭시 실질적인 처리 함수
				//common.js가 있어야 처리된다.
			function boardPwdConfirm() {
				if (!chkData('#passwd',"비밀번호를")) return; //이건 common.js에 정의 되있는 함수를 사용하였다.
				else {
					$.ajax({
						url: "/siteProject/board/passwdCheck.do", //전송 url
						type : "post" ,					//전송 시 method 방식
						data : $("#f_pwd").serialize(),//폼전체 데이터 전송
						dataType : "text",
						error : function() {		//실행시 오류가 발생ㅎ였을 경우
							alert ('시스템 오류 입니다. 관리자에게 문의하세요');
						},							//정상적으로 실행이 되었을 경우
						success : function(resultData) { //resultData에 0이나 1을 받는다. 0이면 비밀번호가 일치 하지않는다.
							var goUrl = "";		//이동할 경로를 저장할 변수
							if(resultData==0){ //일치하지않는 경우
								$("#msg").text("작성시 입력한 비밀번호가 일치하지 않습니다.");
								$("#passwd").select();
							}else if(resultData==1){ //일치할 경우
								$("#msg").text("");
							//console.log("비밀번호 일치");
								if (butChk==1) {//수정버튼 클릭
									goUrl = "/siteProject/board/updateForm.do";
								}else if(butChk==2) {//삭제버튼 클릭
									goUrl = "/siteProject/board/deleteBoard.do";
								}
								$("#f_data").attr("action",goUrl);
								//$("#f_data").submit();  
								//주석 주고 새로고침  404 떨어질거다
							
							}
						}
						
						
						
						
						
					});
					
					
					
					
					
					
				}
					
				
			}
		
		</script>
		
<title>Insert title here</title>
	</head>
	<body>
		<h3>  QNA</h3>
		
		<form method="post" name="f_data" id="f_data">
			<input type="hidden" name="num" value="${detail.num}" >
		</form>
		
		
		
		<%--  ===========================비밀번호 확인 버튼 및 버튼 추가 시작 --%>
		<div id="boardPwdBut">
			<div id="pwdChk" class="authArea">
				<form name="f_pwd" id="f_pwd">
					<input type="hidden" name="num" id="num" value="${detail.num}">
					<label for="b_pwd" id ="l_pwd">비밀번호 : </label>
					<input type="password" name="passwd" id="passwd" />
					<button type="button" id="pwdBut">확인</button>
					<button type="button" id="pwdButCancel">취소</button>		
					<!-- 취소를 누르면 다시 hidden을 키게 로직을 만든다. -->
					<span id = "msg"></span>
				</form>
			</div>
		</div>	
		<div class="btnArea">
			<input type="button" id ="updateForm"  value="수정" > 
			<input type="button" id ="boardDelete"  value="삭제" >
			<input type="button" id ="boardReply" value="답변">
			<input type="button" id ="boardListBtn" value="목록">
		</div>
		<form >
			<table border="solid" >
				
				<thead width="600" > 
					<tr> 
						<td><span>글번호  </span> </td>
						<td> <span>${detail.readcnt }</span>
						<td> <span> 작성일</span> </td>
						<td> <span> ${ detail.writeday }</span> </td>
					</tr> 
				</thead>
				<tbody width="600">
					<tr>
						<td> <span> 글제목</span> </td> <td> <span>${detail.title } </span>    </td>
					</tr>
					<tr>  
						<td> <span> 작성자 </span> </td> <td> <span> ${detail.author }  </span> </td>
					</tr>
					<tr>
						<td> <span> 글내용    </span> </td> <td> <span> ${detail.content }      </span>  </td>
					</tr>
				</tbody>
			
			
			
			</table>
		
		</form>
	
	</body>
</html>