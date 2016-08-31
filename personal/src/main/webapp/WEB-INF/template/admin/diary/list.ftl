<#include "/common/main.ftl">
<@head title="心灵日记管理" isAdmin=true>
</@head>
<@body>
	<div id="diaryListAdminApp">
		<ul>
			<li class="toolbar">
				<div></div>
			</li>
			<li class="maincontent">
				<table width="100%" cellpadding="0" cellspacing="0" align="center" class="datacontainer"  >
					<thead>
						<tr>
							<th width="50px"><input type="checkbox" id="selectAll"/></th>
							<th>日记概要</th>
							<th width="200px">时间</th>
							<th width="150px">操作</th>
							<th width="16px"></th>
						</tr>
					</thead>
					<tbody class="datalist" ng-controller="diaryListAdminController">
						<#if error??>
						   ${error}
						<#else>
							<#list pageBean.recordList  as data>
								<tr id="${data.id}">
									<td><input type="checkbox" id="${data.id}" ng-click="selectItem($event)"/></td>
									<#if data.plainText?length gt 40>
										<#assign text=data.plainText?substring(0,40) + "..."/>
									<#else>
										<#assign text=data.plainText/>
									</#if>
									<td>${text}</td>
									<td>${data.date?number?number_to_date}</td>
									<td>
										<button class="btn btn-info btn-xs" ng-click="edit(${data.id})"><span class="glyphicon glyphicon-pencil"><span>编辑</button>
										<button class="btn btn-danger btn-xs"  ng-click="del(${data.id})"><span class="glyphicon glyphicon-remove"></span>删除</button>
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
			recordCount : ${pageBean.recordCount},
			pageSize : ${pageBean.pageSize},
			pageNumber:${pageBean.currentPage}

		};
	</script>
	<script type="text/javascript" src="${path.contextPath}/js/lib/require.min.js" data-main="${path.contextPath}/js/config.js" data-src="${path.contextPath}/js/admin/diary/list.js"></script>
</@body>