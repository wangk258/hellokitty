<#include "/common/main.ftl">
<@head title="" isAdmin=true>
	<script type="text/javascript" src="${path.contextPath}/js/admin/webSiteCollection/list.js"></script>
</@head>
<@body>
	<div>
		<ul>
			<li class="toolbar">
				<div style="float:left;">
					<div onclick="website.add()" style="float:left;">
						<img src="${path.contextPath}/images/admin/icons/add.ico" width="15px" height="15px" />
						<span>添加</span>
					</div>
					<div onclick="website.del(null,false)" style="float:left;">
						<img src="${path.contextPath}/images/admin/icons/delete.ico" width="15px" height="15px"/>
						<span>删除</span>
					</div>
				</div>
				<div class="search">
					<input type="text"/>
				</div>
			</li>
			<li class="maincontent">
				<table width="100%" cellpadding="0" cellspacing="0" align="center" class="datacontainer"  >
					<thead>
						<tr>
							<th width="5%"><input type="checkbox" id="selectAll"/></th>
							<th width="35%">网站内容</th>
							<th width="50%">操作</th>
							<th width="10%"></th>
						</tr>
					</thead>
					<tbody class="datalist">
						<#if error??>
						   ${error}
						<#else>
							<#list pageBean.recordList  as data>
								<tr id="${data.id}">
									<td><input type="checkbox" id="box_${data.id}"/></td>
									<td><div>${data.content}</div></td>
									<td><div>${data.webSiteUrl}</div></td>
									<td>
										<a href="javascript:void(0);" onclick="website.edit('${data.id}')"><img src="${path.contextPath}/images/admin/icons/modify.ico" width="15px" height="15px"/></a>&emsp;
										<a href="javascript:void(0);" onclick="website.del('${data.id}',true)"><img src="${path.contextPath}/images/admin/icons/delete.ico" width="15px" height="15px"/></a>
									</td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
			</li>
		</ul>
	</div>
	<div id="dialog_div" style="display:none;overflow: auto;overflow-x:hidden;">
		<iframe id="dialog_frame" src="" width="100%" frameborder="0" scrolling="no"
		onload="javascript:this.height=this.contentWindow.document.documentElement.scrollHeight+10"
		 style="left:0px;right: 0px;bottom: 0px;"></iframe>
	</div>
</@body>