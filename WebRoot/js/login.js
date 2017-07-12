/**
 * @Author: 
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
    
    //登录
    $(".submit_btn").click(function(){
    	var username = $("#u_name").val();
	    var password = $("#u_password").val();
	    console.log(username);
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
		            		$.session.set("currentUserId",currentUser.ID);
							$.session.set("currentUserName",currentUser.NAME);
							$.session.set("currentLoginName",currentUser.LOGIN_NAME);
 		            		location.href="./index.html";
		            	}else{
		            		layer.msg("用户名或密码错误！");
		            	}
		            }
		    	});
    });
    $(function(){
        $("#canvas").jParticle({
            background: "#141414",
            color: "#E5E5E5"
        });
    });

});