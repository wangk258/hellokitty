<#include "/common/main.ftl">
<@head title="" isAdmin=true>
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
						
					</tbody>
				</table>
				<div id="pageBar"></div>
			</li>
		</ul>
	</div>
	<div id="dialog_div" style="display:none;overflow: auto;overflow-x:hidden;">
		<iframe id="dialog_frame" src="" width="100%" frameborder="0" scrolling="no"
		onload="this.height=this.contentWindow.document.documentElement.scrollHeight+10"
		 style="left:0px;right: 0px;bottom: 0px;"></iframe>
	</div>
	<script type="text/javascript">
		window.using("pagination",function(){
			var option={
				total:${pageBean.recordCount},
				pageSize:10,
				showPageList:false
			};
			$("#pageBar").pagination(option);
		});
	</script>
</@body>