
var editor = null;
$(function(){
	
	var data = AjaxUnAsync("process/loadEdit.ajax?id="+$("#id").val());
	createUE(data);
	
});


function addMethod(a){
	$("#descriptionHidden").val(editor.getContent());
	$.ajax({
		url:BASEPATH+"/"+$(a).attr("url"),
		dataType:"json",
		data:$("#addForm").serialize(),
		async:false,
		type:"POST",
		beforeSend:function(XMLHttpRequest){
			$("#msg").html("<font color='green'>保存中...！</font>");
		}, 
		success:function(data){
			if(data.flag){
				$("#msg").html("<font color='green'>保存成功！</font>");
				$("#msg").animate({left:'450px'});
			}else{
				$("#msg").html("<font color='red'>保存失败！</font>").animate({left:'250px'});
			}
		}
	});
} 
function viemMethod(a){
	addMethod($("#saveA"));
	if($(a).attr("url")){
		window.open(BASEPATH+"/"+$(a).attr("url"));
	}
}
//创建UE编辑器
function createUE(data){
	//清空文本编辑器
	editor = new UE.ui.Editor({initialFrameWidth:"100%",initialFrameHeight:400,
					imageUrl:contextPath+"/process/uploadFile.ajax",
		toolbars:[
		          ['fullscreen', 'source', '|', 'undo', 'redo', '|',
	                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
	                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
	                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
	                'directionalityltr', 'directionalityrtl', 'indent', '|',
	                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
	                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
	                'insertimage', 'emotion', 'scrawl', 'pagebreak',   '|',
	                'horizontal', 'date', 'time', 'spechars',  'wordimage', '|',
	                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
	                'print', 'preview', 'searchreplace', 'help', 'drafts']
	        ]});
	 editor.render("descriptionTextarea");
	 editor.ready(function(){
		 editor.focus(true);
		 $(document.getElementById('ueditor_0').contentWindow.document.body).html(data.content);
	});
	 $("input[name='content']").val(data.content);
	 $("input[name='id']").val(data.id);
}
