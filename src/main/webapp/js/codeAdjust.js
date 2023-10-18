/* adjustment_tab1 */
$(function(){
    if($("#adjustSql1").text().trim() === "") {
        $("#adjustCopy1").hide();
    }
})

function showCopyMsg(){
    Swal.fire({
      title:'Good job!',
      text:'You copied this text!',
      icon:'success',
      backdrop: `
        rgba(0,0,123,0.4)
        left top
        no-repeat
      `
    })
}

// 檢查空值
function dataCheck(data){
    var isCheck = true;
    for (var key in data) {
        if(data[key].includes("step")){
            continue;
        }else if(data[key].trim() === ''){
            showErrMsg();
            isCheck = false;
            $("#"+key).css("background-color","#D6D6FF");
        }else {
            $("#"+key).css("background-color","white");
        }
    }
    return isCheck;
}

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
    $("#adjustStr").val(' int page, int pageSize,&nbsp;&nbsp;'
                              +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String createDatTime, String createDatTimeEnd, Integer accountId,String loteryId,String cpLoteryId&nbsp;&nbsp;&nbsp;'
                              +'&nbsp;&nbsp;&nbsp;,String status,boolean isNew,Integer lastId,String tradeType');
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