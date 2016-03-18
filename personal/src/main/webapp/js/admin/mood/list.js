var mood=(function(){
	var initEvent=function(){
		$(".background").click(function(){
			openDialog(920,500,contextPath+'/mood/loadbackground.do?path=admin','dialog_div','dialog_frame');
		});
		$(".add").click(function(){
			openDialog(900,500,contextPath+'/mood/addview.do','dialog_div','dialog_frame');
		});
	};
	return {
		initEvent:initEvent
	};
})();
$(function(){
	if(parent.document.getElementById("dialog_frame")){
		parent.document.getElementById("dialog_frame").height=540;
	}
	mood.initEvent();
});