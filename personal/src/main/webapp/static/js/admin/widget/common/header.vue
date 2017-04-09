<template>
    <div class="header_title">{{title}}</div>
</template>
<script>
    module.exports = {
        props:{
            title:{
                type:String,
                required:true
            }
        },
        data:function(){
            return{}
        },
        methods:{
        }
    };
</script>
<style lang="sass">
    .header_title{
        float:left;
        height:50px;
        line-height:50px;
        margin-left:15px;
        color:white;
        font-weight:bolder;
        font-size:18px;
    }
</style>