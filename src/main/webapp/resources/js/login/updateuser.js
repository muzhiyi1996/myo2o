$(function(){
	var getuserbyidUrl="/o2o/login/getuserbyid";
	var updateuserUrl="/o2o/login/updateuser";
	var userid = request("id");
	var identity = request("identity");
	var formData = new FormData();
	formData.append("id",userid);
	$.ajax({
		url:getuserbyidUrl,
		type : 'POST',
		data : formData,
		contentType : false,
		processData : false,
		cache : false,
		success : function(data) {
			$("#user_name").val(data.userName);
			$("#user_password").val(data.password);
			$("#user_age").val(data.age);
			$("#user_gender").val(data.gender);
			$("#user_phone").val(data.phone);
			$("#user_addr").val(data.addr);
			
		}
	});
	$("#submit").click(function(){
		var username = $("#user_name").val();
		var password = $("#user_password").val();
		var age = $("#user_age").val();
		var gender = $("#user_gender").val();
		var phone = $("#user_phone").val();
		var addr = $("#user_addr").val();
		var formData = new FormData();
		formData.append("name",username);
		formData.append("password",password); 
		formData.append("age",age);
		formData.append("gender",gender);
		formData.append("phone",phone);
		formData.append("addr",addr);
		$.ajax({
			url:updateuserUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if(data == 1){
					$.toast("修改成功");
				}
				
			}
		});
		
	});
	$("#return").click(function(){
		if(identity == 2){
			
			window.location.href="/o2o/front/index?id="+userid;
		}else if(identity == 0){
			location.href="/o2o/userlist.html"
		}
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function request(paras){
		var url = window.location.href;
		var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");
		var paraObj = {}
		for (i=0; j=paraString[i]; i++){
		paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);
		}
		var returnValue = paraObj[paras.toLowerCase()];
		if(typeof(returnValue)=="undefined"){
		return "没获取到id";
		}else{
		return returnValue;
		}
		};
})