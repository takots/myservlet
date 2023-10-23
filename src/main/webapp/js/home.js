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

// copy msg 提示
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
// err msg 提示
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

// clear msg 提示
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

