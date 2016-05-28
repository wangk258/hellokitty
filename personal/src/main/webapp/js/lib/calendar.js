/**
 * Created by pah on 2015/4/9.
 */
;
!function (c) {
    if (define && define.amd && typeof define === "function") {
        return define(c);
    }
    return c(jQuery);
}(!function ($) {
    var self = null,
        tempOptions = {};
    function _bindEvent() {
        $("._jt").on("click", "td", function () {
            tempOptions.onDayChange && typeof tempOptions.onDayChange === "function" && tempOptions.onDayChange(new Date($(this).attr("data-date")).getTime());
        });
        $("._cal").off("change").on("change", " .yearSelect,.monthSelect", function () {
            var callback;
            if (this.className.indexOf("year") >= 0) {
                $.extend(tempOptions,{
                    year: $(this).val() - 0,
                    month: $(".monthSelect").val() - 1
                });
                callback=tempOptions.onYearChange;
            }
            else if (this.className.indexOf("month") >= 0) {
                $.extend(tempOptions, {
                    month: $(this).val() - 1,
                    year: $(".yearSelect").val() - 0
                });
                callback=tempOptions.onMonthChange;
            }
            callback && typeof callback === "function" && callback(tempOptions.year,tempOptions.month);
            methods.init.call(self, tempOptions);
        });
        $("._cal").off("click").on("click", " .prevMonth,.nextMonth", function (e) {
            var monthSelect = $(".monthSelect");
            var yearSelect = $(".yearSelect");
            if (this.className.indexOf("prev") >= 0) {
                if (monthSelect.val() - 0 === 1) {
                    monthSelect.val(12);
                    yearSelect.val(yearSelect.val() - 1);
                }
                else {
                    monthSelect.val(monthSelect.val() - 1);
                }
            } else if (this.className.indexOf("next") >= 0) {
                if (monthSelect.val() - 0 === 12) {
                    monthSelect.val(1);
                    yearSelect.val(yearSelect.val() - 0 + 1);
                }
                else {
                    monthSelect.val(monthSelect.val() - 0 + 1);
                }
            }
            $.extend(tempOptions,{
                month: $(".monthSelect").val() - 1,
                year: $(".yearSelect").val() - 0
            });
            tempOptions.onMonthChange && typeof tempOptions.onMonthChange === "function" && tempOptions.onMonthChange(tempOptions.year,tempOptions.month);
            methods.init.call(self, tempOptions);
        });
    }
    function _renderToolBar(date){
        if ($("._cal").length <= 0) {
            bgroup = "<div class='_cal'><select class='yearSelect'>";
            for (var i = 1949; i <= 2050; i++) {
                var selected = "";
                if (i === date.getFullYear()) {
                    selected = "selected";
                }
                bgroup += "<option value='" + i + "' " + selected + ">" + i + "</option>";
            }
            bgroup += "</select><div class='t_r'><button class='prevMonth'>&lt;</button><select class='monthSelect'>";
            for (var i = 1; i <= 12; i++) {
                var selected = "";
                if (i === date.getMonth() + 1) {
                    selected = "selected";
                }
                bgroup += "<option value='" + i + "' " + selected + ">" + i + "</option>";
            }
            bgroup += "</select><button class='nextMonth'>&gt;</button></div>";
            return bgroup;
        }else{
            $(".yearSelect").val(date.getFullYear());
            $(".monthSelect").val(date.getMonth() + 1);
        }
        return "";
    }
    function _renderBody(options){
        var weeks = ["日", "一", "二", "三", "四", "五", "六"];
        var dayNumber = 1;
        var date = new Date(options.year, options.month);
        var c_week = weeks[date.getDay()]; //得到星期
        var startDay = new Date(options.year, options.month, 1).getDay();//获取第一天
        var lastDay = new Date(options.year, options.month + 1, 0).getDate();//获取最后一天

        var table = _renderToolBar(date) + "<table class='_jt'><thead><tr>";
        //生成日到六
        for (var i = 0; i < 7; i++) {
            table += "<th>" + weeks[i] + "</th>";
        }
        table += "</tr></thead><tbody>";
        var rowCount= Math.ceil((lastDay + startDay) / 7);
        for (var row = 1; row <= rowCount; row++) {
            table += "<tr>";
            for (var col = 0; col < 7; col++) {
                var selected = "";
                if ((col < startDay && row === 1) || (dayNumber > lastDay)) {
                    table += '<td></td>';
                }
                else {
                    if (dayNumber === options.day) {
                        selected = "selected";
                    }
                    table += '<td data-date="' + options.year + '-' + (options.month + 1) + '-' + dayNumber + '" class="' + selected + '">' + (dayNumber++) + '</td>';
                }
            }
            table += "</tr>";
        }
        table += "</tbody></table>";
        $("table._jt").remove();
        $(table).appendTo(this);
    }
    var methods = {
        init: function () {
            $.fn._default = {
                year: new Date().getFullYear(),
                month: new Date().getMonth(),
                day: new Date().getDate(),
                onYearChange:function(){},
                onMonthChange:function(){},
                onDayChange:function(){}
            }
            var options = tempOptions = $.extend({}, $.fn._default, arguments[0]);
            return this.each(function () {
                _renderBody.call(this,options);
                _bindEvent();
            });
        }
    }
    var calendar = function () {
        return new $.fn.calendar();
    };
    $.fn.calendar = function () {
        self = this;
        var method = arguments[0];
        if (!method || typeof method === "object") {
            methods.init.call(this, arguments[0]);
        }
        else {
            methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        }
    };
    return calendar;
}(jQuery));