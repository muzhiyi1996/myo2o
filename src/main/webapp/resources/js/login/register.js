$(function(){
	
	var insertUrl = "/o2o/login/insertuser"
	
	$("#user_name").change(function(){
		var name = $("#user_name").val();
		var formData = new FormData();
		formData.append("name",name);
		$.ajax({
			url:'/o2o/login/getuser',
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if(data.name == name){
					$.toast("该用户已存在");
				}
				
			}
			
		});
	});
		
	$("#return").click(function(){
		location.href="/o2o/"
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
			url:insertUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if(data == 1){
					$.toast("注册成功");
				}
				
			}
		});
	});
})