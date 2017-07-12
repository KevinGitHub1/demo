 $(function () {  
//	var myChart = echarts.init(document.getElementById('chart1'));
//	// 过渡---------------------
//myChart.showLoading({
//    text: '正在努力的读取数据中...',    //loading话术
//});
//
//myChart.hideLoading();
//// 图表使用-------------------
//var option = {
//    legend: {                                   // 图例配置
//        padding: 5,                             // 图例内边距，单位px，默认上下左右内边距为5
//        itemGap: 10,                            // Legend各个item之间的间隔，横向布局时为水平间隔，纵向布局时为纵向间隔
//        data: ['ios', 'android']
//    },
//    tooltip: {                                  // 气泡提示配置
//        trigger: 'item',                        // 触发类型，默认数据触发，可选为：'axis'
//    },
//    xAxis: [                                    // 直角坐标系中横轴数组
//        {
//            type: 'category',                   // 坐标轴类型，横轴默认为类目轴，数值轴则参考yAxis说明
//            data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
//        }
//    ],
//    yAxis: [                                    // 直角坐标系中纵轴数组
//        {
//            type: 'value',                      // 坐标轴类型，纵轴默认为数值轴，类目轴则参考xAxis说明
//            boundaryGap: [0.1, 0.1],            // 坐标轴两端空白策略，数组内数值代表百分比
//            splitNumber: 4                      // 数值轴用，分割段数，默认为5
//        }
//    ],
//    series: [
//        {
//            name: 'ios',                        // 系列名称
//            type: 'line',                       // 图表类型，折线图line、散点图scatter、柱状图bar、饼图pie、雷达图radar
//            data: [112, 23, 45, 56, 233, 343, 454, 89, 343, 123, 45, 123]
//        },
//        {
//            name: 'android',                    // 系列名称
//            type: 'line',                       // 图表类型，折线图line、散点图scatter、柱状图bar、饼图pie、雷达图radar
//            data: [45, 123, 145, 526, 233, 343, 44, 829, 33, 123, 45, 13]
//        }
//    ]
//};
//debugger;
//myChart.setOption(option);
//// 增加些数据------------------
//option.legend.data.push('win');
//option.series.push({
//        name: 'win',                            // 系列名称
//        type: 'line',                           // 图表类型，折线图line、散点图scatter、柱状图bar、饼图pie、雷达图radar
//        data: [112, 23, 45, 56, 233, 343, 454, 89, 343, 123, 45, 123]
//});
//myChart.setOption(option);
// 图表清空-------------------
//myChart.clear();
// 图表释放-------------------
//myChart.dispose();
	 
	         // 基于准备好的dom，初始化echarts实例  
        var myChart = echarts.init(document.getElementById('chart1'));  
        var myChart2 = echarts.init(document.getElementById('chart2'));  
        // 指定图表的配置项和数据  
        var option = {
    title : {
        text: '未来一周气温变化',
        subtext: '纯属虚构'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['最高气温','最低气温']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['周一','周二','周三','周四','周五','周六','周日']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} °C'
            }
        }
    ],
    series : [
        {
            name:'最高气温',
            type:'line',
            data:[11, 11, 15, 13, 12, 13, 10],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'最低气温',
            type:'line',
            data:[1, -2, 2, 5, 3, 2, 0],
            markPoint : {
                data : [
                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};
     var option2 = {
    title : {
        text: '某站点用户访问来源',
        subtext: '纯属虚构',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true, 
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        x: '25%',
                        width: '50%',
                        funnelAlign: 'left',
                        max: 1548
                    }
                }
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'直接访问'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1548, name:'搜索引擎'}
            ]
        }
    ]
};
                                     
  myChart.setOption(option);  
  myChart2.setOption(option2); 
        $("#b1").click(function () {  
            myChart.clear();  
            myChart2.clear();  
            // 使用刚指定的配置项和数据显示图表。  
            myChart.setOption(option);  
            myChart2.setOption(option2); 
        })   
});