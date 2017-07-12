layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//从后台获取数据
	var usersData = '';
	$.ajax({
			        type: "GET",
			        url: "../../web/user/queryUser",
			        data:"",
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		
		            		console.log(data.resultBean.content);
		            		usersData = data.resultBean.content;//查询成功获取数据
		            		//执行加载数据的方法
		                    usersList();
		            	}else{
		            		top.layer.msg("获取数据失败！");
		            	}
		            }
		    	});

	//查询
	$(".search_btn").click(function(){
		var userArray = [];
		var userSeach = $(".search_input").val();
		if(userSeach != ''){
			$.ajax({
			        type: "GET",
			        url: "../../web/user/queryUser?userKey="+userSeach,
			        data:"",
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		usersData = data.resultBean.content;//查询成功获取数据
		            		//执行加载数据的方法
		                    usersList();
		            	}else{
		            		top.layer.msg("获取数据失败！");
		            	}
		            }
		    	});
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加会员
	$(".usersAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加用户",
			type : 2,
			content : "addUser.html",
			success : function(layero, index){
			    layui.layer.full(index);
			   // setTimeout("layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {tips: 3});",1000)
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
	$("body").on("click",".users_edit",function(){  //编辑
		layer.alert('您点击了会员编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'文章编辑'});
	})
   $("body").on("click",".users_status_change",function(){  //停用
		var _this = $(this);
		var change = _this[0].text.trim();
		var tempValue = "";
		layer.confirm('确定'+change+'此用户？',{icon:3, title:'提示信息'},function(index){
			var deleteUesrId = _this.attr("data-id");
			for(var i=0;i<usersData.length;i++){
				if(usersData[i].ID == deleteUesrId){
					if(usersData[i].STATUS==1){
						usersData[i].STATUS=0;
						tempValue=0;
					}else{
						usersData[i].STATUS=1;
						tempValue=1;
					}
					
					usersList(usersData);
				}
			}
				//后台改变此用户状态
					$.ajax({
			        type: "POST",
			        url: "../../web/user/changeUserStatus?userId="+deleteUesrId+"&status="+tempValue,
			        data:"",
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		top.layer.msg(change+"成功！");
		            	}else{
		            		top.layer.msg(change+"失败！");
		            	}
		            }
		    	});
			layer.close(index);
		});
	})
	$("body").on("click",".users_del",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
			//_this.parents("tr").remove();
			var deleteUesrId = _this.attr("data-id");
			for(var i=0;i<usersData.length;i++){
				if(usersData[i].ID == deleteUesrId){
					usersData.splice(i,1);
					usersList(usersData);
				}
			}
				//后台删除此用户
					$.ajax({
			        type: "POST",
			        url: "../../web/user/deleteUser?userId="+deleteUesrId,
			        data:"",
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		top.layer.msg("删除成功！");
		            	}else{
		            		top.layer.msg("删除失败！");
		            	}
		            }
		    	});
			layer.close(index);
		});
	})

	function usersList(){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			currData = usersData.concat().splice(curr*nums-nums, nums);
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					var tqyong = currData[i].STATUS==1?'停用':'启用';
					dataHtml += '<tr>'
//			    	+  '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+  '<td>'+currData[i].LOGIN_NAME+'</td>'
			    	+  '<td>'+currData[i].NAME+'</td>'
			    	+  '<td>'+currData[i].STATUS+'</td>'
			    	+  '<td>'+currData[i].MOBILE_NUMBER+'</td>'
			    	+  '<td>'+currData[i].BIRTHDAY+'</td>'
			    	+  '<td>'+currData[i].MOTTO+'</td>'
			    	+  '<td>'+currData[i].PROVINCE+currData[i].CITY+currData[i].COUNTY+'</td>'
			    	+  '<td>'+currData[i].ADRESS+'</td>'
			    	+  '<td>'+currData[i].EMAIL_ADRESS+'</td>'
			    	+  '<td>'+currData[i].LOGIN_ADRESS+'</td>'
			    	+  '<td>'+currData[i].LOGIN_TIME+'</td>'
			    	+  '<td>'+currData[i].HOBBY+'</td>'
			    	+  '<td>'
			    	+    '<a class="layui-btn layui-btn-mini users_status_change" data-id="'+currData[i].ID+'"><i class="iconfont icon-edit"></i> '+tqyong+'</a>'
					+    '<a class="layui-btn layui-btn-mini users_edit"><i class="iconfont icon-edit"></i> 授权</a>'
					+    '<a class="layui-btn layui-btn-danger layui-btn-mini users_del" data-id="'+currData[i].ID+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
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
			pages : Math.ceil(usersData.length/nums),
			jump : function(obj){
				$(".users_content").html(renderDate(usersData,obj.curr));
				$('.users_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
        
})