$(function(){
	
	selecturl="/o2o/login/getuser";
	loginurl="/o2o/shopadmin/shoplist"
	
	identityUrl = "/o2o/login/getshopbyuserid";
	
	$("#login").click(function(){
		var name = $("#name").val();
		var password = $("#password").val();
		var formData = new FormData();
		formData.append("name",name);
		$.ajax({
			url:selecturl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.userName != name) {
					$.toast("用户名错误");
				} else {
					if(data.password != password){
						$.toast("密码错误");
					}else{
						if(data.identity == 0){
							location.href="/o2o/shopadmin/shoplist";
						}else if(data.identity == 1){
							var goid=data.id;
							/*$.ajax({
								url:loginurl,
								typr:'POST',
								data : data.id,
								contentType : false,
								processData : false,
								cache : false,
								success: function(data){
									
								}
							});*/
							location.href="/o2o/shopadmin/shopmanagement?shopId="+1 + "&userId="+goid;
						}else if(data.identity == 2){
							location.href="/o2o/front/index?id=" + data.id;
						}
					}
				}
			},
		error : function(){
			$.toast("登陆失败");
		}
		});
	});
	/*$('#register').click(function(){
		location.href="/o2o/register.html"
	})*/
});