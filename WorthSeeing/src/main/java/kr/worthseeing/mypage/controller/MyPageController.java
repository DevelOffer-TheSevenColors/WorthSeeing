package kr.worthseeing.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.service.UsersService;

@Controller
public class MyPageController {

	@Autowired
	private UsersService usersService;
	
	@GetMapping("/mypageMain")
	public String getmypage(Model model) {
		Users principalUsers = new Users();
		principalUsers.setUserId("user1"); // 로그인 구현되면 수정
		Users users = usersService.getUsers(principalUsers); // 현재 로그인한 사용자의 정보 가져오기(1명꺼)
		model.addAttribute("users", users);
		return "mypageMain";
	}

	@GetMapping("/mypageAuctionHistory")
	public String getmymypageAuctionHistory() {
		return "mypageAuctionHistory";
	}

	@GetMapping("/mypagePointHistory")
	public String getmypagePointHistory() {
		return "mypagePointHistory";
	}

	@GetMapping("/mypagePurchaseHistory")
	public String getmyagePurchaseHistory() {
		return "mypagePurchaseHistory";
	}

	@GetMapping("/mypageCouponMall")
	public String getmypageCouponMall() {
		return "mypageCouponMall";
	}

}
