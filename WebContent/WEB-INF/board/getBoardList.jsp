<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
//<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      			
		<link rel="shortcut icon" href="/siteProject/image/icon.png" />
		<link rel="apple-touch-icon" href="/siteProject/image/icon.png" />
				
		<!-- IE8 이하에서 HTML5를 인식시키기 위해 아래의 패스필터를 적용-->
		<!--[if lt IE 9]>
			<script src="/siteProject/include/js/html5shiv.js"></script>
		<![endif]-->
		
		<script type="text/javascript" src="/siteProject/include/js/jquery-1.12.4.min.js"> </script>  
		<script type="text/javascript" src="/siteProject/include/js/jquery.slides.js"> </script>
		<script type="text/javascript" src="/siteProject/include/js/common.js"> </script>
		
		<script type="text/javascript">
			$(function () {
				//검색 후 검색 대상과 검색 단어 출력
				if ('${data.keyword}'!="") { //표현언어라 묶어 ''줘야한다.
					$("#keyword").val('${data.keyword}');
					$("#search").val('${data.search}');
				}
				if('${param.keyword}'!="") {
					$("#keyword").val('${param.keyword}');
					$("#search").val('${param.search}');
				}
				
				/* 검색 대상이 변경될 때마다 처리 이벤트*/
	            $("#search").change(function(){
	               if($("#search").val()=="all"){
	                  $("#keyword").val("전체 데이터 조회합니다.");
	               }else if($("#search").val()!="all"){
	                  $("#keyword").val("");
	                  $("#keyword").focus();
	               }
	            });
	            
	            /* 검색 버튼 클릭 시 처리 이벤트*/
	            $("#searchData").click(function(){
	               if($("#search").val()!="all"){
	                  if(!chkData(keyword,"검색어를")) return;
	               }
	               goPage();
	            });
	            /*검색을 위한 실질적인 처리 함수*/
	            function goPage(){
	               if($("#search").val()=="all"){
	                  $("#keyword").val("");
	               }
	               $("#f_search").attr({
	                  "method":"post",
	                  "action":"/siteProject/board/getBoardList.do"
	               });
	               $("#f_search").submit();
	            }

	            //글쓰기 버튼 클릭시
				$("#writeForm").click(function() {// 글쓰기 버튼 클릭시 
					location.href="/siteProject/board/insertForm.do"//insertFormController 
						//uri  버튼 클릭했을 때 입력화면이 가도록
						//getBoardListController 서브 컨트롤러가 사용된다.					
				});
				
				
				
				//제목 클릭시 상세페이지 이동을 위한 처리 이벤트
				$(function () {
					$(".goDetail").click(function() {
						//var num =$(this).parents("tr").children().eq(0).html();
						var num = $(this).parents("tr").attr("data-num");
						//console.log("num = "+ num);
						$("#num").val(num);
						$("#detailForm").attr({
							"method":"post",
							"action":"/siteProject/board/detailBoard.do"
							
						});
						$("#detailForm").submit();
					});
				});
			});
		</script>
				<style type="text/css">
			body {
        		font-family: '맑은 고딕' 돋움; font-size:0.75em; color:#333
		}
			th ,td{
			         border:1px solid gray;
			         border-width: 1px 0;
			         border-collapse: collapse;
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
<title>목록보기</title>
	</head>
	<body>
	         <form name="detailForm" id="detailForm">
            <input type="hidden" name="num" id="num">
         </form>
         <div id="boardSearch" class="text-right">
            <form id="f_search" name="f_search" class="form-inline">
               <div class="form-group">
                  <label>검색조건</label>
                  <select id="search" name="search" class="form-control">
                     <option value="all">전체</option>
                     <option value="title">제목</option>
                     <option value="author">작성자</option>
                     <option value="content">글내용</option>
                  </select>
                  <input type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요" maxlength="20" class="form-control" />
                  <button type="button" class="btn btn-primary btn-sm" id="searchData">검색</button>
               </div>
            </form>
            
	
		<div class="contentContainer container-fluid">
			<div class="text-center"><h3>글목록</h3></div>
			<!-- post 방식을 위해 폼을 만ㄷ근다. -->
			<form name="detailForm" id="detailForm" >
				<input type="hidden" name="num" id="num">
			</form>
			
			<%--====================================리스트 시작 ==================== --%>
			<div id = "boardList" >
				<table summary="게시판리스트" class="table">
					<thead>
						<tr>
							<th>번호 </th>
							<th>제목 </th>
							<th>작성자 </th>
							<th>날짜 </th>
							<th>조회수 </th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty list}" >
								<c:forEach var="vo" items="${list}" >
									<tr class="tac" data-num="${vo.num}">
										<td>${vo.num } </td>
										<%-- <td class="tal"><span class = "goDetail">${vo.title}</span></td> --%>
										<%--이렇게 주면 답변 구분이 안되서 주석달음 --%>
										<td class="tal">
											<c:if test="${vo.repStep>0}" >
												<c:forEach begin="1" end = "${vo.repIndent}" >
													&nbsp;&nbsp;&nbsp;
												</c:forEach>
												<img src="/siteProject/include/image/replyicon.jpg" />
											</c:if>
										<span class="goDetail">${vo.title}</span>
										</td>
										<td>${vo.author } </td>
										<td>${vo.writeday  } </td>
										<td>${vo.readcnt } </td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr> 
									<td colspan="5" class="tac">등록된 게시물이 존재하지 않습니다. </td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>	
				</table>
			</div>
			<%--==========================글쓰기 버튼 출력 시작 ============================ --%>
			<div class="contentBtn text-right">
				<input type="button" value="글쓰기" id="writeForm">
			</div>
			
		</div>
		
	</body>
</html>