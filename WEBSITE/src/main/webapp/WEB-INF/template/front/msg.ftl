
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>给我留言</title>
<#include "common/head.ftl">
<style type="text/css">
	table{
		width:37%;
	}
	table tr{
		height:50px;
	}
	input,textarea{
		background-color: white;
		border: medium none;
		box-shadow: 0 0 5px 0px #aaa inset;
		border-radius: 5px;
		width: 100%;
		height: 30px;
		padding-left: 7px;
	}
	#addDiv{
		margin-top:65px;
	}
	.alert{
		width:37%;
		margin:0 auto;
	}
</style>
</head>
<body>
<#include "common/navigation.ftl">
	<div id="addDiv" class="main" >
    	<form id="addForm" method="post" >   
    		<table align="center">
    			<tr>
	    			<td class="tdTitle"> <label for="name">称&emsp;&emsp;呼<span style="color:red;">*</span>：</label> </td>
	    			<td> <input type="text" name="name" maxlength="20" id="name"/> </td>
    			</tr>
    			<tr>
    			<td class="tdTitle"> <label for="name">联系电话<span style="color:red;">*</span>：</label> </td>
    				<td> <input type="text" name="phone" id="phone"/> </td>
    			</tr>  
    			<td class="tdTitle"> <label for="name">留言内容<span style="color:red;">*</span>：</label> </td>
    				<td> <textarea type="text" name="content" id="content" style="height: 200px;padding:10px;"></textarea> </td>
    			</tr>  
    			<td></td>
    			<td><button type="button" class="btn btn-success" onclick="addMsg()">提交留言</button></td>
    		</table>
		</form>  
    </div>
    <#include "common/footer.ftl">
</body>

<script type="text/javascript" src="/js/public/jquery-1.8.0.min.js"></script>  
<script type="text/javascript">
	function addMsg(){
		var msg =validation(); 
		if(msg){
			$(".alert").remove();
			$('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert">&times;</a>'+msg+'</div>').insertBefore($("#addForm"));
			return;
		}else{
			$.ajax({
				"url":"/msg/add.php",
				"type":"post",
				"data":$("#addForm").serialize(),
				"dataType":"json",
				success:function(data){
					$(".alert").remove();
					if(data.flag){
						$('<div class="alert alert-success"><a href="#" class="close" data-dismiss="alert">&times;</a>'+(data.msg)+'</div>').insertBefore($("#addForm"));
						//window.history.go(-1);
						$("input,textarea").val("");
					}
					else{
						$('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert">&times;</a>'+(data.msg)+'</div>').insertBefore($("#addForm"));
					}
				}
			});
		}
	}
	function validation(){
		var msg = "";
		if(!$("#name").val().trim()){
			msg += "称呼不能为空！\n";
		} 
		if(!$("#phone").val().trim()){
			msg += "电话不能为空！\n";
		}else{
			if(!isTel($("#phone").val().trim())){
				msg += "电话格式不正确！\n";
			}
		} 
		if(!$("#content").val().trim()){
			msg += "留言内容不能为空！\t";
		}
		return msg;		
	}
	function isTel(s)   
	{   
		var reg = new RegExp(/^(((1[0-9]{1}[0-9]{1}))+\d{8})$/);
		if(reg.test(s)){
			return true;
		}else{
			return false;
		}
	} 
	$(function(){
		
		document.getElementById("name").focus();
		String.prototype.trim=function(){
		　　    return this.replace(/(^\s*)|(\s*$)/g, "");
		}
	});
	
</script>
</html>