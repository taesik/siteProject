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
			var conText = $("#content").val();
			$("#content").val("\n\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>기존글>>>>>>>>>>\n\n"+conText);
		
		$("#boardInsert").click(function() {
			//입력값 체크
			if (!chkData("#author","이름을")) return;
			else if (!chkData("#title","제목을")) return;
			else if (!chkData("#content","작성할 내용을")) return;
			else if (!chkData("#passwd","비밀번호를")) return;
			
			else {
				$("#f_replyForm").attr({
					"method":"post",
					"action":"/siteProject/board/insertReply.do"
					});
				$("#f_replyForm").submit();
				}	
		});
		//목록 버튼 클릭시 처리 이벤트
		$("#boardListBtn").click(function() {
			location.href="/siteProject/board/getBoardList.do"; 
		});
		});
		</script>
<title>Insert title here</title>
	</head>
	<body>
		<div class="container">
		<div class="row">
			<form  id="f_replyForm" name="f_replyForm">
				<%--답변글 필요 --%>
				<input type="hidden" name="num" value="${reply.num}">
				<input type="hidden" name="repRoot" value="${reply.repRoot}">
				<input type="hidden" name="repStep" value="${reply.repStep}">
				<input type="hidden" name="repIndent" value="${reply.repIndent}">
				
				
			
				<table  width ="200" height="300"
					style="text-align: center; border: 1px solid #dddddd">
					<colgroup>
						<col width="17%" />
						<col width="83%" />
					</colgroup>
					
					<thead>
						<%--줘도 되고 안줘도 된다 답변 달려는 근본글에 대한 번호와 조회수를 EL로 보여싿. --%>
						<tr>
							<td colspan="2"> 원래글번호 ${reply.num} &nbsp;(조회수 : ${reply.readcnt}) </td>
						</tr>
					
					
						<tr>
							<td class="ac"> 작성자 </td>
							<td class="ac" ><input width ="200" type="text" id="author" class="form-control" placeholder="작성자" name = "author" maxlength="50"> </td>
						</tr>

					</thead>

					<tbody>

						<tr>
							<td class="ac"> 글 제목 </td>
							<td><input value="${reply.title}" width ="200" type="text" class="form-control" 
							placeholder="글 제목" id="title" name="title" maxlength="100"/></td>
							
						</tr>

						<tr>
							<td class="ac"> 글 내용 </td>
							<td><textarea  width ="200" height = "300"rows="10" 
							cols="10" class="form-control" id="content" 
							placeholder="글 내용" name="content" 
							maxlength="2048" style="height: 350px;">${reply.content}</textarea></td>
							
						</tr>
						<tr>
							<td class="ac" > 비밀번호 </td>
							<td><input width ="200" height = "300" "type="password" name="passwd" id="passwd" class="form-control">
						</tr>
					</tbody>

				</table>	
				<div class="contentBtn">
				<input type="button" class="btn btn-primary" value="저장" id="boardInsert"/>
				<input type="button" class="btn btn-primary" value="목록" id="boardListBtn"/>
				</div>	
			</form>

		</div>

	</div>
		
	</body>
</html>