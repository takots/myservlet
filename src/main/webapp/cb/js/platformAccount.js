$(function(){
    for(var i=1;i<=1;i++){
        if ($("#PAselectSql"+i).text().trim() === "") {
            $("#PAcopy"+i).hide();
        }
    }
})

// 带入测试值
$("#PAtest1").click(function(){
    $("#PAcb1").val('CB3');
    $("#PAsitepath1").val('A021');
    $("#PAloginname1").val('cpcaiwu04、cpcaiwu03、 cpcaiwu01');
});

// 当前书签的 copy 跟 clear
for(var i=1;i<=1;i++){
    /**
     * 使用 IIFE (立即执行函数表达式) 捕获了每次迭代的 i 值，将其传递给匿名函数，从而在每个按钮点击事件处理程序中使用正确的 i 值。
     * 这将确保每个按钮都能正常工作，而不会受到闭包的影响。
     */
    (function (index) {
        $("#PAcopy"+index).click(function(){
            selectText("PAselectSql"+index);
            document.execCommand('copy');
            showCopyMsg();
        });

        $("#PAclear"+index).click(function(){
            $("#PAselectSql"+index).html('');
            $("#PAcopy"+index).hide();
            $('.PA'+index+'Div input').val('');
        });
    })(i);
}

// 點大標可隱藏
$("#PAfreezeReason1").click(function(){
    $('.PAcloseSearch1Div').toggle();
})

$("#PAfreeze1").click(function(){
    var data = {
        step: "platformAccountFreeze",
        PAcb1: $("#PAcb1").val(),
        PAsitepath1: $("#PAsitepath1").val(),
        PAloginname1: $("#PAloginname1").val(),
    };
    if(dataCheck(data)){
        $.ajax({
            type: "POST",
            url: "/cb",
            data: data,
            dataType: "HTML",
            success: function(result){
                $("#PAcopy1").show();
                $("#PAselectSql1").html(result);
            }
        });
    }else {
        $("#PAselectSql1").html('');
        $("#PAcopy1").hide();
    }
})