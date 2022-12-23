 $(window).on("load",function(){
 const mydata = [15, 28, 24, 35];
  const mydataHalf = [25, 20, 35, 30];
  // var ctx = document.getElementById("myChart");
  var ctx = document.getElementsByClassName("myChart");

  var mixedChart = {
    type: 'bar',
    labels: ['1Q', '2Q', '3Q', '4Q'],
    datasets : [
      {
        label: '매출액',
        data : mydata,
        backgroundColor: 'rgba(256, 0, 0, 0.1)'
      },
      {
        label: '영업이익율',
        data: mydataHalf,
        backgroundColor: 'transparent',
        borderColor: 'skyblue',
        type: 'line'
      }
    ]
    };
    var myChart = new Chart(ctx, {
      type: 'bar',
      data: mixedChart,
      options: {
        legend: {
          display: true
        }
      }
    });  
 });