<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/css/admin.css">
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/js/lib/bootstrap/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/js/lib/bootstrap/css/bootstrap-datetimepicker.min.css">
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/css/lib/public_admin.css">
    <link type="text/css" rel="stylesheet" href="${path.contextPath}/css/lib/toolbar.css">
</head>
<body>
<div class="main" id="personalApp">
    <div class="left" ng-controller="personalController">
        <div class="panel-group" id="accordion">
            <div class="panel panel-default" ng-repeat="func in funcs">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion"
                           href="#collapse{{$index}}" ng-bind="func"></a>
                    </h4>
                </div>
                <div id="collapse{{$index}}" class="panel-collapse collapse" style="height:0px">
                    <div class="panel-body">
                        <ul>
                            <li ng-repeat="num in count track by $index">
                                <a href="#/diarylist/1">
                                    <div class="div_img">
                                        <img src="/images/admin/diary.png"/>
                                    </div>
                                    <div class="div_title"><span>心灵日记</span></div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
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