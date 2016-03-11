$(function() {
	$('#dataGrid').datagrid({
		url : BASEPATH + '/navigation/list.ajax',
		toolbar : '#toolbarDiv',
		frozenColumns : [ [ {
			field : 'id',
			checkbox : true,
			formatter : function(value, row, index) {
				return row.id;
			}
		} ] ],
		columns : [ [ {
			field : 'name',
			title : '导航名',
			width : "20%"
		}, {
			field : 'url',title : '地址',width : "54.5%"
		} ,{
			field : 'sort',title : '排序',width : "20%",
			formatter : function(value, row, index) {
				var data=$('#dataGrid').datagrid('getData');
				//索引为0则向上的禁用
				var sortHtml = "";
				if(index != 0){
					sortHtml +="<a href='javascript:void(0)'" ;
					sortHtml += " onclick=changeSort('"+row.id+"','-1')"
					sortHtml+="><em class='icon_up'></em>" ;
					sortHtml+="<span style='float:left;margin-right:15px;color:gray'>上移<span></a>";
				}
				sortHtml+="&emsp;&emsp;";
				if(index != (data.rows.length-1)){
					sortHtml += "<a href='javascript:void(0)'" ;
					sortHtml += " onclick=changeSort('"+row.id+"','1')><em class='icon_down'></em><span style='float:left;color:gray'>下移</span></a>";
				}
			
				return sortHtml;
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
		width : 400,
		height: 280,
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
}

function removeMethod(a) {
	RemoveMethod("dataGrid", a);
}
function editMethod(a) {
	$('#addDiv').show();
	var id = getEditIds("dataGrid");
	if (!id)
		return;
	$('#addForm').form('load', BASEPATH + "/navigation/loadEdit.ajax?id=" + id);
	$('#addDiv').dialog({
		title : $(a).text(),
		width : 400,
		height : 200,
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
function changeSort(id,value){
	var data = AjaxUnAsync("navigation/changeSort.ajax?id="+id+"&loop="+value);
	showSlimes(data);
	if(data.flag){
		$('#dataGrid').datagrid('load');
	}
}