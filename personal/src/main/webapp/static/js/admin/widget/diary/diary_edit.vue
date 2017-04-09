<template>
    <div class="content diary_edit">
        <div class="line">
            <el-date-picker
                    v-model="param.date"
                    type="date"
                    placeholder="选择日期">
            </el-date-picker>
        </div>
        <div class="line">
            <el-input v-model="param.week" :disabled="true"></el-input>
        </div>
        <div class="line">
            <el-input v-model="param.weather" placeholder="天气情况"></el-input>
        </div>
        <div class="line auto_height">
            <textarea id="content-editor" v-model="param.content"></textarea>
        </div>
        <div class="line center">
            <el-button type="primary" icon="check" @click="save()">保存</el-button>
        </div>
    </div>
</template>
<script>
    module.exports = {
        props: {
            param: Object
        },
        data(){
            return {}
        },
        watch: {
            'param.date'(val){
                var weekArray = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
                this.param.week = weekArray[new Date(val).getDay()];
            }
        },
        mounted (){
            this.renderEditor();
        },
        methods: {
            renderEditor() {
                var contentEditor = new UE.ui.Editor({
                    initialFrameWidth: "100%",
                    initialFrameHeight: window.screen.availHeight * 0.60,
                    imageUrl: "/upload.ajax?type=diary"
                });
                setTimeout(() => {
                    contentEditor.render("content-editor");
                    contentEditor.ready(() => {
                        if (this.param.action === 'add') {
                            contentEditor.setContent('');
                        }
                        contentEditor.addListener("contentChange", () => {
                            this.param.content = contentEditor.getContent();
                            this.param.plainText = contentEditor.getPlainTxt();
                        });
                    });
                }, 0);
            },
            save(){
                if (utils.isNullOrEmpty(this.param.date)) {
                    utils.showErrorMsg('你想写记录的是哪一天的生活啊？');
                    return;
                }
                if (utils.isNullOrEmpty(this.param.weather)) {
                    utils.showErrorMsg('那一天的天气情况如何？');
                    return;
                }
                if (utils.isNullOrEmpty(this.param.content)) {
                    utils.showErrorMsg('既然来都来了，写点什么呗');
                    return;
                }

                this.param.date = +new Date(this.param.date);

                delete this.param.updateTime;

                delete this.param.createTime;

                this.$http.post('/diary/saveOrUpdate.ajax', this.param).then((data) => {
                    if (data && data.ok) {
                        data = data.body;
                    }
                    if (data) {
                        if (data.error) {
                            utils.showErrorMsg(data.msg);
                        } else {
                            utils.showSuccessMsg('地球上又多了一条日记了');
                            dialog.close();
                            root.$refs.sub.search();
                        }
                    } else {
                        utils.showErrorMsg('我不知道出现了什么错误了！');
                    }
                }).catch(err => {
                    utils.showErrorMsg(err.statusText);
                });
            }
        }
    };
</script>
<style lang="sass">
    .diary_edit .el-input, .el-date-editor.el-input {
        width: 100%;
    }

    #edui_fixedlayer {
        z-index: 3000 !important;
    }
</style>