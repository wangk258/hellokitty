/** ************** js 判断浏览器类型 ****************** */
// 系统变量
var Sys = {
	ie : "",
	firefox : "",
	chrome : "",
	opera : "",
	safari : ""
};

window.onload = function() {
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua
			.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua
			.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua
			.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua
			.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

	/**
	 * 以下进行测试 if (Sys.chrome) document.write('Chrome: ' + Sys.chrome); if
	 * (Sys.opera) document.write('Opera: ' + Sys.opera); if (Sys.safari)
	 * document.write('Safari: ' + Sys.safari); if (Sys.ie) document.write('IE: ' +
	 * Sys.ie);
	 */
	//    
	// if (Sys.firefox){
	// t=Sys.firefox;
	// _log(Sys.firefox);
	// }
	//     
}

/** ************** js 发布需要注释的js ****************** */

/**
 * @desc 打印日志方法 仅支持火狐 项目发布需要关闭
 * @auth tangkun
 */

function _log(messag) {
	if (Sys.firefox)
		console.info("[调试信息]" + messag);
}

/** ************** easyui 消息框封装 ****************** */
function showSlimes(data) {

	try {
		console.info(typeof data.flag);
		if (typeof data.flag == "boolean") {
			beforeShowMsg(data);
		} else {
			onDataError();
		}
	} catch (err) {
		onDataError();
	} finally {

	}

}
function beforeShowMsg(data) {
	var setting = {
		title : "处理消息",
		msg : "操作成功",
		timeout : 2000,
		showType : 'slide'

	}, errMsg = "操作失败";
	// data的flag属性为必要属性
	$.extend(setting, data);
	// 成功
	if (!data.flag) {
		if (!data.msg) {
			setting.msg = errMsg;
		}
	}
	messagerShow(setting);
}
function onDataError() {
	var setting = {
		title : "",
		msg : "操作结果返回格式有误",
		timeout : 2000,
		showType : 'slide'
	}
	messagerShow(setting);
}

function messagerShow(setting) {
	$.messager.show( {
		title : setting.title,
		msg : setting.msg,
		timeout : setting.timeout,
		showType : setting.slide
	});
}

/**
 * [修改操作使用] 获取列表中选中的id
 * 
 * @return
 */
function getEditIds(dataGrid) {
	var arr = $("#" + dataGrid).datagrid("getSelections");
	if (arr.length > 1 || arr.length == 0) {
		$.messager.alert('警告', '请选择一条要修改的数据');
		return;
	}
	return ($(arr).attr("id"));
}
function getCheckedIds(dataGrid) {

	var arr = $("#" + dataGrid).datagrid("getSelections"), ids = "";
	if (!arr.length) {
		$.messager.alert('警告', '请选择删除的数据');
		return;
	}
	$.each(arr, function() {
		ids += "," + this.id
	})
	ids = ids.substr(1);
	return ids;
}
/**
 * 获取查询表单传递参数对象拼接
 * @param form 查询form表单
 * @return 拼接的json对象
 */
function GetQueryParams(form){
	var param = "";
	$.each($("#searchForm :input"),function(){
		if(this.id && this.value){
			//js 中json字符串需要使用双引号才有效
			param += ",\""+this.id+"\":\""+this.value+"\"";
		//时间类型的需要name，easyui未将解析的元素添加id属性
		}else if(this.name && this.value){
			param += ",\""+this.name+"\":\""+this.value+"\"";
		}
	});
	param =param.substring(1); 
	param = "{"+param+"}";
	return JSON.parse(param);
}
function getProName(){
	var i = 0
	return "getProName"+i;
}
/**
 * 公用删除列表行的方法
 * 
 * @param dataGrid
 *            数据列表id
 * @param a
 *            功能按钮对象
 * @return
 */
function RemoveMethod(dataGrid, a) {

	dataGrid = dataGrid || "dataGrid", ids = getCheckedIds(dataGrid);
	//验证
	if (!ids)
		return;
	
	//提示
	$.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {
        if (data) {
        	$.ajax( {
        		url : BASEPATH+"/"+$(a).attr("url") + "?ids=" + ids,
        		dataType : "json",
        		async : false,
        		success : function(data) {
        			
        			showSlimes(data);
        			if(data.flag)
        				$("#dataGrid").datagrid("reload");
        		},
        		error : function(e) {
        			showSlimes(e);
        		}
        	}); 
        }
    });
}
/**
 * 非标准格式的json数组转换为可以解析的tree
 * 
 * @param rows
 *            原json数组
 * @return 解析后的json数组
 */
function ConvertTree(rows) {

	function exists(rows, parentId) {
		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].id == parentId)
				return true;
		}
		return false;
	}

	var nodes = [];
	// 得到顶层节点
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row.parentId)) {
			nodes.push( {
				id : row.id,
				text : row.name
			});
		}
	}

	var toDo = [];
	for ( var i = 0; i < nodes.length; i++) {
		toDo.push(nodes[i]);
	}
	while (toDo.length) {
		var node = toDo.shift(); // 父节点
		// 得到子节点
		for ( var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row.parentId == node.id) {
				var child = {
					id : row.id,
					text : row.name
				};
				if (node.children) {
					node.children.push(child);
				} else {
					node.children = [ child ];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}
/** ************** js 扩展 ****************** */
// Array数组扩展方法
Array.prototype.indexOf = function(args) {
	var index = -1;
	alert(args);
	for ( var i = 0, l = this.length; i < l; i++) {
		if (this[i] === args) {
			index = i;
			break;
		}
	}
	return index;
}
/**
 * 获取定义枚举常量
 */
function GetCommstantForJSON(key) {
	var jsonObj = null;
	$
			.ajax( {
				url : _basePath
						+ "CommonConstantsAction.do?method=getConstantForJSON&constantKey="
						+ key,
				async : false,
				dataType : "json",
				success : function(data) {
					jsonObj = data;
				}
			});
	return jsonObj;
}

function AjaxUnAsync(url){
	var result = "";
	$.ajax({
		url:BASEPATH+"/"+url,
		async:false,
		dataType:"json",
		success:function(data){
			result = data;
		},
		error:function(e){
			$.messager.alert('警告',"请求出错");return;  
		}
	});
	return result;
}



function BindRowsEvent(dataGrid){
	
    setTimeout(function(){
        bindRowsEvent();
    }, 10);   
}
function bindRowsEvent(dataGrid){
	dataGrid = dataGrid || "dataGrid";
    var panel = $("#"+dataGrid).datagrid('getPanel');
    var rows = panel.find('tr[datagrid-row-index]');
    rows.unbind('click').bind('click',function(e){
        return false;
    });
    rows.find('div.datagrid-cell-check input[type=checkbox]').unbind().bind('click', function(e){
        var index = $(this).parent().parent().parent().attr('datagrid-row-index');
        if ($(this).attr('checked')){
            $('#'+dataGrid).datagrid('selectRow', index);
        } else {
            $('#'+dataGrid).datagrid('unselectRow', index);
        }
        e.stopPropagation();
    });
}
function OnlySelectCurrentRow(dataGrid){
	
	var rows = $(dataGrid).datagrid("getRows"); //这段代码是获取当前页的所有行。
	for(var i in rows){
		$(dataGrid).datagrid('unselectRow', i);
	}
	$(dataGrid).datagrid('selectRow', dataGrid.currentClickRowIndex);
}