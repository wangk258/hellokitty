/**
 * Created by Administrator on 4/8/2017.
 */
module.exports = {
    isNullOrEmpty(val,zero){
      if(typeof val === 'string'){
          return !val || !val.trim();
      }
      if(val === 0){
          return zero;
      }
      return !val;
    },
    showErrorMsg(msg){
        this.showMsg(msg,'error');
    },
    showSuccessMsg(msg){
        this.showMsg(msg,'success');
    },
    showMsg(msg,type){
        Vue.prototype.$message({
            message:msg,
            type:type
        });
    }
}
