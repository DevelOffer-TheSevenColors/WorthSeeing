function count(type)  {
	  // 결과를 표시할 element
	  const resultElement = document.getElementById('result');
	  
	  // 현재 화면에 표시된 값
	  let number = resultElement.innerText;
	  
	  // 더하기/빼기
	  if(type == '10') {
	    number = parseInt(number) + 10;
	  } else if(type == '100')  {
	    number = parseInt(number) + 100;
	  } else if(type == '1000')  {
	    number = parseInt(number) + 1000;
	  } else if(type == '10000')  {
		number = parseInt(number) + 10000;
	  } 
	  // 결과 출력
	  resultElement.innerText = number;
	}