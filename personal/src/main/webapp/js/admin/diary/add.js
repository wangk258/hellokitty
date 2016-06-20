define([ "angular", "jQueryUI" ], function(angular) {
	var contentEditor;
	function initEditor() {
		contentEditor = new UE.ui.Editor({
			initialFrameWidth : "100%",
			initialFrameHeight : 400,
			imageUrl : contextPath + "/upload.do?type=diary"
		});
		contentEditor.render("content");
	}
	angular.module("DiaryAddApp", []).controller("DiaryAddController",
			function($scope,$http) {
				$scope.diary={};
				$scope.$apply();
				$scope.submit = function() {
					console.log($scope.diary);
				}
			});
	angular.bootstrap(angular.element("#DiaryAddApp"), [ "DiaryAddApp" ]);
	function bindEvent() {
		$("#week").attr("readonly", true);
		$("#date").datepicker(
				{
					onSelect : function(date) {
						var day = new Date(date).getDay();
						var arr_week = new Array("星期日", "星期一", "星期二", "星期三",
								"星期四", "星期五", "星期六");
						$("#week").val(arr_week[day]);
					}
				});
		// $("#saveBtn").click(function(){
		// if(validateForm()){
		// $.post(contextPath+"/diary/saveOrUpdate.do",$("#form1").serialize(),function(result){
		// utils.showAlert(result.flag?"恭喜发财！":"出错啦！"+result.msg,function(){
		// art.dialog.opener.location.reload();
		// });
		// },"json");
		// }
		// });
	}
	function validateForm() {
		if (($.trim($("#date").val())) == "") {
			utils.showAlert("你想写哪天的日记啊，好歹也选一下撒！");
			return false;
		}
		if (($.trim($("#week").val())) == "") {
			utils.showAlert("还记的那天是星期几不，不记得赶快去查一下，马上给我填上！");
			return false;
		}
		if (($.trim($("#weather").val())) == "") {
			utils.showAlert("是不是那天的天气影响你的心情啦，你都不想写它？");
			return false;
		}
		if (contentEditor.getContent() == "") {
			utils.showAlert("虽然我不知道你是有多不愿意写，但是你还是多少写一点撒！");
			return false;
		} else {
			$("#plainText").val(contentEditor.getContentTxt());
		}
		return true;
	}
	return {
		init : function() {
			initEditor();
			bindEvent();
		}
	}
});