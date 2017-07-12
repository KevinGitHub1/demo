 $(function () {  
     //左侧加载内容
	 var yongli=[{"name":"速度","code":"speed"},{"name":"管压","code":"guanya"},{"name":"信号","code":"xinhao"},{"name":"工况","code":"gongkuang"},{"name":"判定","code":"panding"}];
	 debugger;
	 var html="";
	  var flag = false;
	for(var i=0;i<yongli.length;i++){
		html+="<a class='layui-btn layui-btn-primary' draggable='true' id='"+yongli[i].code+"'>"+yongli[i].name+"</a>";
	}
	 $("#yongli")[0].innerHTML=html;
	 for(var i=0;i<yongli.length;i++){
		//注册事件
	$("#"+yongli[i].code)[0].ondragstart = function(ev){
                 ev = ev || window.event;
                 ev.dataTransfer.setData("id",ev.currentTarget.id);
                // ev.dataTransfer.setDragImage(getImg(),0,0);
             }
	}
	 
	var mycanvas = $("#mycanvas")[0];
	 mycanvas.ondragover = function(ev){
		 ev.dataTransfer.dropEffect="copy";
		   ev.stopPropagation();
          ev.preventDefault();
         }
         mycanvas.ondrop = function(ev){
        	debugger;
        	var x = ev.offsetX;
        	var y = ev.offsetY;
             var id =  ev.dataTransfer.getData("id");
             var newElement = $("#"+id).clone();
             newElement.attr('id', 'NewID'); 
             newElement.attr('draggable', 'false'); 
             newElement.attr('style','margin-top:'+y+'px;margin-left:'+x+'px;position:absolute');
             this.append(newElement[0]);
             flag = true;
        }
	
	 //右侧画布画底图
	 //画布上动态添加线框
});