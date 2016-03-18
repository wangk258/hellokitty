<#include "/common/main.ftl">
<@head title="" isAdmin=true>
	<script type="text/javascript" src="script/add.js"></script>
</@head>
<@body>
	<form id="form1">
		<input type="hidden" id="siteId" name="id"/>
		<div style="width:900px;margin:0 auto;display:inline-block;">
			<table class="form-table">
				<tr>
					<td width="10%">标题：</td>
					<td width="90%">
						<input type="text"  name="content" id="content" class="form-control" value="${(site.content)?if_exists}"/>
					</td>
				</tr>
				<tr>
					<td>网址：</td>
					<td><input type="text" name="webSiteUrl" id="webSiteUrl" value="${(site.webSiteUrl)?if_exists}" class="form-control"/></td>
				</tr>
			</table>
			<div style="width:200px; margin:20px auto;">
				<input type="button"  value="确定" id="saveBtn" class="btn btn-success"/>
				&emsp;<input type="reset" value="重写" class="btn btn-info" />
			</div>
		</div>
	</form>
</@body>