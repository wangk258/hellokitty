<template>
    <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
</template>
<script>
    module.exports = {
        data() {
            return {
                data: [{
                    label: '待定',
                    children: [{
                        label: '心灵日记',
                        href:'#/diary'
                    },{
                        label:'时光纪念册',
                        href:'#/album'
                    }]
                }],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                }
            };
        },
        methods: {
            handleNodeClick(node) {
                if(!node.children){
                    window.location.href = node.href;
                }
            }
        }
    };
</script>
<style lang="sass">
</style>