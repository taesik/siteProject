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
		<script type="text/javascript" src="/siteProject/include/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/siteProject/include/js/common.js"></script>
		<script type="text/javascript" src="/siteProject/include/dist/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
			$(function () {
					$("#boardUpdate").click(function() {
						if (!chkData("#title","제목을")) return;
						else if(!chkData("#content","작성할 내용을")) return;
						//else if (!chkData("#passwd","비밀번호를")) return; 비밀번호는 필수항목이 아니다.
						else {
							$("#f_updateForm").attr({
								"method":"post",
								"action":"/siteProject/board/updateBoard.do" //updateBoard.do를 요청한다.
							});
							$("#f_updateForm").submit(); //체크할때는 여기를 주석준다.
						}
					});
					$("#boardListBtn").click(function() {
						location.href="/siteProject/board/getBoardList.do";
					});
			});
		</script>

<title>Insert title here</title>
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
	</style>
	</head>
	<body>
	 	<%-- ${updateData title}이걸로 확인했엇는데 이건 수정해야되니까 텍스트가 필요 --%>
	 	<%-- <input type="text" name="title" id="title" value="${updateData.title}"> --%>
	 	
	 	<h3 align="center">글수정</h3>
	 	<div class="container">
		<div class="contentTB">
			<form  id="f_updateForm" name= "f_updateForm">
				<input type="hidden" name = "num" id="num" value="${updateData.num}">
				<table>
						<tr>
							<td class="ac" >글번호 </td>
							
							<td>${updateData.num} <span>(조회수 : ${updateData.readcnt })</span> </td>
							<td class="ac"> 작성일 </td>
							<td> ${updateData.writeday} </td>
						</tr>
						<tr>
							<td class="ac"> 작성자 </td>
							<td colspan="3"> ${updateData.author} </td> 
						</tr>
						<tr> 
							<td>글 제목 </td>
							<td> <input type="text" id="title" name="title" value="${updateData.title}" /> </td>
						</tr>
						
						<tr>
							<td class="ac"> 글 내용 </td>
							<td colspan="3"><textarea name= "content" cols="70" id="content">${updateData.content}</textarea></td>
						</tr>
						<tr>
							<td class="ac" > 비밀번호 </td>
							<td colspan="3"><input type="password" name="passwd" id="passwd" >(수정할 비밀번호를 입력해 주세요) </td>
						</tr>
					</tbody>

				</table>	
				<div class="contentBtn">
				<input type="button" class="but" value="수정" id="boardUpdate"/>
				<input type="button" class="but" value="목록" id="boardListBtn"/>
				</div>	
			</form>
		</div>
	</div>

	</body>
</html>