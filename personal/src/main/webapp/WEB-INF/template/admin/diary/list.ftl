<#include "/common/main.ftl">
<@head title="心灵日记管理" isAdmin=true>
	<script type="text/javascript" src="${path.contextPath}/js/admin/diary/list.js"></script>
	<script type="text/javascript">
		var contextPath="${path.contextPath}";
	</script>
</@head>
<@body>
	<div>
		<ul>
			<li class="toolbar">
				<div style="float:left;">
					<div onclick="diary.addDiary()" style="float:left;">
						<img src="${path.contextPath}/images/admin/icons/add.ico" width="15px" height="15px" />
						<span>添加</span>
					</div>
					<div onclick="diary.del(null,false)" style="float:left;">
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
										<a href="javascript:void(0);" onclick="diary.edit('${data.id}')"><img src="${path.contextPath}/images/admin/icons/modify.ico" width="15px" height="15px"/></a>&emsp;
										<a href="javascript:void(0);" onclick="diary.del('${data.id}',true)"><img src="${path.contextPath}/images/admin/icons/delete.ico" width="15px" height="15px"/></a>
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
		window.using("pagination",function(){
			var option={
				total:${pageBean.recordCount},
				pageSize:10,
				showPageList:false
			}
			$("#pageBar").pagination(option);
		});
	</script>
</@body>