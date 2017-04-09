<template>
    <div class="content">
        <div class="toolbar">
            <ul class="toolbar_ul">
                <li class="toolbar_item" @click="add()"><i class="el-icon-plus"></i>新增</li>
                <li class="toolbar_item" @click="edit()"><i class="el-icon-edit"></i>编辑</li>
                <li class="toolbar_item" @click="del()"><i class="el-icon-delete"></i>删除</li>
            </ul>
        </div>
        <el-table
                :data="diaryList"
                :highlight-current-row="true"
                @current-change="changeRow"
                border>
            <el-table-column
                    prop="id"
                    label="Id">
            </el-table-column>
            <el-table-column
                    :show-overflow-tooltip="true"
                    prop="content"
                    label="内容">
            </el-table-column>
            <el-table-column
                    :show-overflow-tooltip="true"
                    prop="plainText"
                    label="纯文本">
            </el-table-column>
            <el-table-column
                    inline-template
                    label="日期">
                <span>{{moment(new Date(row.date / 1)).format('YYYY-MM-DD')}}</span>
            </el-table-column>
            <el-table-column
                    prop="weather"
                    label="天气">
            </el-table-column>
        </el-table>
    </div>
</template>
<script>
    module.exports = {
        data(){
            return{
                diaryList:[],
                moment:window.moment,
                currentRow:null
            }
        },
        mounted (){
            this.search();
        },
        methods:{
            search (){
                this.$http.get('/diary/list.ajax').then((data) => {
                    if(data && data.ok){
                        this.diaryList = data.body.data.recordList;
                    }
                }).catch(err =>{
                    utils.showErrorMsg(err.statusText);
                })
            },
            add(){
                dialog.showComponent(DiaryEdit,'日记添加/编辑',{
                    date:'',
                    action:'add',
                    content:''
                });
            },
            edit(){
                if(!this.currentRow){
                    dialog.alert('请先选择要操作的数据！');
                    return;
                }
                this.currentRow.date -= 0
                dialog.showComponent(DiaryEdit,'日记编辑',JSON.parse(JSON.stringify(this.currentRow)));
            },
            changeRow(row){
                this.currentRow = row;
            },
            del(){
                if(!this.currentRow){
                    dialog.alert('请选择要删除的日记！');
                    return;
                }
                dialog.confirm('确实要删除该条日记吗？',() =>{
                    let ids = [this.currentRow.id];
                    this.$http.get('/diary/delete.ajax?ids='+ids).then(resp =>{
                        if(resp && resp.ok){
                            let data = resp.body;
                            if(data.error){
                                utils.showErrorMsg(data.msg);
                            }else{
                                utils.showSuccessMsg('该条日记已经从地球上消失了！');
                                this.search();
                            }
                        }else{
                            utils.showErrorMsg(constant.UNKNOWN_ERROR);
                        }
                    }).catch(err => {
                        utils.showErrorMsg(err.statusText);
                    });
                });
            }
        }
    };
</script>
<style lang="sass">
</style>