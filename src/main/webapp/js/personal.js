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

function showErrIPMsg(){
    Swal.fire({
      icon: 'error',
      title: 'yes but no...',
      text: 'Get Some Help!',
      backdrop: `
          rgba(0,0,123,0.4)
          left top
          no-repeat
        `
    })
}

function hiddenShow(){
    var btn = document.getElementById("fixedBtn");
    btn.parentNode.removeChild(btn);
}

$("#fixedBtn").click(function(){
    Swal.fire({
      title: '輸入',
      input: 'text',
      padding: '3em',
      color: '#716add',
      timer: 1000,
      backdrop: `
        rgba(0,0,123,0.4)
        left top
        no-repeat
        `,
      inputAttributes: {
        autocapitalize: 'off'
      },
      showCancelButton: true,
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      showLoaderOnConfirm: true,
      preConfirm: (data) => {
          return new Promise((resolve, reject) => {
                $.ajax({
                    type: "POST",
                    url: "/proxy",
                    data: {step: "isme",password: data},
                    dataType: "HTML",
                    success: function(result){
                        if(result === "run"){
                            showErrMsg();
                        }else if(result === "sus"){
                            hiddenShow();
                        }else if(result === "ipno"){
                            showErrIPMsg();
                        }
                    }
                });
          })
      }
  })
})