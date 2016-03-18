var option=(function(){
		var initPage=function(){
			$.ajax({
				url:"http://open.iciba.com/dsapi",
				type:"GET",
				dataType:"jsonp",
				jsonp:"callback",
				jsonpCallback:"ok_callback",
				async:false,
				success:function(result){
					$("#content").text(result.content);
				},
				error:function(){
					alert(arguments[1]);
				}
			});
			$(".div_container iframe").attr("allowtransparency",true)
			.attr("width","100%")
			.attr("height","100%")
			.attr("marginwidth",0)
			.attr("marginheight",0)
			.attr("scrolling","no")
			.attr("frameborder",0)
			.attr("hspace",0)
			.attr("vspace",0);
		};
		return {
			initPage:initPage
		};
	})();
	$(function(){
		option.initPage();
	});