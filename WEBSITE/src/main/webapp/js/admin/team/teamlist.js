
var editor = null, editorImage =null;
$(function(){
	loadGrid();
});


function loadGrid(){
	$('#dataGrid').datagrid({    
	    url:BASEPATH+'/team/list.ajax',  
	    toolbar: '#toolbarDiv',
	    frozenColumns : [ [ {
			field : 'id',
			checkbox : true,
			formatter : function(value, row, index) {
				return row.id;
			}
		} ] ],
	    columns:[[    
	        {field:'name',title:'团队',width:100,},
	        {field:'imageUrl',title:'图片',width:300,
	        	formatter:function(value,row,index){
	        		return '<img src="'+row.imageUrl+'" style="width:100px;height:100px;margin:5px"/>';	
	        }},
	        {field:'description',title:'描述'},
	    ]] ,
	    pagination : true,
		rownumbers : true,
		fit : true,
		nowrap : false,
		loadMsg : '数据加载中,请稍候......',
		pageSize : 15,// 每页显示的记录条数，默认为10
		//pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onClickRow : function(rowIndex, rowData) {
			this.currentClickRowIndex =rowIndex;
			OnlySelectCurrentRow(this);
		}
	});
}

function removeMethod(a){
	RemoveMethod("dataGrid",a);
}

function addMethod(a){
	$('#addDiv').form('clear');
	$('#addDiv').show();
	 
	createUE();
	$('#addDiv').dialog({title: '添加',width: 800,height: 500,closed: false,cache: false,modal: true,
	    buttons: [{ 
			text: '提交', iconCls: 'icon-ok', 
			handler: function() { 
				$('#addForm').form('submit', {    
				    url:BASEPATH+"/"+$(a).attr("url"),
				    onSubmit: function(){    
				    	$("#descriptionHidden").val(editor.getContent());
				         return  $("#addForm").form('validate');
				    },    
				    success:function(data){
				    	data = JSON.parse(data);
				    	if(data.flag){
				    		$('#addDiv').dialog('close');  
				    		$("#dataGrid").datagrid("reload");
				    	}
				    	showSlimes(data);
				    }    
				});  
			} 
		}, 
		{ 
			text: '取消', 
			handler: function() { 
				$('#addDiv').dialog('close'); 
			} 
		}] 
	});

}
function createUE(){
	//清空文本编辑器
	editor = new UE.ui.Editor({initialFrameWidth:770,initialFrameHeight:400});
	 editor.render("descriptionTextarea");
	 editor.ready(function(){
		 editor.focus(true);
	});
	//上传图片
	editorImage=new UE.ui.Editor({imageUrl:contextPath+"/team/uploadFile.ajax"});
	editorImage.render("editorImage_container");
	editorImage.ready(function(){
		editorImage.addListener('beforeInsertImage',function(t,arg){
			for(var i=0;i<arg.length;i++){
				$("#imagesDiv").append("<img src='"+arg[i].src+"' style='width:200px;height:100px;margin:5px'>");
				$("#addForm").append("<input type='hidden' name='imageUrl' value='"+arg[i].src+"'>"); 
			}
		});
	});
	//注册上次按钮事件
		$("#uploadPhoto").unbind().click(function(){
			 var dialog=editorImage.getDialog("insertimage");
			 dialog.open();
		});
} 



function editMethod(a){
	$('#addDiv').show();
	var id = getEditIds("dataGrid");
	if(!id) return;
	
	var data = AjaxUnAsync("team/loadEdit.ajax?id="+id);
	setForm(data);
	$('#addDiv').dialog({title: $(a).text(),width: 800,height: 500,closed: false,cache: false,modal: true,
	    buttons: [{ 
			text: '提交', iconCls: 'icon-ok', 
			handler: function() { 
				$('#addForm').form('submit', {    
				    url:BASEPATH+"/"+$(a).attr("url"),
				    onSubmit: function(){    
				    	$("#descriptionHidden").val(editor.getContent());
				       return   $("#addForm").form('validate');
				    },    
				    success:function(data){
				    	data = JSON.parse(data);
				    	showSlimes(data);
						if(data.flag){
							$("#dataGrid").datagrid("reload");
							$('#addDiv').dialog('close'); 
						}
				    }    
				});  
			} 
		}, 
		{ 
			text: '取消', 
			handler: function() { 
				$('#addDiv').dialog('close'); 
			} 
		}] 
	}); 
}

function setForm(data){
	$.each($("#addForm :input"),function(){
		var name = this.name;
		if(name){
			$(this).val(data[name]);
		}
	});

	//清空文本编辑器
	editor = new UE.ui.Editor({initialFrameWidth:770,initialFrameHeight:400});
	 editor.render("descriptionTextarea");
	 editor.ready(function(){
		 editor.focus(true);
		 //手动获取farme设置body内容为隐藏元素
		 $(document.getElementById('ueditor_0').contentWindow.document.body).html($("#descriptionHidden").val());
	});
		
	//上传图片
	editorImage=new UE.ui.Editor({imageUrl:contextPath+"/team/uploadFile.ajax"});
	editorImage.render("editorImage_container");
	editorImage.ready(function(){
		//显示之前上传的图片
		
		$("#imagesDiv").html("<img src='"+$("#imageUrlHidden").val()+"' style='width:200px;height:100px'>");
		editorImage.addListener('beforeInsertImage',function(t,arg){
			for(var i=0;i<arg.length;i++){
				$("#imagesDiv").html("<img src='"+arg[i].src+"' style='width:200px;height:100px'>");
				$("#imageUrlHidden").val(arg[i].src);
				editor.focus(true);
			}
		});
	});
	//注册上次按钮事件
	$("#uploadPhoto").unbind().click(function(){
		 var dialog=editorImage.getDialog("insertimage");
		 dialog.open();
	});
}
function saveShowHomePage(input){
	
	var checkedV = $(input).attr("checked"),
	 max = 6,
	 url ="",
	 result = "";
	//选中
	if(checkedV){
		//选中条数目+1
		if(total >= max){
			$(input).attr("checked",null);
			$.messager.alert('警告',"显示首页的图片已经等于"+max+"张");
		}else{
			url = "product/updateProductShowState.ajax?id="+$(input).attr("rowId")+"&state=1";
			result = AjaxUnAsync(url);
			total++;
		}
	}else{
		result= AjaxUnAsync("product/updateProductShowState.ajax?id="+$(input).attr("rowId")+"&state=0");
		total--;
	}
}