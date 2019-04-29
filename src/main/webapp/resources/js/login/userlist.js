$(function() {

	getuserlist();
	function getuserlist() {
		$.ajax({
			url : "/o2o/login/getuserlist",
			type : "get",
			dataType : "json",
			success : function(data) {
				handleList(data)

			}
		});
	}
	function handleList(data) {
		var userListHtml = '';
		data.map(function(item, index) {
			userListHtml += '<div class="row row-shop"><div class="col-40">'
					+ item.userName + '</div><div class="col-40">'
					+ userType(item.identity)
					+ '</div><div class="col-20">'
					+ goUser(item.identity, item.id) + '</div></div>'
		});
		$('.user-wrap').html(userListHtml);
	}

	function userType(data){
		if(data == 1){
			return '店主';
		}else{
			return '顾客';
		}
	}
	function goUser(identity,id){
		return '<a href="/o2o/updateuser.html?id=' + id + '&identity=0'
		+ '">进入</a>';
	}
})