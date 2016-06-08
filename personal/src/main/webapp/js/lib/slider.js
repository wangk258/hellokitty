/**
 * Created by ahpeng on 2016/6/7.
 */
!function(c){
    if(define && define.amd && typeof define === "function"){
        return define(["jquery"],c);
    }
    return c(jQuery);
}(function($){
    function Slider(options){
        var defaultOptions = {
            container:null,
            images:[],
            template:'<img src="${src}" style="width:${width};height:100%"/>'
        };
        this.options = $.extend({},defaultOptions,options || {});
        this.init();
    }
    Slider.prototype={
        constructor:Slider,
        init:function(){
            var width = document.body.clientWidth +"px";
            var height = this.options.height;
            var self = this;
            this.options.container.html(processTemplate(self.options.template,{
                width:width,
                height:height,
                images:self.options.images
            }));
        }
    }

    function processTemplate(template,data){
        var result="";
        for(var i=0;i<data.images.length;i++){
            var images = data.images[i];
            result += template.replace(/\${\w*}/g,function(match){
                match = match.replace(/[\$|{|}]/g,"");
                return data[match] || images[match];
            });
        }
        return result;
    }
    return Slider;
});
