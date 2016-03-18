var mood=(function(){
	var initEvent=function(){
		$(".selector").click(function(){
			$(".imagelist").show(1000);
		});
		$(".imagelist li img").click(function(){
			$(".small img,.view img").attr("src",this.src);
			$(".imagelist").hide(1000);
		});
		$("textarea").keyup(function(){
			$(".viewContent").text(this.value);
		});
		$(document.body).click(function(event){
			var obj=$(".imagelist")[0];
			if(event.target.className!="selector"){
				if(event.target!=obj){
					if($(obj).is(":visible")){
						$(obj).hide(1000);
					}
				}
			}
		});
		var images=$(".middle ul li");
		images.find("img").click(function(){
			$(".small img,.view img").attr("src",this.src);
		});
		$(".middle ul").css({"width":images.length*110});
		//图片张数
		var imageSize = images.length;
		var leftLoop = 1,rightLoop = showSize=4;
		var middle=$(".middle ul");
		$(".left").click(function(){
			if( rightLoop < imageSize){
				leftLoop++; 
				rightLoop++;
				middle.animate({left:"-="+110});
			}
		});
		$(".right").click(function(){
			if(leftLoop > 1	){
				rightLoop--;
				leftLoop--;	
				middle.animate({left:"+="+110});
			}
		});
	};
	return {
		initEvent:initEvent
	};
})();
$(function(){
	if(parent.document.getElementById("dialog_frame")){
		parent.document.getElementById("dialog_frame").height=450;
	}
	mood.initEvent();
});