var bg=(function(){
	var editor=null;
	var initEvent=function(){
		initUploader();
		$(".add").click(function(){
			editor.getDialog("insertimage").open();
		});
	};
	var initUploader=function(){
		 editor=new UE.ui.Editor({imageUrl:contextPath+"/mood/uploadbackground.do"});
		 editor.render("upload_container");
		 editor.ready(function(){
			editor.addListener('beforeInsertImage',function(t,arg){
				var imageUrls=[];
				for(var i=0;i<arg.length;i++){
					imageUrls.push(arg[i].src);
				}
				$.post(contextPath+"/moodBackground/add.do",{imageUrls:imageUrls.join(",")},function(result){
					success_callback(result,null,"no");
					window.location.reload();
				},"json");
			});
		 });
	};
	return {
		initEvent:initEvent
	}
})();
$(function(){
	if(parent.document.getElementById("dialog_frame")){
		parent.document.getElementById("dialog_frame").height=450;
	}
	bg.initEvent();
});