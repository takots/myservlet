$(function(){
    box();
})
// 创建一个包含1到25的数字数组
var numbers = Array.from({ length: 25 }, (_, i) => i + 1);

function noRepeatRandomNumber(){
    if (numbers.length === 0) {
        // 所有数字都已经被使用，重置数组
        numbers = Array.from({ length: 25 }, (_, i) => i + 1);
    }
    // 随机选择一个索引
    var randomIndex = Math.floor(Math.random() * numbers.length);
    // 获取随机数字
    var randomUniqueNumber = numbers[randomIndex];
    // 从数组中删除已经使用的数字
    numbers.splice(randomIndex, 1);
    return randomUniqueNumber;
}

function box(){
    var n = 25;
    var row = 0;
    var randomInt = 0;
    var c = 0;
    for(var i = 0 ; i< n ; i++){
        if(i % 5 == 0){
            row++;
            var $rowdiv = $("<div></div>")
                            .addClass("row" + row);
            $(".randomDiv").append($rowdiv);
        }

        randomInt = noRepeatRandomNumber();
        var $div = $("<div></div>")
            .addClass("randombox" + randomInt + " default-bg")
            .text(randomInt)
            .click(function() {
                $(this).toggleClass("default-bg clicked-bg"); // 切换类
                c++;
                if(c == 1){
                    // 執行開始倒數
                    countdownStart();
                }
            });
        $rowdiv.append($div);
    }
}

function clearBoxRestart(){
    $("div[class*='row']").remove();
    $("div[class*='randombox']").remove();
    box();
    $("#timer").text(10);
}

function showTryAgainMsg(){
    Swal.fire({
      title: 'Do you want to try again?',
      showDenyButton: true,
      confirmButtonText: 'try',
      denyButtonText: `Don't try`,
    }).then((result) => {
      if (result.isConfirmed) {
            clearBoxRestart();
      } else if (result.isDenied) {
      }
    })
}

var timer = parseInt($("#timer").text());
var intervalId;

// 倒數計時
function countdownStart(){
    clearInterval(intervalId);
    intervalId =
    setInterval(function(){
        timer -- ;
        $("#timer").text(timer);
        if (timer <= 0) {
            clearInterval(intervalId);
            showTryAgainMsg();
        }
    }, 1000);
}

// 重新開始
$("#restart1").click(function(){
    clearBoxRestart();
    clearInterval(intervalId);
})

// 自己增加秒數
$("#timer").click(function(){
    timer++;
    $("#timer").text(timer);
    countdownStart();
})