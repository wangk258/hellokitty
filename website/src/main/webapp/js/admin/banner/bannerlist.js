
var total = 0,editorImage =null;
$(function(){
	$('#dataGrid').datagrid({    
	    url:BASEPATH+'/banner/list.ajax',  
	    toolbar: '#toolbarDiv',
	    frozenColumns : [ [ {
			field : 'id',
			checkbox : true,
			formatter : function(value, row, index) {
				return row.id;
			}
		} ] ],
	    columns:[[    
      
	        {field:'imageUrl',title:'图片',width:"74.5%",
	        	formatter:function(value,row,index){
	        		return '<img src="'+BASEPATH+row.imageUrl+'" style="width:100px;height:100px;margin:5px"/>';	
	        }},
	        {field:'showHomePage',title:'显示首页',width:"20%",
	        	  formatter:function(value,rowData,index){
	        		  var checked = (value==1) ? ' checked="checked" ':""; 
	        		  return  "<input type='checkbox'  style='text-align: center' "+checked+" rowId='"+rowData.id+"' onclick='saveShowHomePage(this)'>";
    	  }}
	    ]] ,
	    pagination : true,
		rownumbers : true,fit : true,
		scrollBarSize:false,nowrap : false,
		loadMsg : '数据加载中,请稍候......',pageSize : 15,// 每页显示的记录条数，默认为10
		//pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onClickRow : function(rowIndex, rowData) {
			this.currentClickRowIndex =rowIndex;
			OnlySelectCurrentRow(this);
		}
	});
	//检索已经被选中的条数
	$.ajax({
		url:BASEPATH+"/"+"banner/loadCheckedCount.ajax",
		async:false,dataType:"json",
		success:function(data){
			total = data.total;
		}
	});
	
});
function addMethod(a){
	$('#addDiv').form('clear');
	$('#addDiv').show();
	 
	//上传图片
	editorImage=new UE.ui.Editor({imageUrl:contextPath+"/banner/uploadFile.ajax"});
	editorImage.render("editorImage_container");
	editorImage.ready(function(){
		editorImage.addListener('beforeInsertImage',function(t,arg){
			for(var i=0;i<arg.length;i++){
				$("#imagesDiv").append("<img src='"+arg[i].src+"' style='width:200px;height:100px;margin:5px'>");
				$("#addForm").append("<input type='hidden' name='imageUrls' value='"+arg[i].src+"'>"); 
			}
		});
	});
	//注册上次按钮事件
		$("#uploadPhoto").unbind().click(function(){
			 var dialog=editorImage.getDialog("insertimage");
			 dialog.open();
		});
	
	$('#addDiv').dialog({title: '添加',width: 800,height: 300,closed: false,cache: false,modal: true,
	    buttons: [{ 
			text: '提交', iconCls: 'icon-ok', 
			handler: function() { 
				$('#addForm').form('submit', {    
				    url:BASEPATH+"/"+$(a).attr("url"),
				    onSubmit: function(){    
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
function removeMethod(a){
	RemoveMethod("dataGrid",a);
}
function editMethod(a){
	$('#addDiv').show();
	var id = getEditIds("dataGrid");
	if(!id)
	$('#addForm').form('load',BASEPATH+"/banner/loadEdit.ajax&id="+id); 
	$('#addDiv').dialog({title: $(a).text(),width: 400,height: 300,closed: false,cache: false,modal: true,
	    buttons: [{ 
			text: '提交', iconCls: 'icon-ok', 
			handler: function() { 
				$('#addForm').form('submit', {    
				    url:BASEPATH+"/"+a.url,
				    onSubmit: function(){    
				         $("#addForm").form('validate');
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
			url = "banner/updateProductShowState.ajax?id="+$(input).attr("rowId")+"&state=1";
			result = AjaxUnAsync(url);
			total++;
		}
	}else{
		result= AjaxUnAsync("banner/updateProductShowState.ajax?id="+$(input).attr("rowId")+"&state=0");
		total--;
	}
}