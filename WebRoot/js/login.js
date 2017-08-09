/**
 * @Author: zmk
 * @date:2017-8-4
 *+----------------------------------------------------------------------
 *+----------------------------------------------------------------------
 */
layui.use(['jquery','layer','form'],function(){
   'use strict';
	var $ = layui.jquery
	   ,layer=layui.layer
	   ,form =layui.form();
    
    $(window).on('resize',function(){
        var w = $(window).width();
        var h = $(window).height();
        $('.larry-canvas').width(w).height(h);
    }).resize();
    var username = "";
	var password = "";
    //登录
    $(".submit_btn").click(function(){
	    var a =validateName();
	    var b = true;
	    if(a){
	    	b = validatePassWord();
	    }
	    
	    if(a&&b){
	    	$.ajax({
			        type: "POST",
			        url: "web/login/checkUser",
			        data: {
			        	loginName : username,
			        	password : password
		        	},
			        dataType: "json",
			        async:false,
			        success: function(data){
		        		
			        	var currentUser = data.resultBean.content;
		            	if(data.resultBean.flag=="success") {
		            		$.session.set("currentUserId",currentUser.id);
							$.session.set("currentUserName",currentUser.name);
							$.session.set("currentLoginName",currentUser.login_name);
 		            		location.href="./index.html";
		            	}else{
		            		layer.msg("用户名或密码错误！");
		            	}
		            }
		    	});
	    }
 	        	
    });
    function validateName(){
    	 username = $("#u_name").val();
	   
    	var validate = true;
    	if(username==""){
    		validate = false;
    		layer.msg("用户名不能为空！");
    	}else if(username.length>20||username.length<5){
    		validate = false;
    		layer.msg("用户名长度必须在5-20个之间！");
    	}else{
    		var reg = /^[a-zA-Z][a-zA-Z0-9_]*$/;
            if (!reg.test(username)) {
    	    validate = false;
    		layer.msg("用户名只能包含字母、数字和下划线！且不能以下划线开始！");
    	    }
       }
    	return validate ;
    }
    function validatePassWord(){
    	  password = $("#u_password").val();
    	  var validate = true;
    	  if(password==""){
    		validate = false;
    		layer.msg("密码不能为空！");
    	}else{
    		var reg = /^[A-Za-z0-9_]*$/;
            if (!reg.test(password)) {
    	    validate = false;
    		layer.msg("密码只能包含字母、数字和下划线！");
    	    }
    	}
    	  return validate ;
    }
    $(function(){
        $("#canvas").jParticle({
            background: "#141414",
            color: "#E5E5E5"
        });
    });

});