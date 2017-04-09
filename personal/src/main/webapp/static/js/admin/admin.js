/**
 * Created by penganhua on 2016/9/6.
 */
require( 'element-ui/lib/theme-default/index.css');
require('../../css/admin.scss');

require('expose?Vue!vue');
require('expose?_!underscore');

require('vue-resource');

var ElementUI =  require( 'element-ui');
Vue.use(ElementUI);

var VueRouter = require('vue-router');
Vue.use(VueRouter);

Vue.http.options.emulateJSON = true;

require('expose?utils!../lib/utils');
require('expose?constant!../lib/const');
require('expose?moment!moment');

var Dialog = require('../lib/Dialog.js');
var Header = require('./widget/common/header.vue');
var SideBar = require('./widget/common/sidebar.vue');
var Admin = require('./widget/admin.vue');


var DiaryList = require('./widget/diary/diary_list.vue');
var DiaryEdit = require('./widget/diary/diary_edit.vue');

var AlbumList = require('./widget/photo/album_list.vue');
var AlbumEdit = require('./widget/photo/album_edit.vue');

var routes = [
    {
        path:'/',
        redirect:'main'
    },
    {
        path:'/main',
        name:'main',
        component:Admin
    },
    {
        path:'/diary',
        name:'diary',
        component:DiaryList
    },
    {
        path:'/album',
        name:'album',
        component:AlbumList
    }
];

var router = new VueRouter({routes: routes});

var personal = new Vue({
    el:'.personal',
    router:router,
    components:{
        'dialog-widget':Dialog,
        'header-widget':Header,
        'sidebar-widget':SideBar
    },
});
window.dialog = personal.$refs.dialog;
window.root = personal.$root;
window.DiaryEdit = DiaryEdit;
window.AlbumEdit = AlbumEdit;