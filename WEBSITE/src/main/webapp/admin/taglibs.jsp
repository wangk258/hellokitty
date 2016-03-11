<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="<%= basePath %>" />
<%--公用常量--%>
<script type="text/javascript">
var BASEPATH = "${ctx}";
var  SUPERFLAG =${sessionScope.SESSION_LOGIN_KEY.username eq applicationScope.currentUser};
</script>
