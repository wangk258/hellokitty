<!DOCTYPE html>
<html>
<head>
    <title>小旋风宅急送--产品列表</title>
    <#include "common/head.ftl">
    <link type="text/css" rel="stylesheet" href="/css/front/product_list.css"/>
</head>
<body>
	<#include "common/navigation.ftl">
	<div class="searchBar  main">
		<input type="text" class="searchInput" value="${key!""}"/>
		<div class="selectYear">
			<div class="final"></div>
			<ul class="year">
				<#list 2014..2050 as year>
					<li>${year}</li>
				</#list>
			</ul>
			<div class="selectBar"></div>
		</div>
		<input type="button" class="searchButton" value="搜索"/>
	</div>
	<div class="category">
		<span>分类：</span>
		<ul class="type">
			<li value="-1" class="selectedType">全部</li>
			<#list categorys as category>
				<li value="${category.id}">${category.name}</li>
			</#list>
		</ul>
		<span>季度：</span>
		<ul class="season">
			<li value="1" class="selectedSeason">第一季度</li>
			<li value="2" >第二季度</li>
			<li value="3" >第三季度</li>
			<li value="4" >第四季度</li>
		</ul>
		<span>月份：</span>
		<ul class="month">
			<#list 1..12 as month>
				<#if month==1>
					<li value="${month}" class="selectedMonth">${month}月</li>
				<#else>
					<li value="${month}">${month}月</li>
				</#if>
			</#list>
		</ul>
	</div>
     <div class="product">
        <#list map?keys as key>
        	<div class="title">
        		<span class="titleName">${key}月</span>
        	</div>
        	<div class="list">
	        	<#list map[key] as product>
	        			<#if month == -1>
		        			<#if product_index &gt; 2>
		        				<#break>
		        			</#if>
	        			</#if>
			        	<table width="30%" cellspacing="0" cellpadding="0" border="1" style="float:left;margin:10px 16px;">
			        		<tr><td align="center"><img src="${product.imageUrl}"/></td></tr>
			        		<tr><td align="center">${(product.name)!""}</td></tr>
			        		<tr><td align="left"><span class="cctv">主料：<span>${(product.material)!""}</td></tr>
			        		<tr><td align="left"><span class="cctv">营养价值：</span>${(product.description)!""}</td></tr>
			        	</table>
	        	</#list>
        	</div>
        </#list>
    </div>
    <#--<div class="pageBar">
    	<ul class="pagination">
    		<#if (pageBean.beginPageIndex&gt;1)>
			  <li value="${page-1}" class="previous"><a href="#">&laquo;</a></li>
    		</#if>
	    	<#list pageBean.beginPageIndex..pageBean.endPageIndex as page>
				  <li value="${page}"><a href="#">${page}</a></li>
	    	</#list>
	    	<#if (pageBean.endPageIndex&lt;pageBean.pageCount)>		    	
				<li value="${page+1}" class="next"><a href="#">&raquo;</a></li>
	    	</#if>
		</ul>
    </div>-->
     <#include "common/footer.ftl">
</body>
<script type="text/javascript">
	$(function(){
		$(".final").text(${year});
		$(".selectBar,.selectYear").unbind().click(function(event){
			event.stopPropagation ? event.stopPropagation() : (event.cancelBubble = true);
			if($(".year").is(":hidden")){
				$(".year").slideDown(500).css("overflow","auto");
			}
			else{
				$(".year").slideUp(500);
			}
		});
		$(document.body).click(function(){
			$(".year").slideUp(500);
		});
		$(".year li").unbind().click(function(){
			$(".final").text($(this).text());
			var type=$(".selectedType").attr("value");
			var season=$(".selectedSeason").attr("value");
			var month=$(".selectedMonth").attr("value");
			if(!month){
				month=-1;
			}
			dosearch(1,type,season,month);
		});
		var page=getParameter("currentPage");
		if(!page){
			page=1;
		}
		var selectedType=getParameter("productType");
		var season=getParameter("season");
		var month=getParameter("month");
		$(".pagination li[value="+page+"]").addClass("active").siblings().removeClass("active");
		$(".category .type  li[value="+selectedType+"]").addClass("selectedType").siblings().removeClass("selectedType");
		$(".category .season  li[value="+season+"]").addClass("selectedSeason").siblings().removeClass("selectedSeason");
		if(!month || month==-1){
			$(".category .month  li").removeClass("selectedMonth");
		}
		else{
			$(".category .month  li[value="+month+"]").addClass("selectedMonth").siblings().removeClass("selectedMonth");
		}
		$(".pagination li").click(function(){
			var type=$(".selectedType").attr("value");
			dosearch(this.value,type);
		});
		$(".fuck").mouseover(function(){
			var id=this.id;
			$(".fuck").fancybox({
				'centerOnScroll' :true,
				'autoDimensions':false,
				'width'                :"50%",
				'height'               :"100%",
				'padding'            :10,
				'scrolling'            :'auto',
				'autoScale'			: true,
				'type'				    : 'iframe',
				'href'                   :'/product/product.php?id='+id
			});
		});
		$(".category .type li").click(function(){
			var season=$(".selectedSeason").attr("value");
			var month=$(".selectedMonth").attr("value");
			if(!month){
				month=-1;
			}
			dosearch(1,$(this).attr("value"),season,month);
		});
		$(".category .season li").click(function(){
			var type=$(".selectedType").attr("value");
			//var month=$(".selectedMonth").attr("value");
			dosearch(1,type,this.value,-1);
		});
		$(".category .month li").click(function(){
			var season=parseInt((this.value-1)/3)+1;
			//var season=$(".selectedSeason").attr("value");
			$(".category .season  li[value="+season+"]").addClass("selectedSeason").siblings().removeClass("selectedSeason");
			var type=$(".selectedType").attr("value");
			dosearch(1,type,season,$(this).attr("value"));
		});
		$(".searchButton").click(function(){
			var type=$(".selectedType").attr("value");
			var season=$(".selectedSeason").attr("value");
			var month=$(".selectedMonth").attr("value");
			if(!month){
				month=-1;
			}
			dosearch(1,type,season,month);
		});
		$(".searchInput").keyup(function(e){
			if(e.keyCode==13){
				var type=$(".selectedType").attr("value");
				var season=$(".selectedSeason").attr("value");
				var month=$(".selectedMonth").attr("value");
				if(!month){
					month=-1;
				}
				dosearch(1,type,season,month);
			}
		});
		function dosearch(page,type,season,month){
			var key=$(".searchInput").val();
			var pkey=getParameter("pkey");
			var year=$(".final").text();
			window.location.href="/product/list.php?currentPage="+page+"&name="+key+
			"&productType="+type+"&season="+season+"&month="+month+"&pkey="+pkey+"&year="+year;
		}
		function getParameter(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			var r = window.location.search.substr(1).match(reg);
			if (r != null) return unescape(r[2]); return null;
		}
	});
</script>
</html>