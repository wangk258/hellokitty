/**
 * Created by ahpeng on 2016/7/1.
 */
define(["datetimepicker-local"],function(){
    app.directive("datepicker", function () {
        return {
            restrict: "C",
            require: "ngModel",
            scope: {
                dateModel: "=ngModel",
                weekModel: "=week"
            },
            link: function (scope, element, attr, ctrl) {
                element.datetimepicker({
                    language:"zh-CN",
                    format: "yyyy-mm-dd",
                    todayBtn:true,
                    minView:2,
                    autoclose:true
                }).on("changeDate",function(e){
                    if(e && e.date && Object.prototype.toString.call(e.date) === "[object Date]"){
                        scope.dateModel = e.date;
                        var day = e.date.getDay();
                        var arr_week = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
                        //angular.element("#week").val(arr_week[day]);
                        scope.weekModel = arr_week[day];
                        scope.$apply();
                    }
                });
            }
        }
    });
});
