var areaData = address;
var $form;
var form;
var $;
layui.config({
	base : "../../js/"
}).use(['form','layer','upload','laydate'],function(){
	form = layui.form();
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
		$ = layui.jquery;
		$form = $('form');
		laydate = layui.laydate;
        layui.upload({
        	url : "../../json/userface.json",
        	success: function(res){
        		var num = parseInt(4*Math.random());  //生成0-4的随机数
        		//随机显示一个头像信息
		    	userFace.src = res.data[num].src;
		    	window.sessionStorage.setItem('userFace',res.data[num].src);
		    }
        });
        //初始化用户名
        var loginName = "";
        var userId = "";
        var params = location.search; //location.search是从当前URL的?号开始的字符串  
        if(params.indexOf("name")>-1){
        	//修改密碼頁面，初始化用戶名
        	loginName = params.substring(params.indexOf("=")+1);//url中仅有一个参数时获取其值
        	 $("#loginName").val(loginName);
        }else{
        	userId = params.substring(params.indexOf("=")+1);//url中仅有一个参数时获取其值
        	//进入修改个人信息页面获取到userId,根据userId查询个人信息并返回
        	var userInfo = "";
        	$.ajax({
			        type: "GET",
			        url: "../../web/user/getUserById?userId="+userId,
			        data: "" ,
			        dataType: "json",
			        async:true,
			        success : function(data) {
					if (data.resultBean.flag == "success") {
						//给各个编辑框赋值
				var userInfo = data.resultBean.content;
				if (userInfo.length > 0) {
					$("#loginName").val(userInfo[0].login_name);
					$("#userName").val(userInfo[0].name);
					$("#mobile").val(userInfo[0].mobile_number);
					$("#email").val(userInfo[0].email_adress);
					$("#birthday").val(userInfo[0].birthday);
					var provinceName = userInfo[0].province;
					var city = userInfo[0].city;
					var county = userInfo[0].county;
					loadProvince(provinceName,city,county);
				$("#adress").val(userInfo[0].adress);
				$("#hobby").val(userInfo[0].hobby);
				$("#motto").val(userInfo[0].motto);
			}
		}else{
		            		layer.msg("获取用户信息失败！");
		            	}
		            }
		    	});
        }
       
        //添加验证规则
        form.verify({
            oldPwd : function(value, item){
                if(value != "123456"){
                    return "密码错误，请重新输入！";
                }
            },
            newPwd : function(value, item){
                if(value.length < 6){
                    return "密码长度不能小于6位";
                }
            },
            confirmPwd : function(value, item){
                if(!new RegExp($("#oldPwd").val()).test(value)){
                    return "两次输入密码不一致，请重新输入！";
                }
            }
        })

        //判断是否修改过头像，如果修改过则显示修改后的头像，否则显示默认头像
        if(window.sessionStorage.getItem('userFace')){
        	$("#userFace").attr("src",window.sessionStorage.getItem('userFace'));
        }else{
        	$("#userFace").attr("src","../../images/face.jpg");
        }

        //提交个人资料
        form.on("submit(changeUser)",function(data){
        	//组装用户信息
        	var userInfo = {};
        	userInfo.id=userId;
        	userInfo.userName=$("#userName").val();
        	userInfo.mobileNumber=$("#mobile").val();
        	if($("#province").val()==""){
        		userInfo.province = "";
        	}else{
        		userInfo.province=$("#province option:selected").text();
        	}
        	if($("#city").val()==""){
        		userInfo.province = "";
        	}else{
        		userInfo.city=$("#city option:selected").text();
        	}
        	if($("#county").val()==""){
        		userInfo.province = "";
        	}else{
        		userInfo.county=$("#county option:selected").text();
        	}
            userInfo.adress=$("#adress").val();
        	userInfo.birthday=$("#birthday").val();
        	userInfo.emailAdress=$("#email").val();
        	userInfo.hobby=$("#hobby").val();
        	userInfo.motto=$("#motto").val();
        	//提交到后台更新数据
        	$.ajax({
			        type: "POST",
			        url: "../../web/user/editUser",
			        data: userInfo ,
			        dataType: "json",
			        async:true,
			        success : function(data) {
					if (data.resultBean.flag == "success") {
				            layer.msg("用户信息更新成功！");
				            var index = parent.layer.index; //获取当前窗体索引
                            parent.layer.close(index); //执行关闭
		                }else{
		            		layer.msg("用户信息更新失败！");
		            	}
		            }
		    	});
        })

        

        //修改密码
        form.on("submit(changePwd)",function(data){
        	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
                layer.close(index);
                layer.msg("密码修改成功！");
                $(".pwd").val('');
            },2000);
        	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        })

})

 //加载省数据
function loadProvince(provincName,city,county) {
    var proHtml = '';
    var selected = "";
    var citys ="";
    for (var i = 0; i < areaData.length; i++) {
    	if(provincName==areaData[i].provinceName){
    		selected="selected";
    		citys = areaData[i].mallCityList;
    	}else{
    		selected = "";
    	}
        proHtml += '<option value="' + areaData[i].provinceCode + '_' + areaData[i].mallCityList.length + '_' + i + '" '+selected+'>' + areaData[i].provinceName + '</option>';
    }
    //初始化省数据
    $form.find('select[name=province]').append(proHtml);
    form.render();
    //初始化市数据以及默认值
    loadCity(city,county,citys);
    //注册选择省事件
    form.on('select(province)', function  (data) {
    	//根据选中省数据重新初始化市数据
        $form.find('select[name=area]').html('<option value="">请选择县/区</option>');
        var value = data.value;
        var d = value.split('_');
        var code = d[0];
        var count = d[1];
        var index = d[2];
        if (count > 0) {
            loadCity(city,county,areaData[index].mallCityList);
        } else {
            $form.find('select[name=city]').attr("disabled","disabled");
        }
    });
}
 //加载市数据
function loadCity(city,county,citys) {
    var cityHtml = '<option value="">请选择市</option>';
    var selected = "";
    var countys = "";
    for (var i = 0; i < citys.length; i++) {
    	 if(city==citys[i].cityName){
    		selected="selected";
    		countys = citys[i].mallAreaList;
    	}else{
    		selected = "";
    	}
        cityHtml += '<option value="' + citys[i].cityCode + '_' + citys[i].mallAreaList.length + '_' + i + '" '+selected+'>' + citys[i].cityName + '</option>';
    }
    $form.find('select[name=city]').html(cityHtml).removeAttr("disabled");
    form.render();
    //初始化县数据以及默认值
    loadArea(county,countys);
    form.on('select(city)', function(data) {
        var value = data.value;
        var d = value.split('_');
        var code = d[0];
        var count = d[1];
        var index = d[2];
        if (count > 0) {
            loadArea(county,citys[index].mallAreaList);
        } else {
            $form.find('select[name=area]').attr("disabled","disabled");
        }
    });
}
 //加载县/区数据
function loadArea(county,areas) {
    var areaHtml = '<option value="">请选择县/区</option>';
    var selected = "";
    for (var i = 0; i < areas.length; i++) {
    	 if(county==areas[i].areaName){
    		selected="selected=true";
    	}else{
    		selected = "";
    	}
        areaHtml += '<option value="' + areas[i].areaCode + '" '+selected+'>' + areas[i].areaName + '</option>';
    }
    $form.find('select[name=area]').html(areaHtml).removeAttr("disabled");
    form.render();
    form.on('select(area)', function(data) {
        //console.log(data);
    });
}