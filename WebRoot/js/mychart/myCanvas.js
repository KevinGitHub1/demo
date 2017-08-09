 $(function () {  
	
  var layer = layui.layer; 
  
  layui.tree({
    elem: '#demo1' //指定元素
    ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
    ,click: function(item){ //点击节点回调
      layer.msg('当前节名称：'+ item.name + '<br>全部参数：'+ JSON.stringify(item));
      console.log(item);
    }
    ,nodes: [ //节点
      {
        name: '常用文件夹'
        ,id: 1
        ,alias: 'changyong'
        ,children: [
          {
            name: '所有未读（设置跳转）'
            ,id: 11
            ,href: 'http://www.layui.com/'
            ,alias: 'weidu'
          }, {
            name: '置顶邮件'
            ,id: 12
          }, {
            name: '标签邮件'
            ,id: 13
          }
        ]
      }, {
        name: '我的邮箱'
        ,id: 2
        ,spread: true
        ,children: [
          {
            name: 'QQ邮箱'
            ,id: 21
            ,spread: true
            ,children: [
              {
                name: '收件箱'
                ,id: 211
                ,children: [
                  {
                    name: '所有未读'
                    ,id: 2111
                  }, {
                    name: '置顶邮件'
                    ,id: 2112
                  }, {
                    name: '标签邮件'
                    ,id: 2113
                  }
                ]
              }, {
                name: '已发出的邮件'
                ,id: 212
              }, {
                name: '垃圾邮件'
                ,id: 213
              }
            ]
          }, {
            name: '阿里云邮'
            ,id: 22
            ,children: [
              {
                name: '收件箱'
                ,id: 221
              }, {
                name: '已发出的邮件'
                ,id: 222
              }, {
                name: '垃圾邮件'
                ,id: 223
              }
            ]
          }
        ]
      }
      ,{
        name: '收藏夹'
        ,id: 3
        ,alias: 'changyong'
        ,children: [
          {
            name: '爱情动作片'
            ,id: 31
            ,alias: 'love'
          }, {
            name: '技术栈'
            ,id: 12
            ,children: [
              {
                name: '前端'
                ,id: 121
              }
              ,{
                name: '全端'
                ,id: 122
              }
            ]
          }
        ]
      }
    ]
  });
  
     //左侧加载内容
	 //元素定义{name:"speed",type:"speed",text:"速度",data:[{name:"属性1",value:"jashdjs"},{name:"属性2",value:"ertertert"}]}
	 var yongli=[{name:"speed",text:"速度",type:"speed",data:[{name:"属性1",value:"jashdjs"},{name:"属性2",value:"ertertert"}]},
		 {name:"guanya",text:"管压",type:"guanya",data:[{name:"属性1",value:"jashdjs"},{name:"属性2",value:"ertertert"}]},
		 {name:"xinhao",text:"信号",type:"xinhao",data:[{name:"属性1",value:"jashdjs"},{name:"属性2",value:"ertertert"}]},
		 {name:"gongkuang",text:"工况",type:"gongkuang",data:[{name:"属性1",value:"jashdjs"},{name:"属性2",value:"ertertert"}]},
		 {name:"panding",text:"判定",type:"panding",data:[{name:"属性1",value:"jashdjs"},{name:"属性2",value:"ertertert"}]}];
	 var html="";
	for(var i=0;i<yongli.length;i++){
		html+="<a class='layui-btn layui-btn-primary' draggable='true' id='"+yongli[i].name+"' >"+yongli[i].text+"</a>";
	}
	 $("#yongli")[0].innerHTML=html;
	 var currentObj="";
	 for(var i=0;i<yongli.length;i++){
		//注册事件
	$("#"+yongli[i].name)[0].ondragstart = function(ev){
                 ev = ev || window.event;
                 ev.dataTransfer.setData("id","");
                 //debugger;
                 ev.dataTransfer.setData("name",ev.currentTarget.id);
                // ev.dataTransfer.setDragImage(getImg(),0,0);
                currentObj =ev.target;
             }
	}
	 var x=0;
	 //判断当前位置可以放置与否
	 function getWeiZhi(){
		 if(x>200){
			return true;
		 } else{
			return false;
		 }
	 }
	 
	 
	 /**
	  * 这个方法用来储存每个用例元素
	  * @param {Object} ev
	  */
	 // 
    function caseElement(id,name,x, y,color,data) {
	  this.id = id;
      this.x = x;
      this.y = y;
      this.name = name;
      this.width = 45;
      this.hight = 30;
      this.data = data;
      this.color = color;
      this.isSelected = false;
    }
    $("#fangda")[0].onclick=function(e){
    	scale(1.2,1.2);
    }
     $("#suoxiao")[0].onclick=function(e){
    	scale(0.8,0.8);
    }
// 保存画布上所有的用例元素
    var caseElements = [];

    var elementType = [{name:"xinhao",text:"信号",x1:100,x2:750,y1:50,y2:100},{name:"speed",text:"速度",x1:100,x2:750,y1:100,y2:150},
    	{name:"gongkuang",text:"工况",x1:100,x2:750,y1:150,y2:200},{name:"yali",text:"压力",x1:100,x2:750,y1:250,y2:300}];
       var  scaleX =1;
       var scaleY =1;
	var mycanvas = $("#canvasId")[0];
	   //右侧画布画底图
		var canvas = $("#canvasId")[0];
       var context = canvas.getContext('2d');//获取画布
      //元素拖拽移动过程中触发事件控制可拖拽的区域
	 mycanvas.ondragover = function(ev){
		 	 var x = ev.offsetX;
		 	 var y = ev.offsetY;
		 	ev.dataTransfer.dropEffect="move";
		   ev.stopPropagation();
          ev.preventDefault();
         }
	 //拖动元素放下时触发事件
	   mycanvas.ondrop = function(ev){
        	var x = ev.offsetX;
        	var y = ev.offsetY;
        	ev.preventDefault();
              var name =  ev.dataTransfer.getData("name");
              var data =  getData(name);
             if(name!=""){//从左侧工具框拖入，新建元素
            	 var newId = randomString();
            var obj = new caseElement(newId,data.name,x,y,'fff',data.data);
            obj.width=obj.width*scaleX;
            obj.hight=obj.hight*scaleY;
             drawDiagonal(obj,1);//绘制新元素
             }
             else{
            	 //更新选中元素的位置
            	 var selection = getSelectElements();
            	 if (selection.length>0) {
            		 for(var j=0;j<selection.length;j++){
            			 var k=selection[j];
            			 caseElements[k].x=x;
            			 caseElements[k].y=y;
            		 }
            	 }
            	 //canvase内部元素移动位置,重绘
            	 draw();
             }
             //新建个元素放在相应位置
//             var newElement = $("#"+id).clone();
//             newElement.attr('id', 'NewID'); 
//             newElement.attr('draggable', 'false'); 
//             newElement.attr('style','margin-top:'+y+'px;margin-left:'+x+'px;position:absolute');
             //新元素 点击事件绑定
//             newElement.click(function(){
//            	 alert("点击了"+id);
//             });
//             if(x>10){
//            	   //mycanvas.children.Add(newElement[0]);
//             }else{
//            	 //alert("此处不可以放置元素");
//            	 $("#"+id).attr('draggable', 'false');
//             }
//           $("#"+id).attr('draggable', 'true');
//             x=0;
//             newElement.trigger("click");
             
        }
	 //获取选中元素下标
	function getSelectElements(){
		 var selection=[];
		 for(var i=0;i<caseElements.length;i++){
			 if(caseElements[i].isSelected){
				 selection.push(i);
			 }
		 }
		 return selection;
	 }
	//根据选中ID设置当前选中元素
	function setSelectElement(id){
		 for(var i=0;i<caseElements.length;i++){
			 if((!caseElements[i].selected)&&caseElements[i].id==id){
				 caseElements[i].isSelected=true;
			 }else{
				  caseElements[i].isSelected=false;
			 }
		 }
		 return caseElements;
	}
	//根据当前点击点的坐标获取点击的元素并设置选中
	function getSelectionElementByPoint(p){
		var selection =[];
		 for(var i=0;i<caseElements.length;i++){
			 var x = caseElements[i].x;
			  var y = caseElements[i].y;
			 var width = caseElements[i].width;
			 var hight = caseElements[i].hight;
			 if(p.x>=x&&p.y>=y&&p.x<=(x+width)&&p.y<=y+hight){
				 caseElements[i].isSelected=true;
				 selection.push(caseElements[i]);
			 }else{
				  caseElements[i].isSelected=false;
			 }
		 }
		  return selection;
	}
	//根据元素名称获取元素各个相关属性信息
	function getData (name){
		for(var i=0;i<yongli.length;i++){
			if(name==yongli[i].name){
				return yongli[i];
			}
		}
	}
       

        drawDashLine(context,scaleX,scaleY);

         //画坐标轴线
         function drawDashLine(ctx,x,y){
        	 //画坐标轴线
       ctx.beginPath(); 
      ctx.moveTo(45*x, 400*y); 
      ctx.lineTo(750*x, 400*y);
      ctx.moveTo(75*x, 5*y); 
      ctx.lineTo(75*x, 425*y);
      ctx.stroke();
     
      //写名称画虚线
      for(var i=0;i<elementType.length;i++){
    	  ctx.strokeText(elementType[i].text,10*x,(80+50*i)*y,35*y);
    	  ctx.save();
    	  ctx.setLineDash([15,5]); 
          ctx.lineWidth = 0.5; 
          ctx.beginPath(); 
          ctx.moveTo(10*x, (100+50*i)*y); 
          ctx.lineTo(750*x, (100+50*i)*y);
          ctx.stroke();
          ctx.closePath();
          ctx.restore();
      }

    }
       
canvas.onmousedown = canvasClick;
canvas.onmousemove = dragElement;
var previousSelectedCircle;
//画布点击事件
 function canvasClick(e) {
      // 取得画布上被单击的点
//      var clickX = e.pageX - canvas.offsetLeft;
//      var clickY = e.pageY - canvas.offsetTop;
     p = getEventPosition(e);
    var selection = getSelectionElementByPoint(p);
    if(selection.length>0){
    	$("#shuxing").empty();
    for(var i=0;i<selection.length;i++){
    	
    	var shuxing = selection[i].data;
    		$("#shuxing").append("<div>元素名称:"+selection[i].name+"</div>");
    	if(shuxing.length>0){
    		//动态拼装属性信息以及相关输入框
    		 for(var j=0;j<shuxing.length;j++){
    			 $("#shuxing").append("<div>"+shuxing[j].name+":"+shuxing[j].value+"</div>");
    			}
    	}
    
    }
    }
    }
 var isDragging = false;
 
    function stopDragging() {
      isDragging = false;
    }
function dragElement(e) {
	          var x = e.offsetX;
		 	  var y = e.offsetY;
       var selections = getSelectElements();

        if (selections.length>0) {
          // 将选中元素移动到鼠标位置
for(var i=0;i<selections.length;i++){
	var index = selections[i];
	caseElements[index].x = x;
    caseElements[index].y = y;
}         
// 更新画布中选中的元素
        draw();
      }
    }
//获取当前点击位置信息
function getEventPosition(ev){
  var x, y;
  if (ev.layerX || ev.layerX == 0) {
    x = ev.layerX;
    y = ev.layerY;
  } else if (ev.offsetX || ev.offsetX == 0) { // Opera
    x = ev.offsetX;
    y = ev.offsetY;
  }
  return {x: x, y: y};
}
//重绘画布元素信息
function draw(){
  var who = [];
  context.clearRect(0, 0, mycanvas.width, mycanvas.height);
  drawDashLine(context,scaleX,scaleY);
  caseElements.forEach(function(v, i){
	  drawDiagonal(v,2);
    });
}

//画布内容放大缩小
function scale(x,y){
//x、y同时放大或缩小
	        //context.save();  //先保存下绘图状态
            //context.translate(150, 150);  //进行位移操作
	//更新当前画布内所有元素的坐标
	scaleX=scaleX*x;
	scaleY=scaleY*y;
  for(var i=0;i<caseElements.length;i++){
	  caseElements[i].x=caseElements[i].x*x;
	  caseElements[i].y=caseElements[i].y*y;
	  caseElements[i].width=caseElements[i].width*x;
	  caseElements[i].hight=caseElements[i].hight*y;
  }
  //清空画布内容
   context.clearRect(0, 0, mycanvas.width, mycanvas.height);
   context.save(); 
   //context.scale(x, y);  //将绘制元素放大x倍
  drawDashLine(context,scaleX,scaleY);
  caseElements.forEach(function(v, i){
	  drawDiagonal(v,2);
    });
	
}
 //给画布上绘制各元素
	function drawDiagonal(obj,type){		
		context.strokeStyle = obj.color; 
//		if(obj.isSelected){
//			 context.lineWidth = 1;
//        }
//        else {
//          context.lineWidth = 0.5;
//        }
		context.beginPath();
        context.rect(obj.x,obj.y,obj.width,obj.hight);//坐标变换已处理
        context.stroke();
        context.strokeText(obj.name,obj.x+10*scaleX,obj.y+10*scaleY,20*scaleX);
       if(type==1){//新增元素绘制
    	 caseElements.push(obj);  
       }
	}
	//绘制图片实例
	function drawimage(){
		var image =  new Image();
		image.src= "../../images/userface4.jpg";
		context.drawImage(image,10,10);
	}
	//获取随机ID信息
	function randomString(len) {
　　len = len || 32;
　　var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
　　var maxPos = $chars.length;
　　var pwd = '';
　　for (i = 0; i < len; i++) {
　　　　pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
　　}
　　return pwd;
}
	//画3d图形
//	function drawDiagonal3D(){
//		var canvas = $("#mycanvas")[0];
//		var gl = canvas.getContext('webgl');
//		gl.clearColor(0.0, 0.0, 0.0, 1.0);  
//    gl.clear(gl.COLOR_BUFFER_BIT); 
//	}
});