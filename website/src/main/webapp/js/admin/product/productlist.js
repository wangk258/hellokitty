//total总数
//edit 富文本编辑器
var total = 0, editor = null, editorImage = null;
$(function(){
	
	loadGrid();
	//检索已经被选中的条数
	var data = AjaxUnAsync("product/loadCheckedCount.ajax");
	total = data.total;
});


function loadGrid(){
	$('#dataGrid')
	.datagrid(
			{
				url : BASEPATH + '/product/list.ajax',
				toolbar : '#toolbarDiv',
				frozenColumns : [ [ {
					field : 'id',
					checkbox : true,
					formatter : function(value, row, index) {
						return row.id;
					}
				} ] ],
				columns : [ [
						{
							field : 'name',
							title : '产品名称',
							width : "10%"
						},
						{
							field : 'material',title : '产品材料',width : "10%"
						},
						{field : 'price',title : '价格',width : "5%"},
						{field : 'year',title : '年份',width : "5%"},
						{field : 'month',title : '月份',width : "10%"},
						{field : 'productType',title : '产品类型',width : "10%",
							formatter : function(value, row, index) {
							if(value){
								return 	value.name;
							}
							return "无类型";
						}},
						{
							field : 'imageUrl',
							title : '图片',
							width : "30%",
							formatter : function(value, row, index) {
								return '<img src="'
										+ BASEPATH
										+ row.imageUrl
										+ '" style="width:100px;height:100px;margin:5px"/>'
							}
						},{
							field : 'showHomePage',
							title : '是否在前端显示',
							width : "13%",
							formatter : function(value, rowData, index) {
								var checked = (value == 1) ? ' checked="checked" '
										: "";
								return "<input type='checkbox' + "
										+ checked
										+ " rowId='"
										+ rowData.id
										+ "' onclick='saveShowHomePage(this)'>";
							}
						} ] ],
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
					this.currentClickRowIndex = rowIndex;
					OnlySelectCurrentRow(this);
				},
				onDblClickRow: function(rowIndex, rowData) {
					$('#editMethod').click();
				},
			});
}
function addMethod(a){
	$('#addDiv').form('clear');
	$('#addDiv').show();
	//时间 月份
	var myDate = new Date();
	
	createProductDate("",myDate.getMonth()+1);
	createUE();
	createProductType(0);
	$('#addDiv').dialog({title: '添加',width: 900,height:520,closed: false,cache: false,modal: true,
	    buttons: [{ 
			text: '提交', iconCls: 'icon-ok', 
			handler: function() { 
				$('#addForm').form('submit', {    
				    url:BASEPATH+"/"+$(a).attr("url"),
				    onSubmit: function(){   
				    	var value =document.getElementById('ueditor_0').contentWindow.document.body.innerHTML;
				    	$("#descriptionHidden").val(value);
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
		}],
	}); 
}

function removeMethod(a){
	RemoveMethod("dataGrid",a);
}
function editMethod(a){
	var id = getEditIds("dataGrid");
	if(!id) return;
	$('#addDiv').show();
	var data = AjaxUnAsync("product/loadEdit.ajax?id="+id);
	setForm(data);
	
	if(data.productType){
		createProductType(data.productType.id);
	}else{
		createProductType(0);
	}
	
	createProductDate(data.year,data.month);
	$('#addDiv').dialog({title: $(a).text(),width: 900,height: 520,closed: false,cache: false,modal: true,
	    buttons: [{ 
			text: '提交', iconCls: 'icon-ok', 
			handler: function() { 
				$('#addForm').form({    
				    url:BASEPATH+"/"+$(a).attr("url"),
				    onSubmit: function(){    
				    	//console.info("editor.getContent()"+editor.getContent());
				    	var value =document.getElementById('ueditor_0').contentWindow.document.body.innerHTML;
				    	$("#descriptionHidden").val(value);
				       return  $("#addForm").form('validate');
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
				
				$("#addForm").submit();
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
			url = "product/updateProductShowState.ajax?id="+$(input).attr("rowId")+"&state=1";
			result = AjaxUnAsync(url);
			total++;
		}
	}else{
		result= AjaxUnAsync("product/updateProductShowState.ajax?id="+$(input).attr("rowId")+"&state=0");
		total--;
	}
}

function createUE(){
	editor = new UE.ui.Editor({initialFrameWidth:"100%",initialFrameHeight:400});
	 editor.render("descriptionTextarea");
	 editor.ready(function(){
		 editor.focus(true);
		 document.getElementById('ueditor_0').contentWindow.document.body.innerHTML="";
		 
	 });
		//上传图片
	editorImage=new UE.ui.Editor({imageUrl:contextPath+"/product/uploadFile.ajax"});
	editorImage.render("editorImage_container");
	editorImage.ready(function(){
		//清空图片
		$("#imagesDiv").html("");
		editorImage.addListener('beforeInsertImage',function(t,arg){
			for(var i=0;i<arg.length;i++){
				$("#imagesDiv").html("<img src='"+arg[i].src+"' style='width:100px;height:100px'>");
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
function setForm(data){
	$.each($("#addForm :input"),function(){
		var name = this.name;
		if(name){
			$(this).val(data[name]);
		}
	});

	//清空文本编辑器
	editor = new UE.ui.Editor({initialFrameWidth:"100%",initialFrameHeight:400});
	 editor.render("descriptionTextarea");
	 editor.ready(function(){
		 editor.focus(true);
		 //手动获取farme设置body内容为隐藏元素
		 $(document.getElementById('ueditor_0').contentWindow.document.body).html($("#descriptionHidden").val());
	});
		
	//上传图片
	editorImage=new UE.ui.Editor({imageUrl:contextPath+"/company/uploadFile.ajax"});
	editorImage.render("editorImage_container");
	editorImage.ready(function(){
		//显示之前上传的图片
		$("#imagesDiv").html("<img src='"+$("#imageUrlHidden").val()+"' style='width:150px;height:150px'>");
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
function createProductType(value){
	//加载类型
	var html = "",
	    data = AjaxUnAsync("productType/listAll.ajax");
	$.each(data,function(){
		if(value==this.id){
			html += "<option value='"+this.id+"' selected='selected'>"+this.name+"</option>"
		}else{
			html += "<option value='"+this.id+"' >"+this.name+"</option>"
		}
	});
	$("#productType").html(html);
} 

function validationPrice(a){
	
	 var reg = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;
	 if (!reg.test($(a).val())) {
		 alert("输入价格不合法!");
	 }
}

function createProductDate(year,month){
	var myDate = new Date();
	var fullYear = myDate.getFullYear();
	for(var i = 0;i<30;i++){
		var options = "";
		if(fullYear == year){
			options += "<option value='"+fullYear+"' selected='selected'>"+fullYear+"</option>";
		}else{
			options += "<option value='"+fullYear+"'>"+fullYear+"</option>";
		}
		$("#year").append(options);
		fullYear++;
	}
	for(var j = 1;j<=12;j++){
		//console.info(j+"===="+(j==(month*1)));
		if(j==(month*1)){
			$("#month").append("<option value='"+j+"' selected='selected'>"+j+"</option>");
		}else{
			$("#month").append("<option value='"+j+"'>"+j+"</option>");
		}
		
	}
	
}