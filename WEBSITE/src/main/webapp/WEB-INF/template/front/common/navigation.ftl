<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
	    	<button type="button" class="navbar-toggle" data-toggle="collapse" 
	         data-target="#star">
	         <span class="sr-only">切换导航</span>
	         <span class="icon-bar"></span>
	         <span class="icon-bar"></span>
	         <span class="icon-bar"></span>
	      </button>
        <a class="navbar-brand" href="#">
            <img src="/images/front/logo.png" class="logo"/>
        </a>
    </div>
    <div class="menu collapse navbar-collapse" id="star">
        <ul class="nav navbar-nav">
        	<#list navigations as nav>
        		<#if nav_index==0>
	        		<li class="active"><a href="${nav.url}?pkey=${nav.id}">${(nav.name)!""}</a></li>
	        	<#else>
	        		<li id="${nav.id}"><a href="${nav.url}?pkey=${nav.id}&page=${nav.page}">${(nav.name)!""}</a></li>
        		</#if>
        	</#list>
        </ul>
    </div>
</nav>
<script type="text/javascript">
	$(function(){
		$("#${Session.navid}").addClass("active").siblings().removeClass("active");
	});
</script>