//$.ajax({
//    url:"http://open.iciba.com/dsapi",
//    type:"GET",
//    dataType:"jsonp",
//    jsonp:"callback",
//    jsonpCallback:"ok_callback",
//    async:false,
//    success:function(result){
//        $("#content").text(result.content);
//    },
//    error:function(){
//        alert(arguments[1]);
//    }
//});
define(["slider"], function (Slider) {
    var slider = new Slider({
        container: $("#banner"),
        height:"30rem",
        images:[{
                src:"http://desk.fd.zol-img.com.cn/t_s1920x1080c5/g5/M00/02/0F/ChMkJldOnemIb7ISAAdhlddvW_QAASJQAH0BNoAB2Gt478.jpg"
            },
            {
                src:"http://desk.fd.zol-img.com.cn/t_s1920x1080c5/g5/M00/02/0F/ChMkJ1dOneGIHRHMAAsBfbPRSQUAASJPwE_2BYACwGV021.jpg"
            },
            {
                src:"http://desk.fd.zol-img.com.cn/t_s1920x1200c5/g5/M00/01/0E/ChMkJldM8SKICKFsAAzc7c2v24oAASFBQLoxGwADN0F457.jpg"
            }]
    });
});