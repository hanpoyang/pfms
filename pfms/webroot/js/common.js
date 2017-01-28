function checkTokenAction(){
    $("a[useToken='true']").bind({click:function(){
       var linkObj = $(this);
       $.getJSON(context + "token", function(data){
            if(data.flag == "success") {
                var href = linkObj.attr("href");
                var token = data.token;
                if(href.indexOf("?") > -1){
                    href += "&token="+token;
                } else {
                    href += "?token="+token;
                }
                linkObj.attr("href", href);
                window.location.href = href;
            }
       });
       return false;
    }
    });
}

function submitForm(){
       var linkObj = $("form");
       $.getJSON(context + "token", function(data){
            if(data.flag == "success") {
                var href = linkObj.attr("action");
                var token = data.token;
                if(href.indexOf("?") > -1){
                    href += "&token="+token;
                } else {
                    href += "?token="+token;
                }
                linkObj.attr("action",href);
                linkObj.trigger("submit");
            }
       });
       return false;
}


function init(){
       checkTokenAction();
   }
   $(function(){
       init();
   });
   
function goBack(){
    history.go(-1);
}   

function turnToPage(pageNo) {
    console.log(pageNo);
    pageNo = pageNo > pageCount ? pageCount : pageNo < 1 ? 1 : pageNo;
    $("input[name='pageNo']").val(pageNo);
    $("form").trigger("submit");
}