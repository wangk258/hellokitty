/**
 * Created by Administrator on 1/7/2017.
 */
var closeFn = function () {
    Dialog.close();
};
var dialog = Vue.extend({
    template: '#dialogTemplate',
    data: function () {
        return {
            showDialog: false,
            isComponent:false,
            title: "",
            showTitle: false,
            content: '',
            showConfirmButton: false,
            confirmText: "确定",
            currentView:'',
            confirmHandler: function () {
            },
            showCancelButton: false,
            cancelText: "取消",
            cancelHandler: function () {
            }
        };
    },
    methods: {
        alert: function (content, handler) {
            this.showDialog = true;
            this.isComponent = false;
            this.content = content;
            this.showConfirmButton = true;
            this.confirmHandler = function(){
                handler && handler();
                Dialog.close();
            }
        },
        confirm:function(content,confirmHandler,cancelHandler){
            this.showDialog = true;
            this.isComponent = false;
            this.content = content;
            this.showConfirmButton = true;
            this.confirmHandler = function(){
                confirmHandler && confirmHandler();
                Dialog.close();
            };
            this.showCancelButton = true;
            this.cancelHandler = function(){
                cancelHandler && cancelHandler();
                Dialog.close();
            };
        },
        showComponent:function(name){
            this.showDialog = true;
            this.isComponent = true;
            this.currentView=name;
        },
        close:function(){
            this.showDialog = false;
        }
    }
});
module.exports = dialog;