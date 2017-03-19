<template>
    <el-table
            :data="diaryList"
            border
            style="width: 100%">
        <el-table-column v-for="field in tableData.fields" :prop="field"  :label="field"></el-table-column>
    </el-table>
</template>
<script>
    module.exports = {
        data(){
            return{
                diaryList:[]
            }
        },
        mounted (){
            this.search();
        },
        methods:{
            search (){
                $.get('/diary/list').then((data) => {
                    console.log(data);
                });
            }
        }
    };
</script>
<style lang="sass">
</style>