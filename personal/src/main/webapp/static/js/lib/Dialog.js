/**
 * Created by Administrator on 1/7/2017.
 */
var closeFn = function () {
    dialog.close();
};
var Dialog = Vue.extend({
    template: '#dialogTemplate',
    data: function () {
        return {
            showDialog: false,
            isComponent:false,
            title: "",
            showTitle: true,
            content: '',
            showConfirmButton: false,
            confirmText: "确定",
            currentView:'',
            param:{},
            confirmHandler: function () {
            },
            showCancelButton: false,
            cancelText: "取消",
            cancelHandler: function () {
            }
        };
    },
    methods: {
        alert: function (content,title, handler) {
            this.showDialog = true;
            this.isComponent = false;
            this.showTitle = true;
            this.title = title || '提示';
            this.content = content;
            this.showConfirmButton = true;
            this.confirmHandler = function(){
                handler && handler();
                closeFn()
            }
        },
        confirm:function(content,confirmHandler,cancelHandler){
            this.showDialog = true;
            this.isComponent = false;
            this.content = content;
            this.showTitle = true;
            this.title = '操作确认';
            this.showConfirmButton = true;
            this.confirmHandler = function(){
                confirmHandler && confirmHandler();
                closeFn()
            };
            this.showCancelButton = true;
            this.cancelHandler = function(){
                cancelHandler && cancelHandler();
                closeFn()
            };
        },
        showComponent:function(name,title,param){
            this.showDialog = true;
            this.isComponent = true;
            this.showTitle = true;
            this.title = title;
            this.param = param;
            this.currentView=name;
        },
        close:function(){
            this.showDialog = false;
        }
    }
});
module.exports = Dialog;