<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/js/lib/bootstrap/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/js/lib/bootstrap/css/bootstrap-datetimepicker.min.css">
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/js/lib/accordion/jquery.accordion.css">
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/js/lib/toolbar/jquery.toolbar.css">
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/css/admin.css">
</head>
<body>
<div class="main" id="personalApp">
    <div class="left" ng-controller="personalController">
        <div class="p-accordion"></div>
    </div>
    <div class="right">
        <div class="content" ng-view></div>
        <div id="pageBar"></div>
    </div>
</div>
<div class="controlmenu">
    <div class="b-left"></div>
    <div class="b-center">
        <div class="b-icon"></div>
        <div class="b-text">退出</div>
    </div>
    <div class="b-right"></div>
</div>
</body>
<script type="text/javascript">
    var baseUrl = "${path.contextPath}";
</script>
<script type="text/javascript" src="${path.contextPath}/js/lib/require.min.js" data-main="${path.contextPath}/js/config.js"></script>
</html>