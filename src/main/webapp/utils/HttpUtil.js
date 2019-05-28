function post(postUrl, params, successHandle, errorHandle) {
	$.ajax({
		url : postUrl,
		async : true,
		type : "post",
		dataType : "json",
		timeout : 15000,
		data : params,
		success : function(result, status, xhr) {
			successHandle(result);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (errorHandle == undefined) {
			} else {
				errorHandle(XMLHttpRequest);
			}
		}
	});
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}