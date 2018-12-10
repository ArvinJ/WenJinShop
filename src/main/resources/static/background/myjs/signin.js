function signin() {
	var username= $("#username").val();
	var password= $("#password").val();
	alert("username:"+username+"-password:"+password);
	
	$.ajax({
        type:"POST",
        dataType: 'json',
        contentType: "application/json;",
        url:"/steels/user/login",
        async:false,
        data:{
            "name":username,
            "pass":password
        },
        beforeSend: function () {
            $("body").append('<div id="pload" style="position:fixed;top:30%;z-index:1200;background:url(background/img/loading.gif) top center no-repeat;width:100%;height:140px;margin:auto auto;"></div>');
        },
        complete: function () {
            $("#pload").remove();
        },
        
        success: function(result){
        	alert(result);
        	var jsonObj = eval( '(' + result + ')' ); 
            if(jsonObj.res=="200"){
            	alert(jsonObj.msg);
            	
            }else{
            	alert(jsonObj.msg);
            	// 失败
            }
        }
    })
	
	
}