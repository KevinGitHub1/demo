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
	var validatecode = "";
    //登录
    $(".submit_btn").click(function(){
	    var a =validateName();
	    var b = true;
	    var c = true;
	    if(a){
	    	b = validatePassWord();
	    	if(b){
	    		c=validateCode();
	    	}
	    }
	    
	    if(a&&b&&c){
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
		            	localStorage.setItem("userid",data.resultBean.content.id);
		            	localStorage.setItem("loginName",data.resultBean.content.login_name);
		            	localStorage.setItem("userName",data.resultBean.content.name);
 		            		location.href="./index.html";
		            	}else{
		            		localStorage=null;
		            		layer.msg("用户名或密码错误！");
		            	}
		            }
		    	});
	    }
 	        	
    });
    //验证用户名信息是否填写正确
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
    //验证密码是否填写正确
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
    //判断验证码是否输入正确
    function validateCode(){
    	  var code = $("#code").val();
    	  var validate = true;
    	  if(code==""){
    		  validate = false;
    		  layer.msg("验证码不能为空！");
    	  }else if(code!=validatecode){
    		   validate = false;
    		   layer.msg("验证码输入不正确！");
    	}
    	  return validate ;
    }
    document.getElementById('verifyImg').onclick = randomString;
    //生成四位随机验证码
    function randomString() {
　　var len = 4;
　　var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
　　var maxPos = $chars.length;
　　var pwd = '';
　　for (var i = 0; i < len; i++) {
　　　　pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
　　}
　　// 验证码写入span区域
    validatecode = pwd;
	document.getElementById('verifyImg').innerHTML = pwd;
}
    //初始化
    $(function(){
        $("#canvas").jParticle({
            background: "#141414",
            color: "#E5E5E5"
        });
        randomString();
    });

});