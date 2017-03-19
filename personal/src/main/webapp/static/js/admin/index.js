/**
 * Created by penganhua on 2016/9/6.
 */
require( 'element-ui/lib/theme-default/index.css');
require('../../css/index.css');

require('expose?Vue!vue');
require('expose?_!underscore');
var dialog = require('../lib/Dialog.js');

require('vue-resource');

var ElementUI =  require( 'element-ui');
Vue.use(ElementUI);

var VueRouter = require('vue-router');
Vue.use(VueRouter);

var Admin = require('./widget/admin.vue');

var routes = [
    {
        path:'/',
        redirect:'admin'
    },
    {
        path:'/admin',
        name:'admin',
        component:Admin
    }
]

var router = new VueRouter({routes: routes});

var database = new Vue({
    el:'#personal',
    router:router,
    components:{
        'dialog-widget':dialog
    },
});
window.Dialog = database.$refs.dialog;