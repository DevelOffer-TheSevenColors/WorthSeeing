// 인기순위 Top 5 이미지 띄우기
	function top5Img(cImgTopList) {
	 	var cImgTopArr = cImgTopList.split(",");
	       
		for (var i = 1; i <= 5; i++) {
			$("#topImg" + i).attr("src", cImgTopArr[i].slice(3));
			if (i == 5) {
				$("#topImg" + i).attr("src", cImgTopArr[i].slice(3).slice(0, -3));
			}
			
			$("#rank" + i).text("TOP  " + i);
			
		}
	}
	
	// 289개 블록 띄우기
	function createBlock(listBGSeq, cImgList) {
	    var cImgArr = cImgList.split(",");
	    var topSize = 0;
	    
		for (var i = 1; i <= 289; i++) {
			
			// 마지막 사진 처리
			if (i == 289) {
				var cImgURL = cImgArr[i].slice(3).slice(0, -3);
			} else {
				var cImgURL = cImgArr[i].slice(3);
			}
		
			var child = "<div class='tt' id=" + i + "><p class='numberDisplay'>" + i + "</p><p class='betweenDay'></p><input type='checkbox' id='checkedNum" + i + "' name='checkedNum" + i + "' th:value='" + i + "' class='reservationBtn' style='width:100px; height:100px;'></input><img class='imgDisplay' src='"+ cImgURL + "'/></li>";
	        
			$(".blockTest").append(child);

			if (i % 17 == 0) {
				$("#" + i).css('margin-left', 1700 + 'px');
				
				$("#" + i).css('margin-top', topSize + 'px');
				topSize += 100;
			} else {
				$("#" + i).css('margin-left', 100 * (i % 17) + 'px');
				
				$("#" + i).css('margin-top', topSize + 'px');
			}
		}
	}
	
	
	// 오늘 기준으로 이전 날짜 데이터가 있으면 localStorage 리셋
	function resetLocalStorage() {
		var today = new Date();
		today = today.getFullYear() + (today.getMonth() + 1) + today.getDate();
		
		for (var i=0; i<localStorage.length; i++) {
			  var searchKey = localStorage.key(i);
			  const expireDate = JSON.parse(localStorage.getItem(searchKey)).expire;
			  
			  if (Number(today) >= Number(expireDate)) {
				  window.localStorage.clear();
			}
		}
	}
	
	
	// localStorage에 클릭한 블록 저장하기
	function setLocalStorage(key, number, today, expireDate) {
		var random = Math.random();
		localStorage.setItem(key + random, JSON.stringify({ number: number, expire : expireDate })); // localStorage에 저장
		
		var setList = new Set();
		for (var i=0; i<localStorage.length; i++) {
			var searchKey = localStorage.key(i);
			  if (searchKey.slice(0, 3) == key) {
					setList.add(JSON.parse(localStorage.getItem(searchKey)).number.toString());
				}
			}
		if (setList.size == 10) { // 클릭한 블록 개수가 중복 없이 10개가 되면 사용자 포인트 + 500
			// db에 사용자 포인트 + 500 저장, 포인트 지급 alert 창 띄움, 걸려있는 링크로 이동
			location.href="/updatePoint?blockGroup_seq=" + number;
		} else {
			// 걸려있는 링크로 이동
			location.href="/click?blockGroup_seq=" + number;
		}
	}
	
	function changeRadioBtn(reservationBlockGroupSeqList, betweenDaysArr, usingBlockGroupArr) {
		var today = new Date();
		var expireDate = today.getFullYear() + (today.getMonth() + 1) + today.getDate() + 1;
		today = today.getFullYear() + (today.getMonth() + 1) + today.getDate();
		
	    
	 // 각 블록 클릭 시 localStorage에 저장 후 click 수 계산하여 처리
		$(".imgDisplay").click(function() {
				setLocalStorage($("#userName").text(), $(this).parents(".tt").attr("id"), today, expireDate);
		});
		
		$("input[name='checkbox']:radio").change(function() {
	        
	        var checkBoxID = $('input[name="checkbox"]:checked').attr('id');
	        
	        $(".numberDisplay").css("display", "none");
			$(".imgDisplay").css("display", "none");
			$(".reservationBtn").css("display", "none");
			$(".betweenDay").css("display", "none");
			
	        if (checkBoxID == "displayBlockNum") { // 블록 번호
				$(".numberDisplay").css("display", "block");
			} else if (checkBoxID == "displayBetweenDay") { // 남은 날짜 표시
				usingBlockGroupArr.forEach(function(blockGroupSeq, index) {
					$("#" + blockGroupSeq).find(".betweenDay").css("display", "block");
					$("#" + blockGroupSeq).find(".betweenDay").text(betweenDaysArr[index] + " 일 남음");
				});
			} else if (checkBoxID == "availableReserv") { // 예약 가능 블록
				reservationBlockGroupSeqList.forEach(function(BlockGroupSeq) {
					$("#" + BlockGroupSeq).find("input").css("display", "block");
				})
			} else { // 기존 이미지 표시
				$(".imgDisplay").css("display", "block");
			}
	        
		});
		
	}
	
