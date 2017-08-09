var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
 	//添加验证规则
        form.verify( {
								rolename : function(value) {
        	                 if (value.length<3) {
										return '角色名不能少于3位字符';
									}
									if (!new RegExp(
											"^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$")
											.test(value)) {
										return '角色名不能有特殊字符';
									}
								}
							});
 	form.on("submit(addRole)",function(data){
    	var roleName = $("#role_name").val();
 	        	$.ajax({
			        type: "POST",
			        url: "../../web/role/addRole",
			        data:{roleName:roleName,user:"log"},
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		top.layer.msg("角色添加成功！");
                            var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
                            parent.layer.close(index); //执行关闭
 		            		//location.href="./allRoles.html";
		            	}else{
		            		top.layer.msg("角色添加失败！");
		            	}
		            }
		    	});
    });
	  
})
