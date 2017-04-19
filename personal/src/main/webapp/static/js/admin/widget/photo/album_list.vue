<template>
    <div class="content">
        <div class="toolbar">
            <ul class="toolbar_ul">
                <li class="toolbar_item" @click="add"><i class="el-icon-plus"></i>新增</li>
            </ul>
        </div>
        <ul class="album_list">
            <li class="album_item" v-for="album in albumList">
                <img :src="album.imageUrl" alt="" class="album_pic">
                <div class="album_text">
                    <span>{{album.name}}</span>
                    <i class="el-icon-delete album_opt" @click="del(album.id)"></i>
                    <i class="el-icon-edit album_opt" @click="edit(album)"></i>
                </div>
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
            },
            del(id){
                dialog.confirm('确实要删除该相册吗？',() =>{
                   this.$http.post('/photo/album/delete.ajax',{id}).then(resp =>{
                       if(resp && resp.ok){
                           let data = resp.body;
                           if(data && data.error){
                               utils.showErrorMsg(data.msg);
                           }else{
                               this.search();
                           }
                       }else{
                           utils.showErrorMsg(constant.UNKNOWN_ERROR);
                       }
                   }).catch(err =>{
                       utils.showErrorMsg(err.statusText);
                   });
                });
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

        &:hover{
            box-shadow: 0 0 10px gray;
            transition:all .3s;
         }
    }

    .album_text{
        position:absolute;
        bottom:1px;
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

    .album_opt{
        color:gray;
        float:right;
        margin:0 3%;
        line-height:30px;

        &:hover{
             color:white;
             transition:all .5s linear;
         }
    }
</style>