<template>
    <div class="content">
        <div class="line">
            <span class="text">名称</span>
            <el-input v-model="param.name"></el-input>
        </div>
        <div class="line center">
            <el-button type="primary" @click="save" icon="check">保存</el-button>
        </div>
    </div>
</template>
<script>
    module.exports = {
        props:{
            param:Object
        },
        data:function(){
            return{}
        },
        methods:{
            save(){
                this.$http.post('/photo/album/saveOrUpdate.ajax',this.param).then(resp =>{
                   if(resp && resp.ok){
                       let data = resp.body;
                       if(data && data.error){
                           utils.showErrorMsg(data.msg);
                       }else{
                           utils.showSuccessMsg('添加成功！');
                           dialog.close();
                           root.$refs.sub.search();
                       }
                   }else{
                       utils.showErrorMsg(constant.UNKNOWN_ERROR);
                   }
                }).catch(err =>{
                    utils.showErrorMsg(err.statusText);
                });
            }
        }
    };
</script>
<style lang="sass">

</style>