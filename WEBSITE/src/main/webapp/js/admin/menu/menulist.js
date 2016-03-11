var navigations = AjaxUnAsync("/navigation/ajaxList.ajax"); 
$(function() {
	$('#dataGrid').datagrid({
		url : BASEPATH + '/editor/list.ajax',
		toolbar : '#toolbarDiv',
		frozenColumns : [ [ {
			field : 'id',
			checkbox : true,
			formatter : function(value, row, index) {
				return row.id;
			}
		} ] ],
		columns : [ [ {
			field : 'ckey',title : '菜单名称',width : "54.5%"
		} ,{
			field : 'pkey',title : '导航',width : "40%",
			formatter : function(value, row, index) {
				return navigations[value].name;
			}
		}] ],
		pagination : true,
		rownumbers : true,
		fit : true,
		nowrap : false,
		loadMsg : '数据加载中,请稍候......',
		pageSize : 15,// 每页显示的记录条数，默认为10
		// pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onClickRow : function(rowIndex, rowData) {
			this.currentClickRowIndex =rowIndex;
			OnlySelectCurrentRow(this);
		},
	   onDblClickRow: function(rowIndex, rowData) {
			$('#editMethod').click();
		}
		
	});
	

});

window.onresize = function(){
	setTimeout(domresize,300);
};
//改变表格宽高
function domresize(){
	$('#dataGrid').datagrid('resize',{  
		height:function(){
			
			alert(0);
		},
	});
}

function addMethod(a) {
	$('#addDiv').form('clear');
	$('#addDiv').show();
	
	$('#addDiv').dialog({
		title : '添加',
		width : 450,height: 300,
		closed : false,
		cache : false,
		modal : true,
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				$('#addForm').form('submit', {
					url : BASEPATH + "/" + $(a).attr("url"),
					onSubmit : function() {
						return $("#addForm").form('validate');
					},
					success : function(data) {
						data = JSON.parse(data);
						if (data.flag) {
							$('#addDiv').dialog('close');
							$("#dataGrid").datagrid("reload");
						}
						showSlimes(data);
					}
				});
			}
		}, {
			text : '取消',
			handler : function() {
				$('#addDiv').dialog('close');
			}
		} ]
	});
	setNavactions(0);
}

function removeMethod(a) {
	RemoveMethod("dataGrid", a);
}
function editMethod(a) {
	$('#addDiv').show();
	var id = getEditIds("dataGrid");
	if (!id)
		return;
	$('#addForm').form('load', BASEPATH + "/editor/loadEditById.ajax?id=" + id);
	setNavactions(id);
	$('#addDiv').dialog({
		title : $(a).text(),
		width : 450,
		height : 300,
		closed : false,
		cache : false,
		modal : true,
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				$('#addForm').form('submit', {
					url : BASEPATH + "/" + $(a).attr("url"),
					onSubmit : function() {
						return $("#addForm").form('validate');
					},
					success : function(data) {
						data = JSON.parse(data);
						showSlimes(data);
						if (data.flag) {
							$("#dataGrid").datagrid("reload");
							$('#addDiv').dialog('close');
						}
					}
				});
			}
		}, {
			text : '取消',
			handler : function() {
				$('#addDiv').dialog('close');
			}
		} ]
	});
}
function setNavactions(id){
	var html ="";
	$.each(navigations,function(){
		if(this.id == id){
			html+="<option value='"+this.id+"' selected='selected'>"+this.name+"</option>"
		}else{
			html+="<option value='"+this.id+"'>"+this.name+"</option>"
		}
	})
	$("#pkey").html(html);
}


function setUrl(select){
	//编辑
	if($(select).val()==0){
		if($("#ckey").val()){
			$("#url").val("/admin/pages/editor/editor.jsp?pkey="+$("#pkey").val()+"&ckey="+$("#ckey").val());
		}else{
			alert("请先填写菜单名称！"); return;
		}
	}else{
		$("#url").val("");
	}
}
function setUrlByNavi(select){
	if($("#navigationType").val()==0){
		if($("#ckey").val()){
			$("#url").val("/admin/pages/editor/editor.jsp?pkey="+$("#pkey").val()+"&ckey="+$("#ckey").val());
		}else{
			alert("请先填写菜单名称！"); return;
		}
	}
}




