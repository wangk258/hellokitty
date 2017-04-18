<template>
    <div class="content">
        <div class="toolbar">
            <ul class="toolbar_ul">
                <li class="toolbar_item" @click="add"><i class="el-icon-plus"></i>新增</li>
            </ul>
        </div>
        <ul class="album_list">
            <li class="album_item" v-for="album in albumList">
                <img src="/static/images/admin/photo/default.png" alt="" class="album_pic">
                <span class="album_text">{{album.name}}</span>
            </li>
        </ul>
    </div>
</template>
<script>
    module.exports = {
        data:function(){
            return{
                moment:window.moment,
                albumList:[]
            }
        },
        mounted(){
          this.search();
        },
        methods:{
            search(){
                this.$http.post('/photo/album/list.ajax').then(resp =>{
                   if(resp && resp.ok){
                       let data = resp.body;
                       if(data && data.error){
                           utils.showErrorMsg(data.msg);
                       }else{
                           this.albumList = data.data.recordList;
                       }
                   }else{
                       utils.showErrorMsg(constant.UNKNOWN_ERROR);
                   }
                }).catch(err=>{
                    utils.showErrorMsg(err.statusText);
                });
            },
            add(){
                dialog.showComponent(AlbumEdit,'新增相册',{});
            },
            edit(album){
                dialog.showComponent(AlbumEdit,'修改相册',album);
            }
        }
    };
</script>
<style lang="sass">
    .album_list{
        margin:0;
        margin-left:.3%;
        padding:0;
    }
    .album_item{
        display:inline-block;
        margin:1% 0;
        width:20%;
        text-align:center;
        position:relative;
        cursor:pointer;
        padding:0;
    }

    .album_pic{
        display:inline-block;
        width:95%;
    }

    .album_text{
        position:absolute;
        bottom:0;
        left:0;
        right:0;
        margin:auto;
        height:30px;
        line-height:30px;
        width:95%;
        background-color:rgba(0,0,0,.6);
        color:white;
        font-size:15px;
    }
</style>