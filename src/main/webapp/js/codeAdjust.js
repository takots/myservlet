/* adjustment_tab1 */
$(function(){
    if($("#adjustSql1").text().trim() === "") {
        $("#adjustCopy1").hide();
    }
})

$("#adjustCopy1").click(function(){
    selectText("adjustSql1");
    document.execCommand('copy');
    showCopyMsg();
});

$("#adjustClear1").click(function(){
    $("#adjustSql1").html('');
    $("#adjustCopy1").hide();
    $('.adjust1Div input').val('');
});

// 带入测试值
$("#adjustTest1").click(function(){
    $("#adjustStr").val(' int page, int pageSize,'
                              +'          ,String createDatTime, String createDatTimeEnd, Integer accountId,String loteryId,String cpLoteryId'
                              +'              , String status,boolean isNew,Integer lastId,String tradeType');
});
$("#adjustTest2").click(function(){
    $("#adjustStr").val(' String customSettingId = request.getParameter("customSettingId");'
                                  		+'           ;; String remarks = request.getParameter("remarks").trim();'
                                  		+'            String result = request.getParameter("result");'
                                  		+'          String ids = request.getParameter("ids");');
});

$("#adjust").click(function(){
    var data = {
        step: "adjust",
        adjustStr: $("#adjustStr").val(),
    };
    if(dataCheck(data)){
        $.ajax({
            type: "POST",
            url: "/proxy",
            data: data,
            dataType: "HTML",
            success: function(result){
                $("#adjustCopy1").show();
                $("#adjustSql1").html(result);
            }
        });
    }else {
        $("#adjustSql1").html('');
        $("#adjustCopy1").hide();
    }
});