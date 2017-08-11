layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
	var userId = localStorage.getItem("userid");
		if(userId==null){
			location.href="../../login.html";
			return;
		}
	//查询树节点信息
	var treeData = "";
	 currentItem ="";
	$.ajax({
			        type: "GET",
			        url: "../../web/user/queryOrgaTree",
			        data:"",
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		console.log(data.resultBean.content);
		            		treeData = data.resultBean.content;//查询成功获取数据
		            		layui.tree({
    elem: '#demo1' //指定元素
    ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
    ,click: function(item){ //点击节点回调
	  currentItem=item;
      //layer.msg('当前节名称：'+ item.name + '<br>全部参数：'+ JSON.stringify(item));
	  //根据点击的节点信息刷新右侧显示的数据，右侧默认数据为全部
      //console.log(item);
	  $.ajax({
			        type: "POST",
			        url: "../../web/user/queryOrganization",
			        data:{
		              type:2,key:item.id
			        },
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
    }
    ,nodes:treeData});
		            	}else{
		            		top.layer.msg("获取数据失败！");
		            	}
		            }
		    	});

	//从后台获取数据
	var usersData = '';
	$.ajax({
			        type: "POST",
			        url: "../../web/user/queryOrganization",
			        data:{
		              type:1,key:""
			        },
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

	//查询
	$(".search_btn").click(function(){
		var orgSeach = $(".search_input").val();
		if(orgSeach != ''){
			$.ajax({
			        type: "POST",
			        url: "../../web/user/queryOrganization",
			        data:{
		              type:3,key:orgSeach
			        },
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
			layer.msg("请输入单位名称");
		}
	})

	//添加单位
	$(".unitAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加单位",
			type : 2,
			content : "addOrg.html",
			success : function(layero, index){
			    layui.layer.full(index);
			},
		   end: function(){
				 location.reload();
		}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
	})
	function getCurrentItem(){
		return currentItem;
	}
//添加部门或分组
	$(".deptAdd_btn").click(function(){
		if(""==currentItem){
			layer.msg("请点击组织机构树上的节点作为上级组织！");
		}else{
			var index = layui.layer.open({
			title : "添加部门",
			type : 2,
			content : "addDept.html",
			success : function(layero, index){
			    layui.layer.full(index);
			},
		   end: function(){
				 location.reload();
		}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		}
		
	})
  selectItem={};//定义编辑时选中的数据行
String.prototype.trim = function(){ return this.replace(/^\s+|\s+$/g,"")} 
	//操作
	$("body").on("click",".org_edit",function(){  //编辑
		  var tr = $(this)[0].parentNode.parentNode;//找到tr原色
         var td = tr.childNodes;//找到td元素
         selectItem.id = td[3].innerHTML;;
         selectItem.name = td[0].innerHTML;
         selectItem.deptno =  td[1].innerHTML;
			var index = layui.layer.open({
			title : "编辑组织机构信息",
			type : 2,
			content : "editOrg.html",
			success : function(layero, index){
			    layui.layer.full(index);
			},
		   end: function(){
				 location.reload();
		}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		//layer.alert('您点击了人员授权按钮，暂未实现，后期会添加，敬请谅解。。。',{icon:6, title:'编辑组织机构信息'});
	})
  
	$("body").on("click",".unit_del",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此组织？',{icon:3, title:'提示信息'},function(index){
			var deleteId = _this.attr("data-id");
				//后台删除此用户
					$.ajax({
			        type: "POST",
			        url: "../../web/user/deleteOrg?id="+deleteId,
			        data:"",
			        dataType: "json",
			        async:false,
			        success: function(data){
		            	if(data.resultBean.flag=="success") {
		            		for(var i=0;i<usersData.length;i++){
				if(usersData[i].id == deleteId){
					usersData.splice(i,1);
					usersList();
				}
			}
		            		top.layer.msg("删除成功！");
		            	}else{
		            		top.layer.msg(data.resultBean.errorInfo);
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
					dataHtml += '<tr>'
//			    	+  '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+  '<td>'+currData[i].name+'</td>'
			    	+  '<td>'+currData[i].deptno+'</td>'
			    	+  '<td>'
					+    '<a class="layui-btn layui-btn-mini org_edit"><i class="layui-icon">&#xe642;</i>编辑</a>'
					+    '<a class="layui-btn layui-btn-danger layui-btn-mini unit_del" data-id="'+currData[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +  '</td>'
			        +  '<td style="display:none">'+currData[i].id+'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 14; //每页出现的数据量
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
        
});