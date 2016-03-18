 $(function(){
	 if(parent.document.getElementById("dialog_frame")){
		parent.document.getElementById("dialog_frame").height=540;
	 }
	 if(location.href.indexOf("photos/list.do")>=0){
		 $("#album_container").hide();
		 $("#photo_container").show();
		 $("#album_id").val(getParameter("albumId"));
	 }
	 else{
		 $("#album_container").show();
		 $("#photo_container").hide();
	 }
	 photo.initEvent();
 });
 var photo=(function(){
		var editor=null;
		var initUploader=function(){
			 editor=new UE.ui.Editor({imageUrl:contextPath+"/photos/uploadPhoto.do"});
			 editor.render("upload_container");
			 editor.ready(function(){
				editor.addListener('beforeInsertImage',function(t,arg){
					var imageUrls=[];
					for(var i=0;i<arg.length;i++){
						imageUrls.push(arg[i].src);
					}
					$.post(contextPath+"/photos/add.do",{imageUrls:imageUrls.join(","),albumId:$("#album_id").val()},function(result){
						success_callback(result,null,"no");
						reloadPhoto();
					},"json");
				});
			 });
		};
		var initEvent=function(){
			initUploader();
			$(".album_image").click(function(){
				var albumName=$(this).parent().find(".album_name").text();
				$("#current_album").text(albumName);
				reloadPhoto(this.id);
			});
			$(".remove_album_toolbar img").click(function(){
				if(window.confirm("确实要删除该相册吗？")){
					deleteAlbum($(this).parent().attr("id"));
				}
			});
			$(".photo li").hover(function(){
				$(this).find(".remove_photo_toolbar").stop(true).show(500);
			},function(){
				$(this).find(".remove_photo_toolbar").stop(true).hide(500);
			});
			$(".album li").hover(function(){
				$(this).find(".remove_album_toolbar").stop(true).show(500);
			},function(){
				$(this).find(".remove_album_toolbar").stop(true).hide(500);
			});
			$(".lightBox_image").click(function(){
				var $selectedIcon=$(this).parent().find(".selected_image");
				if($selectedIcon.length>0){
					$selectedIcon.remove();
				}
				else{
					$(this).parent().append("<img src='"+contextPath+"/images/admin/icons/right.ico' class='selected_image'/>");
				}
			});
			$(".remove").click(function(){
				if(window.confirm("确实要删除该照片吗？")){
					deleteSinglePhoto($(this).parent().attr("id"));
				}
			});
			$(".setCover").click(function(){
				var id=$(this).parent().attr("id");
				$.post(contextPath+"/photos/setCover.do",{id:id},function(result){
					success_callback(result,null,"yes");
				},"json");
			});
			//flag--1:主页显示 0：取消主页显示
			$(".showInMainPage").click(function(){
				var id=$(this).parent().attr("id");
				if($(".mainPage_flag").length>=5){
					alert("主页最多只能显示五张图片 ！");
					return false;s
				}
				$.post(contextPath+"/photos/setToMainPage.do",{id:id,flag:1},function(result){
					success_callback(result,null,"yes");
					reloadPhoto();
				},"json");
			});
			$(".cancelInMainPage").click(function(){
				var id=$(this).parent().attr("id");
				$.post(contextPath+"/photos/setToMainPage.do",{id:id,flag:0},function(result){
					success_callback(result,null,"yes");
					reloadPhoto();
				},"json");
			});
			$("#addAlbum").click(addAlbum);
			$("#back").click(function(){
				reloadAlbum();
			});
			$("#saveBtn").click(function(){
				if($.trim($("#albumName").val())==""){
					alert("不要急，填好了再提交！");
					return false;
				}
				$.post(contextPath+"/album/add.do",{"name":$("#albumName").val()},function(result){
					success_callback(result,"createAlbum","no");
					reloadAlbum();
				},"json");
			});
			$("#uploadPhoto").click(function(){
				 var dialog=editor.getDialog("insertimage");
				 dialog.open();
			});
			$("#deletePhoto").click(deletePhoto);
		};
		var reloadPhoto=function(id){
			if(id){
				location.href=contextPath+"/photos/list.do?albumId="+id+"&path=admin";
			}
			else{
				location.reload();
			}
		};
		var reloadAlbum=function(){
			location.href=contextPath+"/album/list.do?path=admin";
		}
		var deleteSinglePhoto=function(id){
			if($("#"+id).find(".photo_image").find(".mainPage_flag").length>0){
				alert("已经在主页上展示的图片暂时不能删除，请先进行取消操作！");
				return false;
			}
			var ids=[];
			ids.push(id);
			$.post(contextPath+"/photos/delete.do",{ids:ids.join(",")},function(result){
				success_callback(result,null,"no");
				reloadPhoto();
			},"json");
		};
		var deletePhoto=function(){
			var ids=[];
			var flag=true;
			$(".selected_image").each(function(index){
				if($(this).siblings(".mainPage_flag").length>0){
					flag=false;
					return false;
				}
				ids.push($(this).parent().parent().attr("id").slice(6));
			});
			if(!flag){
				alert("已经在主页上展示的图片暂时不能删除，请先进行取消操作！");
				return false;
			}
			$.post(contextPath+"/photos/delete.do",{ids:ids.join(",")},function(result){
				success_callback(result);
				reloadPhoto();
			},"json");
		};
		var deleteAlbum=function(id){
			$.post(contextPath+"/album/delete.do",{id:id},function(result){
				success_callback(result,null,"no");
				reloadAlbum();
			},"json");
		};
		var addAlbum=function(){
			$("#createAlbum").dialog({
				title:"创建相册",
				width:300,
				height:200,
				modal:true,
				open:function(){
					$("#albumName").val("");
				}
			 });
		};
		return {
			initEvent:initEvent
		}
 })();
