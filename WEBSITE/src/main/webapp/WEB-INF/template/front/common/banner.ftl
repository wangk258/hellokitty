<div id="advertisment" class="carousel slide" data-ride="carousel" data-wrap="true" data-interval="3000">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
    	<#list banners as banner>
    		<#if banner_index == 0>
		        <li data-target="#advertisment" data-slide-to="${banner_index}" class="active"></li>
		    <#else>
		        <li data-target="#advertisment" data-slide-to="${banner_index}"></li>
    		</#if>
    	</#list>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
    	<#list banners as banner>
    		<#if banner_index == 0>
		        <div class="item active">
		            <img src="${banner.imageUrl}" alt="">
		        </div>
		     <#else>
		        <div class="item">
		            <img src="${banner.imageUrl}" alt="">
		        </div>
    		</#if>
        </#list>
    </div>
</div>