
var editor = null;
$(function(){
	var data = AjaxUnAsync("editor/loadEdit.ajax?ckey="+$("#ckey").val()+"&pkey="+$("#pkey").val());
	createUE(data);
	
});


function addMethod(a){
	$("#descriptionHidden").val(editor.getContent());
//	$("#msg").css({"position":"absolute"});
//	$("#msg").offset({left:0});
	$("#msg").html("<font color='green'>保存中。。。。。</font>");
	$.ajax({
		url:BASEPATH+"/"+$(a).attr("url"),
		dataType:"json",
		data:$("#addForm").serialize(),
		async:false,
		type:"POST",
		success:function(data){
			if(data.flag){
				$("#msg").html("<font color='green'>保存成功！</font>");
			}else{
				$("#msg").html("<font color='red'>保存失败！</font>").animate({left:'250px'});
			}
//			$("#msg").animate({left:'+450px'});
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
					imageUrl:"/editor/uploadFile.ajax",
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
		 if(data)
		 $(document.getElementById('ueditor_0').contentWindow.document.body).html(data.content);
	});
	 if(data){
		 $("input[name='content']").val(data.content);
		 $("input[name='id']").val(data.id);
	 }
}
