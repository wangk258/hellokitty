<#include "/common/main.ftl">
<@head title="" isAdmin=true>
	<script type="text/javascript" src="${path.contextPath}/js/lib/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${path.contextPath}/js/lib/ueditor/ueditor.config.js"></script>
	<style type="text/css">
		.form-table{
		    width:95%;
		    text-align:center;
		}
	</style>
</@head>
<@body>
	<form id="form1">
		<input type="hidden" id="diaryId" name="id" value="${(diary.id)?if_exists}"/>
		<input type="hidden" id="plainText" name="plainText" value="${(diary.plainText)?if_exists}"/>
		<div style="width:900px;margin:0 auto;display:inline-block;">
			<table class="form-table">
				<tr>
					<td width="10%">日期：</td>
					<td width="90%">
						<input type="text"  name="date" id="date" class="form-control" value="${(diary.date)?if_exists}" />
					</td>
				</tr>
				<tr>
					<td>星期：</td>
					<td><input type="text" name="week" id="week" value="${(diary.week)?if_exists}" class="form-control"/></td>
				</tr>
				<tr>
					<td>天气：</td>
					<td><input type="text" name="weather" id="weather" value="${(diary.weather)?if_exists}" class="form-control"/></td>
				</tr>
				<tr>
					<td>内容：</td>
					<td><textarea style="height:400px;" name="content" id="content" >${(diary.content)?if_exists}</textarea></td>
				</tr>
			</table>
			<div style="width:200px; margin:20px auto;">
				<input type="button"  value="确定" id="saveBtn" class="btn btn-success"/>
				&emsp;<input type="reset" value="重写" class="btn btn-info" />
			</div>
		</div>
	</form>
	<script type="text/javascript" src="${path.contextPath}/js/lib/require.min.js" data-main="${path.contextPath}/js/config.js" data-src="${path.contextPath}/js/admin/diary/add.js"></script>
</@body>