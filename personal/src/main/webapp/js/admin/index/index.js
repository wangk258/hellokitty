/**
 * Created by ahpeng on 2015/7/15.
 */
define(["publicJS", "jQueryUI"], function () {
	return {
		init:function(){
			$(".controlmenu").click(function () {
		        if (window.confirm("是否确定退出系统？")) {
		            window.location.href="/admin/user/loginout/"+encodeURIComponent("/admin");
		        }
		    });
		}
	}
});