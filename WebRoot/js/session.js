/*
 *  *说明：获取java Session信息，如果不为空
 * 
*/

(function($){
	baseurl = location.origin+"/layuiCMS",
	session=undefined;
	 $.session = {  
		 _init:function(){
		 $.ajax({
			        type: "GET",
			        url: baseurl+"/web/login/getRyxxSession",
			        data: "",
			        dataType: "json",
			        async:false,
			        success: function(data){
			        	var currentUser = data.resultBean.content;
		            	if(data.resultBean.flag=="success") {
		            		session = {};
		            		debugger;
		            		session.id=data.resultBean.content.id;
		            		session.name=data.resultBean.content.name;
		            		session.login_name=data.resultBean.content.login_name;
		            		if(location.href==(baseurl+"/index.html")){
		            		}else{
		            		  location.href=baseurl+"/index.html";	
		            		}
		            	}else{
		            		if(location.href==(baseurl+"/index.html")){
		            			 location.href=baseurl+"/login.html";	
		            		}else{
		            		    location.href=baseurl+"/page/404.html";
		            		}
		            	}
		            },
		            error: function(e) { 
                      location.href=baseurl+"/page/404.html";
                    } 
		    	});
		 },
	 clear:function(){
			 $.ajax({
			        type: "GET",
			        url: baseurl+"/web/login/removeSession",
			        data: "",
			        dataType: "json",
			        async:false      
		    	});
	 },
		 get:function(){
		   return session;
		 }
	 };
})(jQuery);