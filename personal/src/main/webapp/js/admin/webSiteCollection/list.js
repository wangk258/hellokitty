var website = {
	initEvent : function() {
		if (parent.document.getElementById("dialog_frame")) {
			parent.document.getElementById("dialog_frame").height = 540;
		}
		$("#selectAll").click(function() {
			$("input:checkbox").attr("checked", this.checked);
		});
	},
	edit : function() {
		openDialog(900, 200, "../../admin/webSiteCollection/add.html",
				"dialog_div", "dialog_frame");
	},
	del : function(id, isSingle) {
		idArray: [];
		if (isSingle) {
			idArray.push(id);
		} else {
			boxs = $("table tr:not(:first)").find("input:checkbox:checked");
			for ( var i = 0; i < boxs.length; i++) {
				idArray.push($(boxs[i]).attr("id").slice(4));
			}
		}
		if (window.confirm("你确定要做傻事吗？")) {
			$.post(top.basePath + "webSiteCollection/delete.do", {
				ids : idArray.join(",")
			}, function(result) {
				if (result) {
					if (result.flag) {
						alert("好吧！你赢了！");
						initData();
					} else {
						alert(result.msg);
					}
				} else {
					alert("服务器响应超时！");
				}
			});
		}
	},
	closeDialog : function(id) {
		$("#" + id).dialog("close");
	},
	add : function() {
		openDialog(900, 200, "../../admin/webSiteCollection/add.html",
				"dialog_div", "dialog_frame");
	}
};
$(function() {
	website.initEvent();
});