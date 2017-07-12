var $,tab,skyconsWeather;
layui.config({
	base : "js/"
}).use(['bodyTab','form','element','layer','jquery'],function(){
	var form = layui.form(),
		layer = layui.layer,
		element = layui.element();
		$ = layui.jquery;
		tab = layui.bodyTab();
		var userId = $.session.get("currentUserId");
		var loginName = $.session.get("currentLoginName");
    $("#currentUser").html(loginName);
    //退出系统
    function loginOut(){
    	layer.open({
    		title:'退出系统',
        content: '您确认退出系统吗？',
        btn: ['确认', '取消'],
        shadeClose: false,
        yes: function(){
            //确认后返回登录界面
    		 layui.data('navbarCache' , null);
    		 layer.closeAll();
             localStorage.clear();
    		location.href="./login.html";
        }, no: function(){
           //取消关闭此页面
        }
    });
    }
   
    $("#loginOut").on("click",function(){
		window.sessionStorage.setItem("#loginOut",true);
		loginOut();
	})
	//修改密码
	 $("#editPassword").attr("data-url","page/user/changePwd.html?name="+loginName);
	//修改个人信息
    $("#editUserInfo").attr("data-url","page/user/userInfo.html?id="+userId);
	// 锁屏控制
    $('#lock').mouseover(function(){
   	   layer.tips('请按Alt+L快速锁屏！', '#lock', {
             tips: [1, '#FF5722'],
             time: 2000
       });
    });
    // 快捷键锁屏设置
    $(document).keydown(function(e){
         if(e.altKey && e.which == 76){
         	 lockSystem();
         }
    });
   //锁屏
    var index="";
	function lockPage(){
	 index =	layer.open({
			title : false,
			type : 1,
			content : $("#lock-box"),
			closeBtn : 0,
			shade : 0.9
		});
	 layer.full(index); 
	 startTimer();
	}
	$(".lockcms").on("click",function(){
		window.sessionStorage.setItem("lockcms",true);
		lockPage();
	})
	// 判断是否显示锁屏
	if(window.sessionStorage.getItem("lockcms") == "true"){
		lockPage();
	}
	
	$(document).on('keydown', function() {
		if(event.keyCode == 13) {
			$("#unlock").click();
		}
	});
   // 解锁进入系统
   $("#unlock").on("click",function(){
       if($("#lockPwd").val() == ''){
			layer.msg("请输入解锁密码！");
			return;
		}else{
			if($("#lockPwd").val() == "123456"){
				window.sessionStorage.setItem("lockcms",false);
				$("#lockPwd").val('');
				layer.close(index);
			}else{
				layer.msg("密码错误，请重新输入！");
				return;
			}
		}
   });

    function startTimer(){
   	    var today=new Date();
        var h=today.getHours();
        var m=today.getMinutes();
        var s=today.getSeconds();
        m = m < 10 ? '0' + m : m;
        s = s < 10 ? '0' + s : s;
        $('#time').html(h+":"+m+":"+s);
        t=setTimeout(function(){startTimer()},500);
   }
 //清除缓存
    $('#clearCached').on('click', function () {
        layui.data('navbarCache' , null);
        localStorage.clear();
        layer.alert('缓存清除完成!本地存储数据也清理成功！', { icon: 1, title: '系统提示' }, function () {
            location.reload();//刷新
        });
    });

    // 设置主题
    var themeName = localStorage.getItem('themeName');
	if (themeName) {
		$("body").attr("class", "");
		$("body").addClass("larryTheme-" + themeName);
	}
    $('#larryTheme').on('click',function(){
        var themeName  = localStorage.getItem('themeName');
        layer.open({
               type: 1,
			   title: false,
			   closeBtn: true,
			   shadeClose: false,
			   shade: 0.35,
			   area: ['450px', '300px'],
			   isOutAnim: true,
			   resize:false,
			   anim: Math.ceil(Math.random() * 6),
			   content: $('#LarryThemeSet').html(),
			   success: function(layero,index){
			   	   if(themeName){
			   	   	   $("#themeName option[value='"+themeName+"']").attr("selected","selected");
			   	   }
			   	   form.render();
			   }
        });
        var themeValue="";
        // 主题设置
         form.on('submit(submitlocal)',function(data){
            localStorage.setItem('themeName',themeValue);//themeName:themeValue
            if(themeName!=themeValue){//更新主题
            	$("body").attr("class", "");
            	$("body").addClass("larryTheme-" + themeName);
            	location.reload();
            }
         })
      // 选中值
        form.on('select(larryTheme)', function (data) {
            themeValue = data.value;
            form.render('select');
        });
    });
   
   
   // 全屏切换
   $('#FullScreen').bind('click',function(){
       var fullscreenElement =
            document.fullscreenElement ||
            document.mozFullScreenElement ||
            document.webkitFullscreenElement;
        if (fullscreenElement == null) {
            entryFullScreen();
            $(this).html('<i class="larry-icon larry-quanping"></i>退出全屏');
        } else {
            exitFullScreen();
             $(this).html('<i class="larry-icon larry-quanping"></i>全屏');
        }
   });

   // 进入全屏：
   function entryFullScreen(){
   	   var docE = document.documentElement;
   	   if(docE.requestFullScreen){
   	   	  docE.requestFullScreen();
   	   }else if(docE.mozRequestFullScreen){
   	   	  docE.mozRequestFullScreen();
   	   }else if(docE.webkitRequestFullScreen){
   	   	  docE.webkitRequestFullScreen();
   	   }
   }

   // 退出全屏
	function exitFullScreen() {
		var docE = document;
		if (docE.exitFullscreen) {
			docE.exitFullscreen();
		} else if (docE.mozCancelFullScreen) {
			docE.mozCancelFullScreen();
		} else if (docE.webkitCancelFullScreen) {
			docE.webkitCancelFullScreen();
		}
	}
	//手机设备的简单适配
	var treeMobile = $('.site-tree-mobile'),
		shadeMobile = $('.site-mobile-shade')

	treeMobile.on('click', function(){
		$('body').addClass('site-mobile');
	});

	shadeMobile.on('click', function(){
		$('body').removeClass('site-mobile');
	});

	// 添加新窗口
	$(".layui-nav .layui-nav-item a").on("click",function(){
		addTab($(this));
		$(this).parent("li").siblings().removeClass("layui-nav-itemed");
	})

	//公告层
	function showNotice(){
		layer.open({
	        type: 1,
	        title: "系统公告", //不显示标题栏
	        closeBtn: false,
	        area: '310px',
	        shade: 0.8,
	        id: 'LAY_layuipro', //设定一个id，防止重复弹出
	        btn: ['火速围观'],
	        moveType: 1, //拖拽模式，0或者1
	        content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p>最近偶然发现贤心大神的layui框架，瞬间被他的完美样式所吸引，虽然功能不算强大，但毕竟是一个刚刚出现的框架，后面会慢慢完善的。很早之前就想做一套后台模版，但是感觉bootstrop代码的冗余太大，不是非常喜欢，自己写又太累，所以一直闲置了下来。直到遇到了layui我才又燃起了制作一套后台模版的斗志。由于本人只是纯前端，所以页面只是单纯的实现了效果，没有做服务器端的一些处理，可能后期技术跟上了会更新的，如果有什么问题欢迎大家指导。谢谢大家。</p><p>在此特别感谢Beginner和Paco，他们写的框架给了我很好的启发和借鉴。希望有时间可以多多请教。</p></div>',
	        success: function(layero){
				var btn = layero.find('.layui-layer-btn');
				btn.css('text-align', 'center');
				btn.on("click",function(){
					window.sessionStorage.setItem("showNotice","true");
				})
				if($(window).width() > 432){  //如果页面宽度不足以显示顶部“系统公告”按钮，则不提示
					btn.on("click",function(){
						layer.tips('系统公告躲在了这里', '#showNotice', {
							tips: 3
						});
					})
				}
	        }
	    });
	}
	//判断是否处于锁屏状态(如果关闭以后则未关闭浏览器之前不再显示)
	if( window.sessionStorage.getItem("showNotice") != "true"){
		showNotice();
	}
	$(".showNotice").on("click",function(){
		showNotice();
	})

	//刷新后还原打开的窗口
	if(window.sessionStorage.getItem("menu") != null){
		menu = JSON.parse(window.sessionStorage.getItem("menu"));
		curmenu = window.sessionStorage.getItem("curmenu");
		var openTitle = '';
		for(var i=0;i<menu.length;i++){
			openTitle = '';
			if(menu[i].icon.split("-")[0] == 'icon'){
				openTitle += '<i class="iconfont '+menu[i].icon+'"></i>';
			}else{
				openTitle += '<i class="layui-icon">'+menu[i].icon+'</i>';
			}
			openTitle += '<cite>'+menu[i].title+'</cite>';
			openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="'+menu[i].layId+'">&#x1006;</i>';
			element.tabAdd("bodyTab",{
				title : openTitle,
		        content :"<iframe src='"+menu[i].href+"' data-id='"+menu[i].layId+"'></frame>",
		        id : menu[i].layId
			})
			//定位到刷新前的窗口
			if(curmenu != "undefined"){
				if(curmenu == '' || curmenu == "null"){  //定位到后台首页
					element.tabChange("bodyTab",'');
				}else if(JSON.parse(curmenu).title == menu[i].title){  //定位到刷新前的页面
					element.tabChange("bodyTab",menu[i].layId);
				}
			}else{
				element.tabChange("bodyTab",menu[menu.length-1].layId);
			}
		}
	}

})
//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}
 //获取当前天气情况
    function getTianQi(){
    	//https://api.seniverse.com/v3/weather/now.json?key=ebfyyscheyqlqhkz&location=zhengzhou&language=zh-Hans&unit=c
//    	$.ajax({
//			        type: "GET",
//			        url: "https://api.seniverse.com/v3/weather/now.jsonp?key=ebfyyscheyqlqhkz&location=zhengzhou&language=zh-Hans&unit=c",
//			        dataType: "jsonp",
//			        jsonp:"jsoncallback", 
//			        success : function(results) {
//					if (results) {
//				            layer.msg("用户信息更新成功！");
//				          
//		                }else{
//		            		layer.msg("用户信息更新失败！");
//		            	}
//		            }
//		    	});
    }
//捐赠弹窗
function donation(){
	layer.tab({
		area : ['260px', '367px'],
		tab : [{
			title : "微信",
			content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='images/wechat.jpg'></div>"
		},{
			title : "支付宝",
			content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='images/alipay.jpg'></div>"
		}]
	})
}
$(function(){ 
     getTianQi();
});