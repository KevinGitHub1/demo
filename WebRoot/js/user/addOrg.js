var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
		//添加部门时初始化上级单位信息
		var parentid=parent.currentItem.id;
		var parentName = parent.currentItem.name;
		$("#pdeptname").val(parentName);
		//编辑组织机构信息时初始化页面内容
		var id = parent.selectItem.id;
		$("#eorgname").val(parent.selectItem.name);
		$("#eorgno").val(parent.selectItem.deptno);
 	//添加验证规则
        form.verify( {
								orgname : function(value) {
        	                 if (value.length<2) {
										return '单位名称不能少于2个字符';
									}
        	                 if (value.length>10) {
										return '单位名称不能超过10个字符';
									}
									if (!new RegExp(
											"^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$")
											.test(value)) {
										return '单位名称不能包含特殊字符';
									}
								},
								deptname : function(value) {
        	                 if (value.length<2) {
										return '部门名称不能少于2个字符';
									}
        	                 if (value.length>10) {
										return '部门名称不能超过10个字符';
									}
									if (!new RegExp(
											"^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$")
											.test(value)) {
										return '部门名称不能包含特殊字符';
									}
								}
							});
    //新增单位信息
 	form.on("submit(addOrg)",function(data){
    	var orgname = $("#orgname").val();
	    var orgno = $("#orgno").val();
	    var user =  {
			        	orgname : orgname,
			        	orgno : orgno
		        	}
 	        	$.ajax({
			        type: "POST",
			        url: "../../web/user/addOrg",
			        data:user,
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		top.layer.msg("单位添加成功！");
                            var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
                            parent.layer.close(index); //执行关闭
		            	}else{
		            		top.layer.msg("单位添加失败！");
		            	}
		            }
		    	});
    });  
 	//新增部门或组信息
 	form.on("submit(addDept)",function(data){
    	var deptname = $("#deptname").val();
	    var deptno = $("#deptno").val();
	    var dept =  {
			        	deptname : deptname,
			        	deptno : deptno,
			        	parentid:parentid
		        	}
 	        	$.ajax({
			        type: "POST",
			        url: "../../web/user/addDept",
			        data:dept,
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		top.layer.msg("部门添加成功！");
                            var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
                            parent.layer.close(index); //执行关闭
		            	}else{
		            		top.layer.msg("部门添加失败！");
		            	}
		            }
		    	});
    });  
 	//编辑组织机构信息
 	form.on("submit(editOrg)",function(data){
    	var orgname = $("#eorgname").val();
	    var deptno = $("#eorgno").val();
	    var org =  {
	    	             id:id,
			        	orgname : orgname,
			        	orgno : deptno
			        	
		        	}
 	        	$.ajax({
			        type: "POST",
			        url: "../../web/user/editOrg",
			        data:org,
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		top.layer.msg("组织机构信息编辑成功！");
                            var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
                            parent.layer.close(index); //执行关闭
		            	}else{
		            		top.layer.msg("组织机构信息编辑失败！");
		            	}
		            }
		    	});
    }); 
})