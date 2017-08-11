var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;
var userId = localStorage.getItem("userid");
		if(userId==null){
			location.href="../../login.html";
			return;
		}
 	var addUserArray = [],addUser;
 	//添加验证规则
        form.verify( {
								username : function(value) {
        	                 if (value.length<6) {
										return '用户名不能少于6个字符';
									}
        	                 if (value.length>20) {
										return '用户名不能超过20个字符';
									}
									if (!new RegExp(
											"^[a-zA-Z][a-zA-Z0-9_]*$")//^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$
											.test(value)) {
										return '用户名只能包含字母、数字和下划线！且不能以下划线开始！';
									}
								}
							});
// 	form.on("submit(addUser)",function(data){
// 		//是否添加过信息
//	 	if(window.sessionStorage.getItem("addUser")){
//	 		addUserArray = JSON.parse(window.sessionStorage.getItem("addUser"));
//	 	}
// 		addUser = '{"usersId":"'+ new Date().getTime() +'",';//id
// 		addUser += '"userName":"'+ $(".userName").val() +'",';  //登录名
// 		addUser += '"userEmail":"'+ $(".userEmail").val() +'",';	 //邮箱
// 		addUser += '"userSex":"'+ data.field.sex +'",'; //性别
// 		addUser += '"userStatus":"'+ userStatus +'",'; //会员等级
// 		addUser += '"userGrade":"'+ userGrade +'",'; //会员状态
// 		addUser += '"userEndTime":"'+ formatTime(new Date()) +'"}';  //登录时间
// 		console.log(addUser);
// 		addUserArray.unshift(JSON.parse(addUser));
// 		window.sessionStorage.setItem("addUser",JSON.stringify(addUserArray));
// 		//弹出loading
// 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
//        setTimeout(function(){
//            top.layer.close(index);
//			top.layer.msg("用户添加成功！");
// 			layer.closeAll("iframe");
//	 		//刷新父页面
//	 		parent.location.reload();
//        },2000);
// 		return false;
// 	})
 	form.on("submit(addUser)",function(data){
    	var loginName = $("#login_name").val();
	    var email = $("#email").val();
	    var user =  {
			        	loginName : loginName,
			        	emailAdress : email
		        	}
 	        	$.ajax({
			        type: "POST",
			        url: "../../web/user/addUser",
			        data:user,
			        dataType: "json",
			        async:false,
			        success: function(data){
			        	var currentUser = data.resultBean.content;
		            	if(data.resultBean.flag=="success") {
		            		top.layer.msg("用户添加成功！");
                            var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
                            parent.layer.close(index); //执行关闭
 		            		location.href="./allUsers.html";
		            	}else{
		            		top.layer.msg("用户添加失败！");
		            	}
		            }
		    	});
    });
	  
})

//格式化时间
function formatTime(_time){
    var year = _time.getFullYear();
    var month = _time.getMonth()+1<10 ? "0"+(_time.getMonth()+1) : _time.getMonth()+1;
    var day = _time.getDate()<10 ? "0"+_time.getDate() : _time.getDate();
    var hour = _time.getHours()<10 ? "0"+_time.getHours() : _time.getHours();
    var minute = _time.getMinutes()<10 ? "0"+_time.getMinutes() : _time.getMinutes();
    return year+"-"+month+"-"+day+" "+hour+":"+minute;
}
