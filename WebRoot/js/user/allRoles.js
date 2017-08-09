layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//从后台获取数据
	var rolesData = '';
	$.ajax({
			        type: "GET",
			        url: "../../web/role/queryRole",
			        data:"",
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		rolesData = data.resultBean.content;//查询成功获取数据
		            		//执行加载数据的方法
		                    rolesList();
		            	}else{
		            		top.layer.msg("获取数据失败！");
		            	}
		            }
		    	});

	//查询
	$(".search_btn").click(function(){
		var roleSeach = $(".search_input").val();
		if(roleSeach != ''){
			$.ajax({
			        type: "GET",
			        url: "../../web/role/queryRole?RoleKey="+roleSeach,
			        data:"",
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		rolesData = data.resultBean.content;//查询成功获取数据
		            		//执行加载数据的方法
		                    rolesList();
		            	}else{
		            		top.layer.msg("获取数据失败！");
		            	}
		            }
		    	});
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加角色
	$(".rolesAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加角色",
			type : 2,
			content : "addRole.html",
			success : function(layero, index){
			    layui.layer.full(index);
			   // setTimeout("layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {tips: 3});",1000)
			},
			end: function(){
				 location.reload();
		}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		//layui.layer.full(index);
	})

    //全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

	//操作
	$("body").on("click",".roles_edit",function(){  //编辑
		layer.alert('您点角色授权按钮，暂未实现，后期会添加，敬请谅解。。。',{icon:6, title:'角色权限'});
	})
 
	$("body").on("click",".roles_del",function(){  //删除
		
		var _this = $(this);
		layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
			//_this.parents("tr").remove();
			var deleteRoleId = _this.attr("data-id");
			
				//后台删除此用户
					$.ajax({
			        type: "POST",
			        url: "../../web/role/deleteRole?RoleId="+deleteRoleId,
			        data:"",
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
//		            		for(var i=0;i<rolesData.length;i++){
//				         if(rolesData[i].ID == deleteRoleId){
//					         debugger;
//					           rolesData.splice(i,1);
//					      rolesList();
//				}
//			}
		            		rolesData = data.resultBean.content;//查询成功获取数据
		            		//执行加载数据的方法
		                    rolesList();
		            		top.layer.msg("删除成功！");
		            	}else{
		            		top.layer.msg("删除失败！");
		            	}
		            }
		    	});
			layer.close(index);
		});
	})

	function rolesList(){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			currData = rolesData.concat().splice(curr*nums-nums, nums);
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
//			    	+  '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+  '<td>'+currData[i].name+'</td>'
			    	+  '<td>'+currData[i].create_time+'</td>'
			    	+  '<td>'+currData[i].create_persion+'</td>'
			    	+  '<td>'+currData[i].edite_time+'</td>'
			    	+  '<td>'+currData[i].edite_persion+'</td>'
			    	+  '<td>'
					+    '<a class="layui-btn layui-btn-mini roles_edit"><i class="layui-icon">&#xe618;</i> 授权</a>'
					+    '<a class="layui-btn layui-btn-danger layui-btn-mini roles_del" data-id="'+currData[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +  '</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 10; //每页出现的数据量
		laypage({
			cont : "page",
			pages : Math.ceil(rolesData.length/nums),
			jump : function(obj){
				$(".roles_content").html(renderDate(rolesData,obj.curr));
				//$('.users_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
        
})