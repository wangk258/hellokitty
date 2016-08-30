/**
 * Created by qmtv on 2016/8/29.
 */
!function (P) {
    var define;
    if (define && define.amd && typeof define === "function") {
        return define(P);
    }
    return P(jQuery);
}(function ($) {

    function Accordion(options) {
        var default_options = {
            items: [],
            duration: 500,
            firstExpand:true,
            itemTmpl:"<li class='w-accordion_item'>" +
                            "<div class='w-accordion_title'>${text}</div>" +
                            "<div class='w-accordion_sub_wrap'><ul>${subHtml}</ul></div>"+
                        "</li>",
            subItemTmpl:'<li class="w-accordion_sub_item"><a class="w-accordion_a" href="${href}">${text}</a></li>'
        };
        this.options = $.extend({}, default_options, options);
        this.render();
    }

    function BindEvent(){
        var self = this;
        $(".w-accordion_item").on("click",function(){
            if($(this).hasClass("expand")) return;
            $(this).parent().find(".expand").removeClass("expand").find(".w-accordion_sub_wrap").slideUp(self.options.duration);
            $(this).addClass("expand").find(".w-accordion_sub_wrap").slideDown(self.options.duration);
        });
    }

    function processTemplate(tmpl,data){
        return tmpl.replace(/\${(\w+)}/g,function(match,prop) {
            return data[prop];
        });
    }

    $.extend(Accordion.prototype, {
        render: function () {
            var str = "<ul class='w-accordion'>",
                itemTmpl = this.options.itemTmpl,
                subItemTmpl = this.options.subItemTmpl;
            for(var i=0;i<this.options.items.length;i++){
                var item= this.options.items[i];
                var subItem = item.sub;
                var substr = ""
                for(var j=0;j<subItem.length;j++){
                    substr += processTemplate(subItemTmpl,subItem[j]);
                }
                item.subHtml = substr;
                str += processTemplate(itemTmpl,item);
            }
            str += "</ul>";
            $(str).appendTo(this.options.dom);
            var itemsEl = $(".w-accordion_item");
            $(".w-accordion_sub_wrap").height(this.options.dom.height() - itemsEl.length * (itemsEl.height() + 2));
            BindEvent.call(this);
            if(this.options.firstExpand){
                $(".w-accordion_item:first").click();
            }
        }
    });

    $.fn.accordion = function (options) {
        this.each(function () {
            options.dom = $(this);
            new Accordion(options);
        });
    }
});