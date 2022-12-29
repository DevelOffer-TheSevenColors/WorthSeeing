
	$(function() {
		// 입력상자 클릭시 내용 비우기
		$("input[type='text'], input[type='password'], input[type='email']")
				.click(function() {
					this.value = '';
				});
	});

	function Validation() {
		var RegExp = /^[a-zA-Z0-9]{4,12}$/; //id와 pwassword 유효성 검사 정규식
		//이메일 유효성검사
		var e_RegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		var n_RegExp = /^[가-힣]{2,15}$/; //이름 유효성검사 정규식
		var t_RegExp = /^[0-1]{3}[0-9]{4}[0-9]{4}/;

		var jnumArr = new Array(); // 입력 한 주민번호를 저장해줄 배열 선언
		var jnumplus = [ 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 1 ]; // 주민번호 계산할때 쓰이는 배열
		var jnumSum = 0; //objNum[i] * jnumplus[i] 더한 값

		var objId = document.getElementById("id"); //아이디
		var objPwd = document.getElementById("password"); //비밀번호
		var objPwd2 = document.getElementById("password_confirm"); //비밀번호확인
		var objEmail = document.getElementById("email");//메일
		var objName = document.getElementById("name"); //이름
		var objAdr = document.getElementById("address"); //이름
		var objAdrdetail = document.getElementById("detailaddress"); //이름
		var objTel = document.getElementById("tel"); //전화번호

		// ================ ID 유효성검사 ================ //

		if (objId.value == '') {
			alert("아이디를 입력해주세요.");
			return false;
		}
		if (!RegExp.test(objId.value)) { //아이디 유효성검사
			alert("아이디는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.");
			return false;
		}

		// ================ PASSWORD 유효성검사 ===============//
		if (objPwd.value == '') { // 비밀번호 입력여부 검사
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		if (!RegExp.test(objPwd.value)) { //패스워드 유효성검사
			alert("비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.");
			return false;
		}
		if (objPwd.value == objId.value) { //패스워드와 ID가 동일한지 검사
			alert("비밀번호는 ID와 동일하면 안됩니다.");
			return false;
		}

		if (objPwd2.value != objPwd.value) { //비밀번호와 비밀번호확인이 동일한지 검사
			alert("비밀번호가 틀립니다. 다시 확인하여 입력해주세요.");
			return false;
		}

		// ================ email 유효성검사 ================ //
		if (e_RegExp.value == '') { // 이메일 입력여부 검사
			alert("이메일을 입력해주세요.");
			return false;
		}

		if (!e_RegExp.test(objEmail.value)) { //이메일 유효성 검사
			alert("올바른 이메일 형식이 아닙니다.");
			return false;
		}

		// ================ 이름 유효성검사 ================ //        
		if (objName.value == '') {
			alert("이름을 입력해주세요.");
			return false;
		}
		if (!n_RegExp.test(objName.value)) {
			alert("이름은 한글만 입력하여주세요.");
			return false;
		}
		
		// ================ 상세주소 유효성검사 ================ //        
		if (objAdr.value == '') {
			alert("주소를 입력해주세요.");
			return false;
		}
		
		// ================ 상세주소 유효성검사 ================ //        
		if (objAdrdetail.value == '') {
			alert("상세주소를 입력해주세요.");
			return false;
		}
		
		if (!t_RegExp.test(objTel.value)) {
			alert("01012345678 형식으로 입력해주세요!");
        	return false;
  		}

	}

	window.onload = function() {
		document
				.getElementById("address_kakao")
				.addEventListener(
						"click",
						function() { // 주소입력칸을 클릭하면
							new daum.Postcode(
									{
										oncomplete : function(data) { // 선택 시 입력값 세팅
											document.getElementById("address").value = data.address; // 주소 넣기
											document.getElementById("address_kakao").value = data.address; // 주소 넣기
											document
													.querySelector(
															"input[name=detailAddress]")
													.focus(); // 상세입력 포커싱
										}
									}).open();
						});
	}
