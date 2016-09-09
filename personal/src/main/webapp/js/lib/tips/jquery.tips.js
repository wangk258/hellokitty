/**
 * Created by qmtv on 2016/8/19.
 */
(function($){
    var speed = 500;
    function Tips(options){
        var default_options = {
            content:"",
            duration:3000
        };
        this.options = $.extend({},default_options,options);
        this.render();
    }
    function Toast(options){
        if(typeof options === "string"){
            options = {
                content:options
            }
        }
        var default_options = {
            content:"",
            duration:2000
        };
        this.options = $.extend({},default_options,options);
        this.render();
    }
    $.extend(Tips.prototype,{
       render:function(){
           var self = this;
           var container = this.options.dom.parent();
           var tips = container.find(".w-tips");
           if(tips.length){
                tips.find(".w-tips_content").text(this.options.content);
           }else{
               var str = '<div class="w-tips"><div class="w-tips_content">'+this.options.content+'</div><div class="w-tips_arrow"></div></div>';
               if(!container.css("position")){
                   container.css({
                       position:relative
                   });
               }
               tips = $(str).appendTo(container);
           }
           tips.fadeIn(speed);
           setTimeout(function(){
                self.close();
           },this.options.duration);
       },
        close:function(){
           var tips = this.options.dom.parent().find(".w-tips");
            tips.fadeOut(speed,function(){
                tips.remove();
            });
        }
    });
    $.extend(Toast.prototype,{
        render:function(){
            var toast = $(".toast");
            if(toast.length){
                toast.text(this.options.content);
            }else{
                var self = this;
                var str = "<div class='toast_wrap'><span class='toast'>"+this.options.content+"</span></div>";
                toast = $(str).appendTo(document.body);
            }
            toast.fadeIn(speed);
            setTimeout(function(){
                self.close();
            },this.options.duration)
        },
        close:function(){
            var toast = $(".toast");
            toast.fadeOut(speed,function(){
                toast.remove();
            });
        }
    });
    $.fn.tips = function(options){
        this.each(function(){
            options.dom = $(this);
            new Tips(options);
        });
    };
    $.extend({
       toast:function(options){
           new Toast(options);
       }
    });
}(jQuery));