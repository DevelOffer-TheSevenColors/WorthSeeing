
 function remaindTime() {
   console.log($("#reservation_seq").val());
     
    var now = new Date();
    console.log();
    if($('#auction_stop').val() == 'yes'){
    console.log("@@"+$("#reservation_seq").val());
        $.ajax({
              url: "/auction/endAuction",
              type: "POST",
              data: {reservation_seq : $("#reservation_seq").val()},
              success: function(data){
                 console.log(data);
              },
              error: function(){
                 console.log("err");
              }
        });
       var end = new Date(now.getFullYear(),now.getMonth(),now.getDate());
    }else{
       var end = new Date(now.getFullYear(),now.getMonth(),now.getDate(),23,00,00);
    }
    var open = new Date(now.getFullYear(),now.getMonth(),now.getDate(),00,00,00);
  
    var nt = now.getTime();
    var ot = open.getTime();
    var et = end.getTime();
  
   if(nt<ot){
       $("#ending").hide();
       $("#starting").hide();
       $("#bidbutton").hide();
       $("#opening").show();
     $(".time").fadeIn();
    // $("p.time-title").html("금일 오픈까지 남은 시간");
     sec =parseInt(ot - nt) / 1000;
     day  = parseInt(sec/60/60/24);
     sec = (sec - (day * 60 * 60 * 24));
     hour = parseInt(sec/60/60);
     sec = (sec - (hour*60*60));
     min = parseInt(sec/60);
     sec = parseInt(sec-(min*60));
     if(hour<10){hour="0"+hour;}
     if(min<10){min="0"+min;}
     if(sec<10){sec="0"+sec;}
      $(".hours").html(hour);
      $(".minutes").html(min);
      $(".seconds").html(sec);
   }else if(parseInt(nt-et)>0&&parseInt(nt-et)<1000){
         $.ajax({
              url: "/auction/endAuction",
              type: "POST",
              data: {reservation_seq : $("#reservation_seq").val()},
              success: function(data){
                 console.log(data);
              },
              error: function(){
                 console.log("err");
              }
        });
   
   }else if(nt>et){
      $("#starting").hide();
      $("#opening").hide();
      $("#bidbutton").hide();
      $("#ending").show();
      $("#stringTitle").hide();
  //  $("span.time-title").html("금일 마감");
    $(".time").fadeOut();
   }else {
   
    $("#stop_seq").val($("#reservation_seq").val())
    $.ajax({
        url: "/auction/selectAuction",
        type: "POST",
        data: {reservation_seq : $("#reservation_seq").val()},
        success: function(data){
            $('#currentPrice').val(data.suggestPrice);
            $('#currentPrice1').val(data.suggestPrice);
            $('#currentPriceSend').val(data.suggestPrice);
            $('#startPrice').val(data.auctionPrice);
            $('#currentMaxPrice').val(data.maxPrice);
            $('#currentMaxPrice1').val(data.maxPrice);
            $('#userAutoId').val(data.userAutoId);
             if($('#userAutoId').val() == $('#sessionUserId').text()){
                $("#autoBiddingStop").show();
               $("#bidbutton").hide();
           }else{
               
              $("#autoBiddingStop").hide();
              $("#bidbutton").show();
            }
        },
        error: function(){
        }
     });
     
     $.ajax({
        url: "/auction/selectBlock",
        type: "POST",
        data: {reservation_seq : $("#reservation_seq").val()},
        success: function(data){
            var block ="";
            console.log(data);
            $.each(data , function(i){
                block += data[i].block_seq +",";
            });
            block = block.substring(0,block.length-1);

            $('#block').val(block);
              var date = new Date();
               $('#startDate').val( new Date(date.getFullYear(), date.getMonth() + 1, 1).toLocaleDateString() );
        },
        error: function(){
           console.log("hey");
           console.log("err");
        }
     });
     
      $.ajax({
          url: "/auction/autoBidding",
          type: "POST",
          data: {reservation_seq : $("#reservation_seq").val()},
          success: function(data){
             console.log(data);
          },
          error: function(){
             console.log("err");
          }
     });
   
   $("#ending").hide();
      $("#opening").hide();
      $("#starting").show();
       $(".time").fadeIn();
     sec =parseInt(et - nt) / 1000;
     day  = parseInt(sec/60/60/24);
     sec = (sec - (day * 60 * 60 * 24));
     hour = parseInt(sec/60/60);
     sec = (sec - (hour*60*60));
     min = parseInt(sec/60);
     sec = parseInt(sec-(min*60));
     if(hour<10){hour="0"+hour;}
     if(min<10){min="0"+min;}
     if(sec<10){sec="0"+sec;}
      $(".hours").html(hour);
      $(".minutes").html(min);
      $(".seconds").html(sec);
   }
 }
 
 $(window).on("load",function(){
 $('#auction_stop_btn').click(function(){
    console.log("qweq2e12");
  $('#auction_stop').val('yes');
});
 $("#autoBiddingStop").hide();
 $("#bidbutton").hide();
    remaindTime();
    $(".autoPrice").hide();
    $("#autoCheck").on('click', function() {
         if ($('#autoCheck').is(':checked')) {
           $(".autoPrice").show();
           $(".sPrice").hide();
         } else {
           $(".autoPrice").hide();
           $(".sPrice").show();
         }
   });
 });
 
 
 setInterval(remaindTime, 1000);
 
function checkForm() {
    var suggestPrice = document.fmField.suggestPrice;
    var autoPrice = document.fmField.autoPrice;
    
    // 암호 입력 유무 체크
    if(!$('#autoCheck').is(':checked')){
       if(suggestPrice.value == ''){
           alert('입찰가격을 입력해주세요!!');
           suggestPrice.focus();
           return false;
       }else if(parseInt(suggestPrice.value) <= parseInt($('#currentPrice').val())){
           alert('현재 입찰가격보다 큰 금액을 입력해주세요!');
           suggestPrice.focus();
           return false;
       }
    }else{
       if( autoPrice.value == '' ){
           alert('최대입찰가격을 입력해주세요!');
           return false;
       }else if( parseInt($('#currentPrice').val()) >= parseInt(autoPrice.value) ){
           alert('현재 현재입찰가격보다 큰 금액을 입력해주세요!');
           return false;
       }else if( parseInt($('#currentMaxPrice').val()) >= parseInt(autoPrice.value) ){
           alert('현재 최대입찰가격보다 큰 금액을 입력해주세요!');
           return false;
       }
    }
}

$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})