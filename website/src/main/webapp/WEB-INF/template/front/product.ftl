<#include "common/head.ftl">
<link type="text/css" rel="stylesheet" href="/css/front/product.css"/>
<div class="detail">
     <div class="pic">
         <img src="${(product.imageUrl)!""}"/>
     </div>
     <div class="shit">
	     <div class="name">
	         ${(product.name)!""}
	     </div>
     </div>
     <div class="desc">
         ${(product.description)!""}
     </div>
</div>