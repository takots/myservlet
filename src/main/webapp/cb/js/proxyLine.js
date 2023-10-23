function dateNeedMinus(minus,day){
    // 获取当前日期
    var currentDate = new Date();

    // 减去天
    if(minus){
        currentDate.setDate(currentDate.getDate() - day);
    }

    // 将日期格式化为需要的格式，例如：年-月-日
    var year = currentDate.getFullYear();
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // 月份从0开始，需要加1
    var day = currentDate.getDate().toString().padStart(2, '0');

    return year + '-' + month + '-' + day;
}

$("#clearRangeDate4").click(function(){
    $("#date4S").val('');
    $("#date4E").val('');
})

$("#clearRangeDate5").click(function(){
    $("#date5S").val('');
    $("#date5E").val('');
})

// 带入测试值
$("#test4").click(function(){
    $("#cb4").val('CB5');
    $("#sitepath4").val('B136');
    $("#superior4").val('sszy');
    $("#vip4").val('2');
    $("#date4M").val('9');
    $("#date4D").val('30');
    var dayS = dateNeedMinus(true,30);
    var dayE = dateNeedMinus(false,0);
    $("#date4S").val(dayS);
    $("#date4E").val(dayE);
});

$("#test5").click(function(){
    $("#cb5").val('CB5');
    $("#sitepath5").val('B136');
    $("#superior5").val('sszy');
    $("#vip5").val('5');
    $("#date5M").val('8');
    $("#date5D").val('60');
    var dayS = dateNeedMinus(true,60);
    var dayE = dateNeedMinus(false,0);
    $("#date5S").val(dayS);
    $("#date5E").val(dayE);
});

// 檢查空值
function dataCheckProxyLine(data){
    var d = 0 ,c = 0;
    var isCheck = true;
    for (var key in data) {
        if(data[key].includes("step")){
            continue;
        }else if(key.includes("vip")){
            continue;
        }else if(key.includes("date")){
            if((key == 'date4S'|| key =='date4E')
            || (key == 'date5S'|| key =='date5E')){
                c++;
            }

            if(data[key].trim()!==''){
                d++;
                // 避免多個時間
                if(d>1){
                    if(c == 2 && d == 2){
                    // 選擇區間
                    }else {
                        dateChoseOnly();
                        isCheck = false;
                    }
                }
            }
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

function dateChoseOnly(){
    Swal.fire(
      '',
      '時間範圍請則一輸入',
      'question'
    )
}

// 功能說明
$(".proxyLine_functionDescription_inside").click(function(){
    var text = '<div style="margin-left: 25px;text-align: left;">'
            +'欄位說明:<br>'
            +'時間範圍0: 超過兩個月 = 當前月份假設10月 則輸入8<br>'
            +'時間範圍1: 超過兩個月 = 60<br>'
            +'時間範圍2: 今年89月  = 2023-08初 到 2023-09底<br>'
            +'<br>'
            +'按鈕說明:<br>'
            +'clear: 清除当前书签 input 跟 sql<br>'
            +'带入测试值: 带入测试值<br>'
            +'clear Range: 清除時間範圍2<br>'
            +'go: 产生sql<br>'
            +'copy: 克隆sql<br></div>';
    Swal.fire(
      '',
      text,
      'question'
    )
});

// 未登入
$("#proxyLineNotLogin").click(function(){
    var data = {
        step: "proxyLineNotLogin",
        cb4: $("#cb4").val(),
        sitepath4: $("#sitepath4").val(),
        superior4: $("#superior4").val(),
        vip4: $("#vip4").val(),
        date4M: $("#date4M").val(),
        date4D: $("#date4D").val(),
        date4S: $("#date4S").val(),
        date4E: $("#date4E").val()
    };
    if(dataCheckProxyLine(data)){
        $.ajax({
            type: "POST",
            url: "/cb",
            data: data,
            dataType: "HTML",
            success: function(result){
                $("#copy4").show();
                $("#selectSql4").html(result);
            }
        });
    }else {
        $("#selectSql4").html('');
        $("#copy4").hide();
    }
});

// 未投注
$("#proxyLineNotBet").click(function(){
    var data = {
        step: "proxyLineNotBet",
        cb5: $("#cb5").val(),
        sitepath5: $("#sitepath5").val(),
        superior5: $("#superior5").val(),
        vip5: $("#vip5").val(),
        date5M: $("#date5M").val(),
        date5D: $("#date5D").val(),
        date5S: $("#date5S").val(),
        date5E: $("#date5E").val()
    };
    if(dataCheckProxyLine(data)){
        $.ajax({
            type: "POST",
            url: "/cb",
            data: data,
            dataType: "HTML",
            success: function(result){
                $("#copy5").show();
                $("#selectSql5").html(result);
            }
        });
    }else {
        $("#selectSql5").html('');
        $("#copy5").hide();
    }
});