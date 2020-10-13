/* chkData(유효성체크 대상, 메시지 내용) */
function chkData(item, msg) {
	if($(item).val().replace(/\s/g,"")=="") {
		alert(msg + "입력해주세요.");
		$(item).val("");
		$(item).focus();
		return false;
	}else{
		return true;
	}
}