$(function(){

    // 获取shopId
    var shopId = getQueryString("shopId");
    // 商铺管理的url
    var shopInfoUrl = '/o2o/shopadmin/getshopmanageInfo?shopId=' + shopId;

    $.getJSON(shopInfoUrl,function (data) {
        // 如果后台返回redirect=true,则跳转后台到设置的url
        if(data.redirect){
            window.location.href = data.url;
        }else{
            // 如果后台返回redirect=false，则设置shopId并给 按钮设置超链接属性（即编辑商铺）
            if (data.shopId != undefined && data.shopId != null){
                shopId = data.shopId;
            }
            $('#shopInfo').attr('href','/o2o/shopadmin/shopoperation?shopId=' + shopId);
            $('#productCategory').attr('href','/o2o/shopadmin/productcategorymanagement');
        }
    });

    var userid = request("userId");
    $("#return").click(function(){
    	if(userid == 2){
    		window.location.href="/o2o/login.html";
    	}else{
    		window.location.href="/o2o/shopadmin/shoplist";
    		
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
});