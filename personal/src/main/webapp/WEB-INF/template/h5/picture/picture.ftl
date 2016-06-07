<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<title></title>
<link rel="stylesheet" type="text/css" href="${path.contextPath}/css/lrtk.css" />
<script type="text/javascript" src="${path.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/photo/pptBox.js"></script>
<style type="text/css">
	body{
		margin: 2px 0;
		padding:0px;
		overflow:hidden;
	}
</style>
</head>
<body>
<div id="xxx" >
     <script type="text/javascript">
	     var box =new PPTBox();
	     box.width = 780; //宽度
	     box.height = 400;//高度
	     box.autoplayer = 3;//自动播放间隔时间
	     $.ajaxSetup({
	    	 async:false
	     });
	     $.get("${path.contextPath}/photos/showinfront.do",null,function(result){
	    	if(result){
	    		var images=result;
	    		for(var i=0;i<images.length;i++){
	    			box.add({"url":"${path.contextPath}"+images[i].imageUrl,"href":"http://www.lanrentuku.com/","title":""});
	    		}
			     box.show();
	    	}
	     },"json");
    </script>
</div>
</body>
</html>