

 function remaindTime() {
	
	 $.ajax({
        url: "/auction/selectAuction",
        type: "POST",
        data: {auction_seq : $("#auction_seq").val()},
        success: function(data){
            $('#currentPrice').val(data.suggestPrice);
            $('#startPrice').val(data.auctionPrice);
        },
        error: function(){
        }
  	});
  	
  	$.ajax({
        url: "/auction/selectBlock",
        type: "POST",
        data: {reservation_seq : $("#reservation_seq").val()},
        success: function(data){
            console.log(data[0].block_seq);
            var block ="";
            $.each(data , function(i){
                block += data[i].block_seq +",";
            });
            block = block.substring(0,block.length-1);
            $('#block').val(block);
        },
        error: function(){
        	console.log("err");
        }
  	});
  	
    var now = new Date();
    var end = new Date(now.getFullYear(),now.getMonth(),now.getDate(),15,00,00);
    var open = new Date(now.getFullYear(),now.getMonth(),now.getDate(),12,00,00);
  
    var nt = now.getTime();
    var ot = open.getTime();
    var et = end.getTime();
  
   if(nt<ot){
     $(".time").fadeIn();
     $("p.time-title").html("금일 오픈까지 남은 시간");
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
   } else if(nt>et){
    $("span.time-title").html("금일 마감");
    $(".time").fadeOut();
   }else {
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
 setInterval(remaindTime,1000);
 