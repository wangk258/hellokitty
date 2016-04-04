<#include "/common/main.ftl">
<@head title="心灵日记管理" isAdmin=true>
	<script type="text/javascript">
		var contextPath="${path.contextPath}";
	</script>
</@head>
<@body>
	<div>
		<ul>
			<li class="toolbar">
				<div style="float:left;"></div>
			</li>
			<li class="maincontent">
				<table width="100%" cellpadding="0" cellspacing="0" align="center" class="datacontainer"  >
					<thead>
						<tr>
							<th width="50px"><input type="checkbox" id="selectAll"/></th>
							<th>日记概要</th>
							<th width="200px">创建时间</th>
							<th width="150px">操作</th>
							<th width="16px"></th>
						</tr>
					</thead>
					<tbody class="datalist">
						<#if error??>
						   ${error}
						<#else>
							<#list pageBean.recordList  as data>
								<tr id="${data.id}">
									<td><input type="checkbox" id="box_${data.id}"/></td>
									<#if data.plainText?length gt 20>
										<#assign text=data.plainText?substring(0,20)/>
									<#else>
										<#assign text=data.plainText/>
									</#if>
									<td>${text}</td>
									<td>${data.creatTime?string("yyyy-MM-dd")}</td>
									<td>
										<a href="javascript:void(0);" class="edit" data-id="${data.id}"><img src="${path.contextPath}/images/admin/icons/modify.ico" width="15px" height="15px"/></a>&emsp;
										<a href="javascript:void(0);" class="del" data-id="${data.id}"><img src="${path.contextPath}/images/admin/icons/delete.ico" width="15px" height="15px"/></a>
									</td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
				<div id="pageBar"></div>
			</li>
		</ul>
	</div>
	<div id="dialog_div" style="display:none;overflow: auto;overflow-x:hidden;">
		<iframe id="dialog_frame" src="" width="100%" frameborder="0" scrolling="no"
		onload="javascript:this.height=this.contentWindow.document.documentElement.scrollHeight+10"
		 style="left:0px;right: 0px;bottom: 0px;"></iframe>
	</div>
	<script type="text/javascript">
		var pageObj = {
			totalPage : ${pageBean.pageCount},
			pageSize : ${pageBean.pageSize}
		};
	</script>
	<script type="text/javascript" src="${path.contextPath}/js/lib/require.min.js" data-main="${path.contextPath}/js/config.js" data-src="${path.contextPath}/js/admin/diary/list.js"></script>
</@body>