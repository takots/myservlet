$(function(){
    for(var i=1;i<=1;i++){
        if ($("#MAselectSql"+i).text().trim() === "") {
            $("#MAcopy"+i).hide();
        }
    }
})

// 带入测试值
$("#MAtest1").click(function(){
    $("#MAcb1").val('CB3');
    $("#MAsitepath1").val('A012');
    $("#MAloginname1").val('526126564');
});

// 当前书签的 copy 跟 clear
for(var i=1;i<=1;i++){
    /**
     * 使用 IIFE (立即执行函数表达式) 捕获了每次迭代的 i 值，将其传递给匿名函数，从而在每个按钮点击事件处理程序中使用正确的 i 值。
     * 这将确保每个按钮都能正常工作，而不会受到闭包的影响。
     */
    (function (index) {
        $("#MAcopy"+index).click(function(){
            selectText("MAselectSql"+index);
            document.execCommand('copy');
            showCopyMsg();
        });

        $("#MAclear"+index).click(function(){
            $("#MAselectSql"+index).html('');
            $("#MAcopy"+index).hide();
            $('.MA'+index+'Div input').val('');
        });
    })(i);
}

// 點大標可隱藏
$("#MAfreezeReason1").click(function(){
    $('.MAcloseSearch1Div').toggle();
})

$("#MAfreeze1").click(function(){
    var data = {
        step: "memberAccountFreeze",
        MAcb1: $("#MAcb1").val(),
        MAsitepath1: $("#MAsitepath1").val(),
        MAloginname1: $("#MAloginname1").val(),
    };
    if(dataCheck(data)){
        $.ajax({
            type: "POST",
            url: "/cb",
            data: data,
            dataType: "HTML",
            success: function(result){
                $("#MAcopy1").show();
                $("#MAselectSql1").html(result);
            }
        });
    }else {
        $("#MAselectSql1").html('');
        $("#MAcopy1").hide();
    }
})