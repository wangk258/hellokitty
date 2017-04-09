<!DOCTYPE html>
<html>
<head>
    <title>小旋风宅急送</title>
    <#include "common/head.ftl">
    <link type="text/css" rel="stylesheet" href="/css/front/index.css">
</head>
<body>
<#include "common/navigation.ftl">
<#include "common/banner.ftl">
<div class="product">
        <ul class="row">
        	<#list products as product>
            	<li style="width:${(100/products?size)-0.6}%;height:262px;">
            		<div style="width:100%;height:238px;">
		            	<a href="javascript:void(0)" id="${product.id}" class="fuck">
		            		<img src="${(product.imageUrl)!""}"/>
		            		<span class="productName">${(product.name)!""}</span>
		            		<span class="price"> ￥ ${(product.price)!""} 元</span>
		            	</a>
            		<div>
            	</li>
            </#list>
        </ul>
    </div>
<#include "common/footer.ftl">
</body>
<script type="text/javascript">
	$(function(){
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
	});
</script>
</html>