$(function(){
    for(var i=1;i<=5;i++){
        if ($("#selectSql"+i).text().trim() === "") {
            $("#copy"+i).hide();
        }
    }
})

// 功能说明
$(".transferProxyLine_functionDescription").click(function(){
    var text = '<div style="margin-left: 30px;text-align: left;">'
//    clear all: 清除所有 input 跟 sql<br>
            +'转移代理线-step1: 查询step2要用到的资讯<br>'
            +'转移代理线-step2: 更新代理相关资讯<br>'
            +'转移代理线-step3: 检查更新代理相关资讯是否正确<br>'
            +'代理线底下未登入: 代理线xxx底下xx天未登入的下级账号的相关资讯<br>'
            +'代理线底下未投注: 代理线xxx底下xx天未投注的下级账号的相关资讯<br>'
            +'close: 关闭当前书签<br></div>';
    Swal.fire(
      '',
      text,
      'question'
    )
});

$(".transferProxyLine_functionDescription_inside").click(function(){
    var text = '<div style="margin-left: 70px;text-align: left;">'
            +'clear: 清除当前书签 input 跟 sql<br>'
            +'带入测试值: 带入测试值<br>'
            +'go: 产生sql<br>'
            +'copy: 克隆sql<br></div>';
    Swal.fire(
      '',
      text,
      'question'
    )
});

// msg 提示
function showClearMsg(){
    Swal.fire({
      title: 'Clear!',
      padding: '3em',
      color: '#716add',
      timer: 1000,
      backdrop: `
        rgba(0,0,123,0.4)
        left top
        no-repeat
      `
    })
}

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

function showErrMsg(){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Get Some Help!',
      backdrop: `
          rgba(0,0,123,0.4)
          left top
          no-repeat
        `
    })
}

// 当前书签的 copy 跟 clear
for(var i=1;i<=5;i++){
    /**
     * 使用 IIFE (立即执行函数表达式) 捕获了每次迭代的 i 值，将其传递给匿名函数，从而在每个按钮点击事件处理程序中使用正确的 i 值。
     * 这将确保每个按钮都能正常工作，而不会受到闭包的影响。
     */
    (function (index) {
        $("#copy"+index).click(function(){
            selectText("selectSql"+index);
            document.execCommand('copy');
            showCopyMsg();
        });

        $("#clear"+index).click(function(){
            $("#selectSql"+index).html('');
            $("#copy"+index).hide();
            $('.step'+index+'Div input').val('');
            $('.proxyLine'+index+'Div input').val('');
        });
    })(i);
}

// copy
function selectText(e){
    var text = document.getElementById(e);
    if (document.body.createTextRange) {
        var range = document.body.createTextRange(); //获取range
        range.moveToElementText(text); //光标移上去
        range.select();  //选择
    } else if (window.getSelection) {
        var selection = window.getSelection(); //获取selection
        var range = document.createRange(); //创建range
        range.selectNodeContents(text);  //选择节点内容
        selection.removeAllRanges(); //移除所有range
        selection.addRange(range);  //添加range
    }
}

$("#clearall").click(function(){
    for(var i=1;i<=3;i++){
        $("#selectSql"+i).html('');
        $("#copy"+i).hide();
    }
    $('input').val('').css("background-color","white");
    showClearMsg();
    openTabs(event, '');
});

// 带入测试值
$("#test1").click(function(){
    $("#cb1").val('CB5');
    $("#sitepath1").val('F108');
    $("#superior1").val('flcp2023');
    $("#subordinate1").val('flc001');
});

$("#test2").click(function(){
    $("#cb2").val('CB5');
    $("#sitepath2").val('F108');
    $("#accountNamePath").val("liebian001/weihu001/flc001/");
    $("#accountIdPath").val("405237091/405244005/405112641/");
    $("#superior2").val('flcp2023');
    $("#superiorId").val("405112342");
    $("#subordinate2").val('flc001');
    $("#subordinateId").val("405112641");
    $("#platformId").val("40005");
});

$("#test3").click(function(){
    $("#cb3").val('CB5');
    $("#sitepath3").val('F108');
    $("#superior3").val('flcp2023');
    $("#subordinate3").val('flc001');
});


// sql
$("#transferProxyLineStep1").click(function(){
    var data = {
        step: "transferProxyLineStep1",
        cb1: $("#cb1").val(),
        sitepath1: $("#sitepath1").val(),
        superior1: $("#superior1").val(),
        subordinate1: $("#subordinate1").val()
    };
    if(dataCheck(data)){
        $.ajax({
            type: "POST",
            url: "/proxy",
            data: data,
            dataType: "HTML",
            success: function(result){
                $("#copy1").show();
                $("#selectSql1").html(result);
            }
        });
    }else {
        $("#selectSql1").html('');
        $("#copy1").hide();
    }
});

$("#transferProxyLineStep2").click(function(){
    var data = {
        step: "transferProxyLineStep2",
        cb2: $("#cb2").val(),
        sitepath2: $("#sitepath2").val(),
        accountNamePath: $("#accountNamePath").val(),
        accountIdPath: $("#accountIdPath").val(),
        superior2: $("#superior2").val(),
        superiorId: $("#superiorId").val(),
        subordinate2: $("#subordinate2").val(),
        subordinateId: $("#subordinateId").val(),
        platformId: $("#platformId").val()
    };
    if(dataCheck(data)){
        $.ajax({
            type: "POST",
            url: "/proxy",
            data: data,
            dataType: "HTML",
            success: function(result){
                $("#copy2").show();
                $("#selectSql2").html(result);
            }
        });
    }else {
        $("#selectSql2").html('');
        $("#copy2").hide();
    }
});

 $("#transferProxyLineStep3").click(function(){
     var data = {
        step: "transferProxyLineStep3",
        cb3: $("#cb3").val(),
        sitepath3: $("#sitepath3").val(),
        superior3: $("#superior3").val(),
        subordinate3: $("#subordinate3").val()
     };
    if(dataCheck(data)){
        $.ajax({
            type: "POST",
            url: "/proxy",
            data: data,
            dataType: "HTML",
            success: function(result){
                $("#copy3").show();
                $("#selectSql3").html(result);
            }
        });
    }else {
        $("#selectSql3").html('');
        $("#copy3").hide();
    }
});