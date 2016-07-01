/**
 * Created by ahpeng on 2016/7/1.
 */
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
                dateFormat: "yy-mm-dd",
                changeMonth: true,
                changeYear: true,
                onSelect: function (date) {
                    scope.dateModel = date;
                    var day = new Date(date).getDay();
                    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
                    angular.element("#week").val(arr_week[day]);
                    scope.weekModel = arr_week[day];
                    scope.$apply();
                }
            });
        }
    }
});
